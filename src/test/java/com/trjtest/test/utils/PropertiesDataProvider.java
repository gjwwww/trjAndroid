package com.trjtest.test.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by gjw on 17/2/24.
 *
 *从properties文件中获取相关的测试数据
 */
public class PropertiesDataProvider {
    public static String getTestData(String configFilePath, String key) {
        Configuration config = null;
        try {
            config = new PropertiesConfiguration(configFilePath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return String.valueOf(config.getProperty(key));
    }
}
