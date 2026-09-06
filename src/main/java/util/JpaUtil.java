package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public final class JpaUtil {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("BaiTap01PU", databaseProperties());

    private JpaUtil() {
    }

    private static Map<String, String> databaseProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.url",
                value("DB_URL", "jdbc:sqlserver://localhost:1433;databaseName=ServletCRUDMVC;encrypt=true;trustServerCertificate=true;loginTimeout=5"));
        properties.put("jakarta.persistence.jdbc.user", value("DB_USERNAME", "YOUR_SQLSERVER_USERNAME"));
        properties.put("jakarta.persistence.jdbc.password", value("DB_PASSWORD", "YOUR_SQLSERVER_PASSWORD"));
        return properties;
    }

    private static String value(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }
        return defaultValue;
    }

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    public static void close() {
        if (ENTITY_MANAGER_FACTORY.isOpen()) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }
}
