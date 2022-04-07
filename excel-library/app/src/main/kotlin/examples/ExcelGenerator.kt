package examples

import org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFColor
import java.awt.Color
import kotlin.reflect.full.declaredMemberProperties

// Data formats are here: https://poi.apache.org/apidocs/dev/org/apache/poi/ss/usermodel/BuiltinFormats.html
class ExcelGenerator {
//    private val eggplant50 = XSSFColor(Color(230, 218, 246))
//    private val eggplant200 = XSSFColor(Color(174, 136, 226))
//    private val eggplant900 = XSSFColor(Color(36, 2, 87))
//    private lateinit var defaultFont: Font
//    private lateinit var titleFont: Font
//
//    fun generate() {
//        workbook {
//            defaultFont = createFont {
//                fontName = "calibri"
//            }
//            titleFont = createFont {
//                fontName = "calibri"
//                setColor(eggplant50)
//                bold = true
//            }
//
//            sheet("Report A") {
//                generateHeading()
//                repeat(10) {
//                    generateRow()
//                }
//                generateCollapsableRow()
//                xssfSheet.addMergedRegion(CellRangeAddress(11, 11, 0, 3))
//                repeat(3) {
//                    generateRow()
//                }
//                xssfSheet.groupRow(12, 14)
//
//
//            }.also {
//                it.xssfSheet.autoSizeColumn(0)
//                it.xssfSheet.autoSizeColumn(1)
//                it.xssfSheet.autoSizeColumn(3)
//                it.xssfSheet.autoSizeColumn(4)
//                it.xssfSheet.autoSizeColumn(5)
//            }
//
//
//        }.write("test.xlsx")
//    }
//
//    fun Sheet.generateHeading() {
//        val headers =
//            Report::class.declaredMemberProperties.map { p -> p.name.replaceFirstChar { it.titlecase() } }.reversed()
//        val headerStyle = createCellStyle { setFont(defaultFont) }
//        row(headerStyle) {
//            headers.forEach { cell(it) }
//        }
//    }
//
//    fun Sheet.generateCollapsableRow() {
//        row {
//            val style = generateCollapseStyle(this)
//            cell("Cost of Goods", style)
//            cell("")
//            cell("")
//            cell("")
//            cell(Formula("SUM(E2:E11)"), style.also { it.dataFormat = 4 })
//        }
//    }
//
//    fun Sheet.generateRow() {
//        val report = Report.random()
//        row {
//            val style = generateCellStyle(this)
//            cell(report.status, style)
//            cell(report.name, style)
//            cell(report.account, style)
//            cell(
//                report.date,
//                style.also {
//                    style.dataFormat = xssfWorkbook.creationHelper.createDataFormat().getFormat("MM/dd/yyyy")
//                })
//            cell(report.amount, style.also {
//                it.dataFormat = 2
//            })
//        }
//    }
//
//    fun generateCellStyle(row: Row): XSSFCellStyle {
//        return row.createCellStyle {
//            setFont(defaultFont)
//            setFillForegroundColor(eggplant50)
//            setFillPattern(SOLID_FOREGROUND)
//        }
//    }
//
//    fun generateCollapseStyle(row: Row): XSSFCellStyle {
//        return row.createCellStyle {
//            setFont(titleFont)
//            setFillForegroundColor(eggplant900)
//            setFillPattern(SOLID_FOREGROUND)
//        }
//    }
//
//    fun generateAFormula() {
//
//    }
//
//    fun groupRows() {
//
//    }
}