rootProject.name = "passage-extension-example"

buildCache {
    local {
        directory = File(rootDir, "build-cache")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
