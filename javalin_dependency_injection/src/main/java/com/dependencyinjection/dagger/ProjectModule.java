package com.dependencyinjection.dagger;

import com.dependencyinjection.HelloWorldService;
import com.dependencyinjection.RestEndpoints;
import dagger.Module;
import dagger.Provides;
import io.javalin.Javalin;

import javax.inject.Singleton;

@Module
public class ProjectModule {

    @Provides
    @Singleton
    static Javalin getJavalin() {
        return Javalin.create();
    }

    @Provides
    @Singleton
    static HelloWorldService getHelloWorldService() {
        return new HelloWorldService();
    }

    @Provides
    @Singleton
    static RestEndpoints getRestEndpoints(Javalin app, HelloWorldService helloWorldService) {
        return new RestEndpoints(app, helloWorldService);
    }

}
