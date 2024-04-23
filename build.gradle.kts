import com.vanniktech.maven.publish.SonatypeHost

plugins {
    `java-library`
    id("com.vanniktech.maven.publish") version "0.28.0"
    // Check for updates with ./gradlew dependencyUpdates
    id("com.github.ben-manes.versions") version "0.51.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.10.2")
    implementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}


mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = true)
    signAllPublications()

    coordinates("za.co.wethinkcode","jltk-io",version.toString())
    pom {
        name.set("jltk-io")
        description.set("jltk-io Java Learning Toolkit I/O Library")
        url.set("https://github.com/wethinkcode/jltk-io")
        licenses {
            license {
                name.set("The MIT License")
                url.set("https://github.com/wethinkcode/jltk-io/blob/main/LICENSE")
            }
        }
        developers {
            developer {
                id.set("wtcos")
                name.set("WeThinkCode Open Source Team")
                email.set("opensource@wethinkcode.ca.za")
            }
        }
        scm {
            connection.set("git@github.com:wethinkcode/jltk-io.git")
            developerConnection.set("git@github.com:wethinkcode/jltk-io.git")
            url.set("https://github.com/wethinkcode/jltk-io.git")
        }
    }
}