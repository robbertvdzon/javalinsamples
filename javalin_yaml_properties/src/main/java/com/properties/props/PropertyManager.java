package com.properties.props;

import com.properties.PropertiesApplication;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.function.Supplier;

public class PropertyManager {

    private ApplicationProperties NULL = new ApplicationProperties();

    public ApplicationProperties loadProperies() {
        ApplicationProperties applicationProperties = loadProperies(() -> getInputStreamFromFile("application.yml"));
        if (applicationProperties == NULL) {
            applicationProperties = loadProperies(() -> getInputStreamFromClasspath("/application.yml"));
        }
        return applicationProperties;
    }

    private ApplicationProperties loadProperies(Supplier<InputStream> inputStreamSupplier) {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = inputStreamSupplier.get();
            if (inputStream != null) {
                return yaml.load(inputStream);
            }
        } catch (Exception e) {
            System.err.println("Properties could not be loaded");
        }
        return NULL;
    }

    private InputStream getInputStreamFromFile(String filename) {
        try {
            return new FileInputStream(filename);
        } catch (Exception e) {
            return null;
        }
    }

    private InputStream getInputStreamFromClasspath(String filename) {
        try {
            return PropertiesApplication.class.getResource(filename).openStream();
        } catch (Exception e) {
            return null;
        }
    }

}
