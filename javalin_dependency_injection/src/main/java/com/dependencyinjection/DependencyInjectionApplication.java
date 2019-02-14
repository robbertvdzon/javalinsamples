package com.dependencyinjection;

import com.dependencyinjection.dagger.ComponentFactory;
import io.javalin.Javalin;

public class DependencyInjectionApplication {

    private ComponentFactory factory;

    public static void main(String[] args) {
        new DependencyInjectionApplication().start();
    }

    public void start() {
        // build factory
        factory = new ComponentFactory();

        // get components
        Javalin app = factory.module().getJavalin();
        RestEndpoints restEndpoints = factory.module().getRestEndpoints();

        // initialize components
        restEndpoints.init();

        // start application
        app.start(8080);
    }

    public void stop() {
        Javalin app = factory.module().getJavalin();
        app.stop();
    }
}
