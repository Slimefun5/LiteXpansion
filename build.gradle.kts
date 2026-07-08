plugins {
    java
    id("com.gradleup.shadow") version "9.3.2"
    id("io.github.intisy.github-gradle") version "1.8.2.1"
}

group = "dev.j3fftw"
description = "LiteXpansion is a Slimefun addon inspired by Industrial Craft 2."

apply(from = "https://raw.githubusercontent.com/Slimefun5/gradle/stable/slimefun-addon.gradle")

dependencies {
<<<<<<< HEAD
    implementation("com.github.Slimefun5:SlimefunMetrics:master-SNAPSHOT")
<<<<<<< HEAD
    compileOnly("io.papermc.paper:paper-api:${property("paperApiVersion")}")
=======
=======
    githubImplementation("Slimefun5:SlimefunMetrics:v1.0.0")
<<<<<<< HEAD
>>>>>>> origin/experimental
=======
    implementation("org.bstats:bstats-bukkit:2.2.1")
<<<<<<< HEAD
>>>>>>> origin/experimental
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
>>>>>>> origin/experimental
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    githubCompileOnly("Slimefun5:Slimefun5:gh-v5.2.3.2")

    // Shaded
    
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
=======
>>>>>>> origin/experimental
}

tasks {
    shadowJar {
<<<<<<< HEAD
        archiveFileName.set("LiteXpansion v${project.version}.jar")
=======
        relocate("org.bstats", "litexpansion.libs.bstats")
<<<<<<< HEAD
<<<<<<< HEAD
        archiveFileName.set("LiteXpansion-1.0.0-UNOFFICIAL.jar")
>>>>>>> origin/experimental
=======
        archiveFileName.set("LiteXpansion-$displayVersion.jar")
>>>>>>> origin/experimental
                relocate("dev.j3fftw.extrautils", "dev.j3fftw.litexpansion.extrautils")
        exclude("META-INF/**")
    }
    build {
        dependsOn(shadowJar)
    }
    compileTestJava {
        enabled = false
    }
    test {
        enabled = false
=======
        relocate("dev.j3fftw.extrautils", "dev.j3fftw.litexpansion.extrautils")
>>>>>>> origin/experimental
    }
}
