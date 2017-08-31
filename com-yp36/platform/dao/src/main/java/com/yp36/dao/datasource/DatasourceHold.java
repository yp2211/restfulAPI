package com.yp36.dao.datasource;

/**
 * Created by yangpeng on 2017/06/16.
 */
public class DatasourceHold {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static String getDBType() {
        return contextHolder.get();
    }

    public static void setDBType(String dbType) {
        contextHolder.set(dbType);
    }

    public static void clearDBType() {
        contextHolder.remove();
    }
}
