package com.dependencyinjection.dagger;

import com.dependencyinjection.RestEndpoints;
import dagger.Component;
import io.javalin.Javalin;

import javax.inject.Singleton;

@Singleton
@Component(modules = ProjectModule.class)
public interface ProjectModuleComponent {
    Javalin getJavalin();

    RestEndpoints getRestEndpoints();
}
