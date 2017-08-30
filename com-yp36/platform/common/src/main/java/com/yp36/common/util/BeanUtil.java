package com.yp36.common.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.ContextLoader;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * desc
 * Created by yangpeng on 2017/06/13.
 */
@Slf4j
public class BeanUtil {

    public static Object getCustomProperty(String field, Object obj) {
        Object o;
        if (field.contains(".")) {
            String parent = field.substring(0, field.indexOf("."));
            String child = field.substring(field.indexOf(".") + 1);
            field = child;
            obj = getPropertyValue(parent, obj);
        }
        o = getPropertyValue(field, obj);
        return o;
    }

    public static Object getPropertyValue(String field, Object obj) {
        Object o = null;
        try {
            PropertyDescriptor pd = new PropertyDescriptor(field,
                    obj.getClass());
            Method getMethod = pd.getReadMethod();
            o = getMethod.invoke(obj);
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>>>getPropertyValue failed.", e);
        }
        return o;
    }


    /**
     * @param beanName
     * @return Object
     */
    public static Object getBean(String beanName) {
        return ContextLoader.getCurrentWebApplicationContext().getBean(beanName);
    }

    /**
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        return ContextLoader.getCurrentWebApplicationContext().getBean(requiredType);
    }
}
