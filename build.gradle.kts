plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "io.github.itsk1mlot.respawn"
version = "1.0"


val testServerDir: String by project

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(kotlin("test"))
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")

    implementation("io.github.monun:kommand-api:3.1.2")
    implementation("io.github.monun:invfx-api:3.3.0")
}

task("fatJar", type = Jar::class) {
    dependsOn(tasks.jar)
    archiveBaseName.set("${project.name}-FAT")
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

task("testJar") {
    val fatJar = tasks.getByName("fatJar") as Jar
    dependsOn(fatJar)
    if(!fatJar.archiveFile.get().asFile.exists()) return@task
    val destFile = File("$testServerDir/plugins/Example.jar")
    doLast {
        tasks.jar.get().archiveFile.get().asFile.copyTo(destFile, overwrite = true)
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("EasterMainKt")
}