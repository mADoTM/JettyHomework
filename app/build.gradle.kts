plugins {
    application
}

val jettyVersion = "9.4.33.v20201020"

dependencies {
    implementation(project(":models"))
    implementation(project(":jooq-generated"))

    implementation("com.google.inject:guice:5.1.0")
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.google.inject.extensions:guice-servlet:5.1.0")


    implementation("org.eclipse.jetty:jetty-server:$jettyVersion")
    implementation("org.eclipse.jetty:jetty-servlet:$jettyVersion")
    implementation("org.eclipse.jetty:jetty-client:$jettyVersion")


    implementation("org.flywaydb:flyway-core:9.8.2")
    implementation("org.postgresql:postgresql:42.5.0")
}

application {
    mainClass.set("ru.mail.Main")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}