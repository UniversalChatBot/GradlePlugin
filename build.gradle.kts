group = "tech.open-edgn.botManager"
version = "0.1-beta"

buildscript {
    repositories {

        maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
        maven { url = project.uri("https://mirrors.163.com/maven/repository/maven-public/") }
        mavenCentral()
        maven { url = project.uri("https://jitpack.io") }
    }


    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
    }
}

allprojects {
    repositories {
        maven { url = project.uri("https://maven.aliyun.com/repository/gradle-plugin/") }
        maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
        maven { url = project.uri("https://mirrors.163.com/maven/repository/maven-public/") }
        mavenCentral()
        maven { url = project.uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    for (childProject in childProjects.values) {
        delete(childProject.buildDir)
    }
}
