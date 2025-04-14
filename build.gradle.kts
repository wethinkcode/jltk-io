plugins {
    `java-library`
    // Check for updates with ./gradlew dependencyUpdates
    id("com.github.ben-manes.versions") version "0.51.0"
    id("io.deepmedia.tools.deployer") version "0.16.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    java {
        withJavadocJar()
        withSourcesJar()
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}


deployer {
    // 1. Artifact definition.
    // https://opensource.deepmedia.io/deployer/artifacts
    content {
        component {
            fromJava()
        }
    }

    // 2. Project details.
    // https://opensource.deepmedia.io/deployer/configuration
    projectInfo {
        version = project.version.toString()
        description.set("jltk-io -- Java Learning Toolkit Console I/O Library.")
        url.set("https://github.com/wethinkcode/jltk-io")
        scm.fromGithub("wethinkcode", "jltk-io")
        license(MIT)
        developer("wtcos", "opensource@wethinkcode.co.za", "WeThinkCode", "https://wethinkcode.co.za")
        groupId.set("za.co.wethinkcode")
    }

    localSpec {

    }

    // 3. Central Portal configuration.
    // https://opensource.deepmedia.io/deployer/repos/central-portal
    centralPortalSpec {
        signing.key.set(secret("SIGNING_KEY"))
        signing.password.set(secret("SIGNING_PASSPHRASE"))
        auth.user.set(secret("UPLOAD_USERNAME"))
        auth.password.set(secret("UPLOAD_PASSWORD"))
    }
}