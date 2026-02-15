plugins {
    id("java")
}

group = ""
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

var autoServiceVersion = "1.1.1"

dependencies {
    compilyOnly("com.google.auto.service:auto-service:$autoServiceVersion")
    annotationProcessor("com.google.auto.service:auto-service:$autoServiceVersion")
    implementation("com.squareup:javapoet:1.13.0")

    compilyOnly(project(":annotation"))
    implementation(project(":annotation"))
}