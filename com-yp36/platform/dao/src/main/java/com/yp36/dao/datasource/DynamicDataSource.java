package com.yp36.dao.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * Created by yangpeng on 2017/06/16.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatasourceHold.getDBType();
    }

    @Override
    public DataSource determineTargetDataSource() {
        return super.determineTargetDataSource();
    }
}
