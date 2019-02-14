mvn package
java -javaagent:jmx_prometheus_javaagent-0.11.0.jar=8081:config.yaml -jar target\javalin_prometheus-0.1.0-shaded.jar