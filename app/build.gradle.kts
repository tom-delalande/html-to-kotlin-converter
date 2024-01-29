plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.20"
    kotlin("plugin.serialization") version "1.9.20"
    application
}

application {
    mainClass.set("app.MainKt")
    applicationName = "app"
}

tasks.distZip {
    enabled = false
}

tasks.distTar {
    archiveFileName.set("app-bundle.${archiveExtension.get()}")
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}
dependencies {
}
