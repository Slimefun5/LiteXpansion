plugins {
    java
    id("com.gradleup.shadow") version "9.3.2"
    id("io.github.intisy.github-gradle") version "1.8.2.1"
}

group = "dev.j3fftw"
description = "LiteXpansion is a Slimefun addon inspired by Industrial Craft 2."

// Shared Slimefun-addon build conventions (Java 8, spigot-api baseline, core dep, publish, shadow, version).
apply(from = "https://raw.githubusercontent.com/Slimefun5/workflows/stable/slimefun-addon.gradle")

repositories {
    maven("https://jitpack.io")
}

dependencies {
    githubImplementation("Slimefun5:SlimefunMetrics:v1.0.0")
    implementation("org.bstats:bstats-bukkit:2.2.1")

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.15.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.16")
    testImplementation("org.mockbukkit.mockbukkit:mockbukkit-v1.21:4.107.0") {
        exclude(group = "org.jetbrains", module = "annotations")
    }
}

configurations.testImplementation {
    extendsFrom(configurations.compileOnly.get())
}

tasks {
    shadowJar {
        relocate("org.bstats", "litexpansion.libs.bstats")
        relocate("dev.j3fftw.extrautils", "dev.j3fftw.litexpansion.extrautils")
    }
    compileTestJava { enabled = false }
    test { enabled = false }
}
