package io.docuport_g1.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static {
        try{
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            //FileInputStream input = new FileInputStream("config.properties");
            properties = new Properties();
            properties.load(input);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperties(String keyName){
        return properties.getProperty(keyName);
    }

}
