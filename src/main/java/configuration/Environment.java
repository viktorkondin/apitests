package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment {

    public Environment() {
        initializeEnvironment();
    }

    private String environmentType = "test";
    private Properties properties = new Properties();

    private void initializeEnvironment() {

        environmentType = System.getProperty("environment_type") == null ? environmentType : System.getProperty("environment_type");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(String.format(System.getProperty("user.dir") + "/src/main/resources/environment-%s.properties", environmentType.toLowerCase()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEnvironmentType() {
        return environmentType;
    }

    public Properties getProperties()
    {
        return properties;
    }

}
