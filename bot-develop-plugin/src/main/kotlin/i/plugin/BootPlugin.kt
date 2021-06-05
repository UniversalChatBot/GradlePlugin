package i.plugin

import i.plugin.tools.FatJarPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class BootPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply(FatJarPlugin::class.java)
    }
}
