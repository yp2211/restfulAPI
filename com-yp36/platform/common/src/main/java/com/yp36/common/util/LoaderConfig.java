package com.yp36.common.util;

import java.util.Properties;

/**
 * @author yangpeng
 */
public class LoaderConfig {

    private static final String fileName = "system.properties";
    private static Properties properties = new Properties();

    static {
        PropertiesUtil.reLoad(properties, PropertiesUtil.getFilePath(fileName));
    }

    /**
     * @param preKey
     * @return
     */
    public static String getObject(String preKey) {
        return properties.get(preKey).toString();
    }

    /**
     * @param preKey
     * @param defaultValue
     * @return
     */
    public static String getObjectStr(String preKey, String defaultValue) {
        if (!properties.containsKey(preKey) || StringUtil.isEmpty(properties.get(preKey).toString())) {
            return defaultValue;
        }
        return properties.get(preKey).toString();
    }

    /**
     * @param preKey
     * @param defaultValue
     * @return
     */
    public static int getObjectInt(String preKey, int defaultValue) {
        if (!properties.containsKey(preKey) || StringUtil.isEmpty(properties.get(preKey).toString())) {
            return defaultValue;
        }
        return Integer.valueOf(properties.get(preKey).toString());
    }
}
