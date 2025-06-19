@file:Suppress("UnstableApiUsage")

import com.google.protobuf.gradle.id
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

// provide general GAV coordinates
group = "net.scrayos"
version = "1.0.0-SNAPSHOT"
description = "Passage gRPC Extension Example"

// hook the plugins for the builds
plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.ktlint)
}

// configure the repositories for the dependencies
repositories {
    // official maven repository
    mavenCentral()
}

// declare all dependencies (for compilation and runtime)
dependencies {
    // add adventure dependencies
    implementation(libs.bundles.adventure)

    // add gRPC dependencies that are necessary for compilation and execution
    implementation(libs.protobuf.kotlin)
    implementation(libs.bundles.grpc)
    runtimeOnly(libs.grpc.netty)

    // add coroutines for our coroutine-based communication
    implementation(libs.kotlin.coroutines.core)
}

// configure the kotlin extension
kotlin {
    // set the toolchain version required to build this project
    // replaces sourceCompatibility and targetCompatibility as it also sets these implicitly
    // https://kotlinlang.org/docs/gradle-configure-project.html#gradle-java-toolchains-support
    jvmToolchain(21)
}

// configure the protobuf extension (protoc + grpc)
protobuf {
    // configure the protobuf compiler for the proto compilation
    protoc {
        // set the artifact for protoc (the compiler version to use)
        artifact = libs.protoc.core.get().toString()
    }

    // configure the plugins for the protobuf build process
    plugins {
        // add a new "grpc" plugin for the java stub generation
        id("grpc") {
            // set the artifact for protobuf code generation (stubs)
            artifact = libs.protoc.genJava.get().toString()
        }
        // add a new "grpckt" plugin for the kotlin stub generation
        id("grpckt") {
            artifact = libs.protoc.genKotlin.get().toString() + ":jdk8@jar"
        }
    }

    // configure the proto tasks (extraction, generation, etc.)
    generateProtoTasks {
        // only modify the main source set, we don't have any proto files in test
        all().configureEach {
            // apply the "java" and "kotlin" builtin tasks as we are compiling against java and kotlin
            builtins {
                // id("java") – is added implicitly by default
                id("kotlin")
            }

            // apply the "grpc" and "grpckt" plugins whose specs are defined above, without special options
            plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

// configure ktlint
ktlint {
    // explicitly use a recent ktlint version for latest checks
    version = libs.versions.ktlint

    // exclude any generated files
    filter {
        // exclude generated protobuf files
        exclude { element -> element.file.path.contains("/generated/") }
    }

    // configure the reporting to use checkstyle syntax
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.SARIF)
    }
}

// configure tasks
tasks {
    jar {
        // exclude the proto files as we won't need them in downstream projects
        exclude("**/*.proto")

        // exclude the now empty folders (because the proto files were removed)
        includeEmptyDirs = false

        // remove duplicates from the final jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}
