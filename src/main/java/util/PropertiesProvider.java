package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {
    private static final Logger log = LoggerFactory.getLogger(PropertiesProvider.class);
    private static final String PROPERTY_FILE_NAME = "config.properties";
    private static PropertiesProvider instance = null;
    private static final Properties prop = new Properties();

    private PropertiesProvider() {
        try (InputStream input = this.getClass()
                .getClassLoader()
                .getResourceAsStream(PROPERTY_FILE_NAME)) {

            prop.load(input);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static PropertiesProvider getInstance() {
        if(instance == null) {
            instance = new PropertiesProvider();
        }
        return instance;
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
