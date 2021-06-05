import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = rootProject.version
group = rootProject.group

plugins {
    id("com.gradle.plugin-publish") version "0.15.0"
    kotlin("jvm")
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        create("botPlugin") {
            id = "${rootProject.group}.gradle"
            implementationClass = "i.plugin.BootPlugin"
        }
    }
}

dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation(gradleApi())
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.6.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}
pluginBundle {
    website = "https://www.gradle.org/"
    vcsUrl = "https://github.com/UniversalChatBot/GradlePlugin"
    version = rootProject.version
    description = "UniversalChatBot Project Gradle plugin."


    (plugins) {

        "botPlugin" {
            displayName = "UniversalChatBot Project Gradle plugin."
            tags = listOf("individual", "tags", "per", "plugin")
        }

    }
    mavenCoordinates {
        groupId = rootProject.group.toString()
        artifactId = "gradlePlugin"
        version = rootProject.version.toString()
    }
}



