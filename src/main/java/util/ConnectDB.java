package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final Logger log = LoggerFactory.getLogger(ConnectDB.class);
    private static ConnectDB instance = null;
    private Connection connection;

    private ConnectDB() {
        PropertiesProvider propertiesProvider = PropertiesProvider.getInstance();
        try {
            connection = DriverManager
                    .getConnection(propertiesProvider.getProperty("datasource.url"),
                            propertiesProvider.getProperty("datasource.username"),
                            propertiesProvider.getProperty("datasource.password"));

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static ConnectDB getInstance() {
        if(instance == null) {
            instance = new ConnectDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
