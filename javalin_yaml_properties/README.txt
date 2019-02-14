Added properties support.

For testing, I also added the maven-shade-plugin so we can build a runnable jar.
When running java -jar target/javalin_yaml_properties-0.1.0-shaded.jar, the application.yml from outside the jar is used.
When running the JavalinBaseApplicationIT, the application.yml from the test resources is used
When running the application from within the IDE, the  application.yml from the mail resources is used