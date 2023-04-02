package siwen.fruit.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @projectName: LearnJava
 * @package: com.siwen.fruit.tool
 * @className: DBPropertiesTool
 * @author: 749291
 * @description: get database properties
 * @date: 2/7/2023 11:22 PM
 * @version: 1.0
 */

public class DbProperties {
    private static Properties properties = null;

    private DbProperties() {}

    public static Properties getProperties() throws IOException {
        if (properties == null) {
            properties = new Properties();
            properties.load(DbProperties.class.getResourceAsStream("/siwen/demo_db.properties"));
        }
        return properties;
    }
}
