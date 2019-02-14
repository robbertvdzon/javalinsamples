package com.dependencyinjection.dagger;

public class ComponentFactory {

    private ProjectModuleComponent myModuleComponent;

    public ComponentFactory() {
        myModuleComponent = DaggerProjectModuleComponent.create();
    }

    public ProjectModuleComponent module() {
        return myModuleComponent;
    }
}
