package by.tms.projectinterpol.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

import static by.tms.projectinterpol.util.LoggerUtil.ERROR_DURING_PROPERTIES_LOADING_MESSAGE;

public class PropertiesManager {

    private static final Properties properties = new Properties();
    private static final Logger LOGGER = LogManager.getLogger(PropertiesManager.class);

    private PropertiesManager() {
        throw new UnsupportedOperationException();
    }

    static {
        try {
            properties.load(PropertiesManager.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            LOGGER.error(ERROR_DURING_PROPERTIES_LOADING_MESSAGE, e);
        }
    }

    public static String getPropertyByKey(String key) {
        return properties.getProperty(key);
    }


}
