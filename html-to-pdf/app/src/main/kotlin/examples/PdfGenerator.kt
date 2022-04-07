package examples

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import org.jsoup.Jsoup
import org.jsoup.helper.W3CDom
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.nio.file.FileSystems

class PdfGenerator {
    val baseUrl = FileSystems.getDefault().getPath("app/src/main/resources/").toUri().toURL().toString()

    fun generate(html: String): OutputStream {
        return ByteArrayOutputStream().apply {
            PdfRendererBuilder().toStream(this)
                .withW3cDocument(W3CDom().fromJsoup(Jsoup.parse(html)), baseUrl)
                .run()
        }
    }
}