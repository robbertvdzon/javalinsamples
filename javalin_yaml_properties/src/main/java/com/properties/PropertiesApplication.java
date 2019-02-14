package com.properties;

import com.properties.props.ApplicationProperties;
import com.properties.props.PropertyManager;
import io.javalin.Javalin;

public class PropertiesApplication {
    Javalin app;

    public static void main(String[] args) {
        new PropertiesApplication().start();
    }

    public void start() {
        ApplicationProperties properties = new PropertyManager().loadProperies();
        System.out.println("Properties loaded:"+properties);

        app = Javalin.create();
        new RestEndpoints().initRestEndpoints(app, properties);
        app.start(8080);
    }

    public void stop() {
        app.stop();
    }
}
