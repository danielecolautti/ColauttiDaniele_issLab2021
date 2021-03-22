/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    // Use JUnit test framework.
    testImplementation("junit:junit:4.13")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")

    // https://mvnrepository.com/artifact/org.json/json
    implementation("org.json", "json", "20210307")

    //HTTP
    // https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
    implementation("org.apache.httpcomponents:httpclient:4.5.13")

    //JSON
    // https://mvnrepository.com/artifact/org.json/json
    implementation("org.json:json:20201115")
    // https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
    implementation("com.googlecode.json-simple:json-simple:1.1.1")

    //SOCKET.IO
    implementation("com.github.nkzawa:socket.io-client:0.6.0")
    // https://mvnrepository.com/artifact/javax.websocket/javax.websocket-api
    implementation("javax.websocket:javax.websocket-api:1.1")
    //javax.websocket api is only the specification
    implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:1.9")
}

application {
    // Define the main class for the application.
    mainClass.set("iss2021_resumablebw.App")
}
