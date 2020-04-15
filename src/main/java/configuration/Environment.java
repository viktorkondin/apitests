package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {

    private Properties properties;

    public Environment() {
        String environmentType = findEnvironmentType();
        properties = createProperties(environmentType);
    }

    public Properties getProperties() {
        return properties;
    }

    private Properties createProperties(String environment) {
        try {
            Properties properties = new Properties();
            properties.load(getInputStreamFromProperties(environment));
            return properties;

        } catch (IOException e) {
            throw new RuntimeException("Was not able to load properties", e);
        }
    }

    private InputStream getInputStreamFromProperties(String environment) {

        try {
            String userDir = System.getProperty("user.dir");
            String fileName = String.format("%s/src/main/resources/environment-%s.properties", userDir, environment);
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Environment was not provided. Please provide TEST / RC / PROD", e);
        }
    }

    private String findEnvironmentType() {
        String environmentType = System.getProperty("environment_type");
        if (environmentType == null) {
            throw new IllegalStateException("Environment type is not defined");
        }
        return environmentType.toLowerCase();
    }
}