package examples

import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import freemarker.template.Version
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
import java.io.ByteArrayOutputStream
import java.io.StringWriter
import java.util.*


class TemplateRenderer {
    init {
        Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath")
        Velocity.setProperty("classpath.resource.loader.class", ClasspathResourceLoader::class.java.name)
    }

    fun render(templateFile: String, contextValues: Map<String, Any>): String {
        val template = Velocity.getTemplate(templateFile)
        val context = VelocityContext(contextValues)
        StringWriter().use {
            template.merge(context, it)
            return it.toString()
        }
    }

    fun render2(templateFile: String, contextValues: Map<String, Any>): String {
        val cfg = Configuration(Version(2, 3, 20))


        // Where do we load the templates from:
        cfg.setClassForTemplateLoading(ClasspathResourceLoader::class.java, "/")

        cfg.defaultEncoding = "UTF-8"
        cfg.locale = Locale.US
        cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
        val template = cfg.getTemplate(templateFile)

        val stream = ByteArrayOutputStream()
        template.process(contextValues, stream.writer())
        return stream.toString()
    }
}