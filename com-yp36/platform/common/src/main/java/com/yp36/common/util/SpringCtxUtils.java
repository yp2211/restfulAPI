package com.yp36.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Global spring container util
 *
 * @author yangpeng
 *
 */
public class SpringCtxUtils {
	public static ClassPathXmlApplicationContext ctx;

	public static void init(String[] springConfigs) {
		ctx = new ClassPathXmlApplicationContext(springConfigs);
	}

	public static void start() {
		ctx.start();
	}

	public static void close() {
		ctx.close();
	}

	public static <T> T getBean(Class<T> requiredType) {
		return ctx.getBean(requiredType);
	}

	public static <T> T getBean(String beanName, Class<T> requiredType) {
		return ctx.getBean(beanName, requiredType);
	}

	public Object getBean(String name) throws BeansException {
		return ctx.getBean(name);
	}
}
