package i.plugin

import i.plugin.tasks.FatJarPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class ChatBotPlugin : Plugin<Project> {
    override fun apply(target: Project) {
//        target.pluginManager.apply()
        val chatBotOptions = ChatBotOptions(target)
        target.extensions.add("chat",chatBotOptions)
        target.afterEvaluate {
//            target.dependencies.add("implementation","$rootp")
        }
        target.plugins.apply(FatJarPlugin::class.java)
    }
}
