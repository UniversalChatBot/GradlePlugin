package i.plugin.tools

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.bundling.Jar

class FatJarPlugin:Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("fatJar", Jar::class.java) {
            it.archiveClassifier.set("uber")
            val source = (target as ExtensionAware).extensions.getByName("sourceSets") as SourceSetContainer
            it.from(source.getByName("main").output)
            val runtimeClasspath = target.configurations.named("runtimeClasspath", Configuration::class.java)
            it.dependsOn(runtimeClasspath)
            it.from({
                runtimeClasspath.get().filter { it ->
                    it.name.endsWith("jar")
                }.map { it1 -> target.zipTree(it1) }
            })
        }
    }
}
