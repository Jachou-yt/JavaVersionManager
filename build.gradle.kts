plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("idea")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Jachou-yt/JavaVersionManager")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}


group = "fr.jachou"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("fr.flowarg:flowmultitools:1.4.0")
}

tasks.test {
    useJUnitPlatform()
}