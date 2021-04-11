import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.spring") version "1.4.31"
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

extra["springCloudVersion"] = "2020.0.2"
extra["kotlinLoggingVersion"] = "2.0.6"

tasks.withType<BootJar> {
    if (rootProject == project) {
        enabled = true
        mainClass.set("io.bandrefilipe.ktserver.KtServerApplicationKt")
    }
}

dependencies {
    implementation(project(":application"))
    implementation(project(":graphql-api"))
    implementation(project(":grpc-api"))
    implementation(project(":persistence"))
    implementation(project(":rest-api"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth")
}

allprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    group = "io.bandrefilipe"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<Jar> {
        enabled = true
        val archiveFileNamePrefix = when (rootProject) {
            project -> project.name
            else -> "${rootProject.name}-${project.name}"
        }
        archiveFileName.set("$archiveFileNamePrefix-${project.version}.${archiveExtension.get()}")
    }
}

subprojects {
    apply(plugin = "java-library")

    tasks.withType<BootJar> {
        enabled = false
    }
}

project(":application") {
    dependencies {
        api(project(":commons"))
    }
}

project(":commons") {
    extra["apacheCommonsCodecVersion"] = "1.15"

    dependencies {
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        // https://github.com/MicroUtils/kotlin-logging
        api("io.github.microutils:kotlin-logging-jvm:${property("kotlinLoggingVersion")}")
        // Default https://start.spring.io/ dependencies for Kotlin
        api("org.springframework.boot:spring-boot-starter")
        api("org.jetbrains.kotlin:kotlin-reflect")
        api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        implementation("commons-codec:commons-codec:${property("apacheCommonsCodecVersion")}")
    }
}

project(":graphql-api") {
    dependencies {
        implementation(project(":application"))
    }
}

project(":grpc-api") {
    dependencies {
        implementation(project(":application"))
    }
}

project(":persistence") {
    dependencies {
        implementation(project(":application"))

        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        runtimeOnly("org.postgresql:postgresql")
    }
}

project(":rest-api") {
    dependencies {
        implementation(project(":application"))

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    }
}
