import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

version = rootProject.version
group = rootProject.group

val pkgArtifactId = "gradlePlugin"

plugins {
    id("com.gradle.plugin-publish") version "0.15.0"
    kotlin("jvm")
    `java-gradle-plugin`
    `maven-publish`

}

gradlePlugin {
    plugins {
        create("botPlugin") {
            id = "${rootProject.group}.gradlePlugin"
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


pluginBundle {
    website = "https://github.com/UniversalChatBot"
    vcsUrl = "https://github.com/UniversalChatBot/GradlePlugin"
    version = rootProject.version
    description = "Gradle plugin for UniversalChatBot Project."


    (plugins) {
        "botPlugin" {
            displayName = "UniversalChatBot Project Gradle plugin."
            tags = listOf("individual", "tags", "per", "plugin")
        }

    }
    mavenCoordinates {
        groupId = rootProject.group.toString()
        artifactId = pkgArtifactId
        version = rootProject.version.toString()
    }
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = pkgArtifactId
            version = rootProject.version.toString()
            from(components["java"])
            artifact(sourcesJar.get())
        }


    }
    repositories {
        mavenLocal()
    }
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
