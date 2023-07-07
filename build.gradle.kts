import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.vanniktech:gradle-maven-publish-plugin:0.24.0")
    }
}

plugins {
    kotlin("jvm")
}

apply(plugin = "com.vanniktech.maven.publish.base")

allprojects {
    group = Config.group
    version = Config.version

    tasks.withType<JavaCompile> {
        sourceCompatibility = Config.javaVersion.toString()
        targetCompatibility = Config.javaVersion.toString()
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = Config.javaVersion.toString()
        }
    }

    @Suppress("UnstableApiUsage")
    plugins.withId("com.vanniktech.maven.publish.base") {
        configure<MavenPublishBaseExtension> {
            publishToMavenCentral(SonatypeHost.S01)
            signAllPublications()

            pom {
                description.set("Simple high level constructs to handle common file types.")
                name.set(project.name)
                inceptionYear.set("2023")
                url.set("https://github.com/divyanshupundir/filetools-kt/")

                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }

                scm {
                    url.set("https://github.com/divyanshupundir/filetools-kt/")
                    connection.set("scm:git:git://github.com/divyanshupundir/filetools-kt.git")
                    developerConnection.set("scm:git:ssh://git@github.com/divyanshupundir/filetools-kt.git")
                }

                developers {
                    developer {
                        id.set("divyanshupundir")
                        name.set("Divyanshu Pundir")
                        url.set("https://github.com/divyanshupundir/")
                    }
                }
            }
        }
    }
}

task("publishAll") {
    dependsOn(":filetools:publish")
}

task("closeAndReleaseAll") {
    dependsOn(":filetools:closeAndReleaseRepository")
}

task("createGitTag") {
    doLast {
        val tagName = "v${Config.version}"
        exec { commandLine("git", "tag", tagName) }
        exec { commandLine("git", "push", "origin", tagName) }
    }
}
