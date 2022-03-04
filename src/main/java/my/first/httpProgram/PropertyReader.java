package my.first.httpProgram;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties = loadProperties();

    private static Properties loadProperties(){
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(new File("config.properties")));
        }catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty(String propertyKey){
        return properties.getProperty(propertyKey);
    }
}
