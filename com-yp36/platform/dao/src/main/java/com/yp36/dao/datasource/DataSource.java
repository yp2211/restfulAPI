package com.yp36.dao.datasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {

	String CF_DATASOURCE = "cf_dataSource";

    String BIGDATA_DATASOURCE = "bigdata_dataSource";

    String name();
}