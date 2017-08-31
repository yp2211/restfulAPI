package com.yp36.dao.datasource;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by yangpeng on 2017/06/16.
 */
public class DynamicDataSourceAspect implements MethodBeforeAdvice, AfterReturningAdvice {

    public void afterReturning(Object returnValue, Method method,
                               Object[] args, Object target) throws Throwable {
        DatasourceHold.clearDBType();
    }

    public void before(Method method, Object[] args, Object target)
            throws Throwable {

        if (method.isAnnotationPresent(DataSource.class)) {
            DataSource datasource = method.getAnnotation(DataSource.class);
            DatasourceHold.setDBType(datasource.name());
        } else {
            DatasourceHold.clearDBType();
        }

    }
}