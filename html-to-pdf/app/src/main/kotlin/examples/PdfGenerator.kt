package examples

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import org.jsoup.Jsoup
import org.jsoup.helper.W3CDom
import java.io.File
import java.nio.file.FileSystems

class PdfGenerator {
    val baseUrl = FileSystems.getDefault().getPath("app/src/main/resources/").toUri().toURL().toString()

    fun generate(html: String) {
        File("output.pdf").outputStream()
            .use {
                PdfRendererBuilder().toStream(it)
                    .withW3cDocument(W3CDom().fromJsoup(Jsoup.parse(html)), baseUrl)
                    .run()
            }
    }
}