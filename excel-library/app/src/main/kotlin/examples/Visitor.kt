package examples

import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.CellType.*
import org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND
import org.apache.poi.ss.usermodel.HorizontalAlignment.CENTER
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.*
import java.awt.Color
import java.io.FileOutputStream

private const val FIRST_ROW_INDEX = 0
private val EGGPLANT_50 = XSSFColor(Color(230, 218, 246), DefaultIndexedColorMap())
private val EGGPLANT_200 = XSSFColor(Color(174, 136, 226), DefaultIndexedColorMap())
private val EGGPLANT_900 = XSSFColor(Color(36, 2, 87), DefaultIndexedColorMap())

class Visitor {
    private val workbook: XSSFWorkbook = XSSFWorkbook()
    private var sheet: XSSFSheet = workbook.createSheet("Report A")
    private var defaultFont: XSSFFont = workbook.createFont().apply {
        fontHeightInPoints = 14
        fontName = "calibri"
    }
    private var rowIndex = FIRST_ROW_INDEX

    private fun createHeaders() {
        val row = sheet.createRow(rowIndex++)
        val headers = listOf("Account", "Name", "Date", "Status", "Amount")
        val headerStyle = workbook.createCellStyle().apply {
            setFillForegroundColor(EGGPLANT_900) // When using a custom color
            fillPattern = SOLID_FOREGROUND
            setFont(defaultFont.apply {
                bold = true
                color = IndexedColors.WHITE.index
            })
        }

        row.apply {
            headers.forEachIndexed { i, header ->
                createCell(i).apply {
                    setCellValue(header)
                    cellStyle = headerStyle
                }
            }
        }
    }

    fun save(filename: String) {
        repeat(5) { index -> sheet.autoSizeColumn(index) }
        FileOutputStream(filename).use { workbook.write(it) }
    }

    fun visit(category: ReportCategory) {
        val startingIndex = rowIndex
        sheet.createRow(rowIndex++).apply {
            val rowStyle = workbook.createCellStyle().apply {
                setFillForegroundColor(EGGPLANT_900)
                fillPattern = SOLID_FOREGROUND
                setFont(defaultFont.apply {
                    color = IndexedColors.WHITE.index
                })
            }
            createCell(0).apply {
                setCellValue(category.name)
                cellStyle = rowStyle
            }
            createCell(4, FORMULA).apply {
                cellFormula = "SUM(E${rowIndex + 2}:E${rowIndex + category.children.size + 1})"
                cellStyle = rowStyle.apply {
                    dataFormat = workbook.creationHelper.createDataFormat().getFormat("0.00")
                }
            }

        }
        sheet.addMergedRegion(CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 3))
        category.children.forEach { it.accept(this) }
        println(">>>> group range: ${startingIndex + 1} - ${startingIndex + category.size()}")
        sheet.groupRow(startingIndex + 1, startingIndex + category.size())
    }

    fun visit(report: Report) {
        sheet.createRow(rowIndex++).apply {
            rowStyle = workbook.createCellStyle().apply {
                 borderTop = BorderStyle.NONE
            }
            createCell(0, NUMERIC).apply {
                setCellValue(report.account.toDouble()) //Only accepts Double??
                cellStyle = workbook.createCellStyle().apply {
                    fillForegroundColor = IndexedColors.GREY_25_PERCENT.index // When using an indexed color
                    fillPattern = SOLID_FOREGROUND
                    alignment = CENTER
                }
            }
            createCell(1, STRING).apply {
                setCellValue(report.name)
                cellStyle = workbook.createCellStyle().apply {
                    indention = rowIndex.toShort()
                }
            }
            createCell(2).apply {
                setCellValue(report.date)
                cellStyle = workbook.createCellStyle().apply {
                    dataFormat = workbook.creationHelper.createDataFormat()
                        .getFormat("MM\\/dd\\/yyyy") //Forward slash has to be escaped
                }
            }
            createCell(3).apply {
                setCellValue(report.status)
            }
            createCell(4, NUMERIC).apply {
                setCellValue(report.amount)
                cellStyle = workbook.createCellStyle().apply {
                    dataFormat = workbook.creationHelper.createDataFormat().getFormat("0.00")

                }
            }
        }

    }
}