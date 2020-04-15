package common;

import configuration.Environment;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void setup() {
        Environment environment = new Environment();

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/api";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = environment.getProperties().getProperty("base_host");
        }
        RestAssured.baseURI = baseHost;

    }
}
