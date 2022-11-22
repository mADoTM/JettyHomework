plugins {
    application
}

dependencies {
    implementation(project(":models"))
    implementation(project(":jooq-generated"))

    implementation("com.google.inject:guice:5.1.0")
    implementation("com.google.code.gson:gson:2.10")
    // https://mvnrepository.com/artifact/com.google.inject.extensions/guice-servlet
    implementation("com.google.inject.extensions:guice-servlet:5.1.0")


    // https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-server
    implementation("org.eclipse.jetty:jetty-server:9.4.33.v20201020")
// https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-servlet
    implementation("org.eclipse.jetty:jetty-servlet:9.4.33.v20201020")
// https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-client
    implementation("org.eclipse.jetty:jetty-client:9.4.33.v20201020")


    implementation("org.flywaydb:flyway-core:9.8.2")
    implementation("org.postgresql:postgresql:42.5.0")
}

application {
    mainClass.set("ru.mail.Main")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}