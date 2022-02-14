package examples

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
import java.io.StringWriter

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
}