package com.awakeee.hodgepodge.netty.server.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class BeanUtil implements ApplicationListener<ContextRefreshedEvent> {
	Logger logger = Logger.getLogger(BeanUtil.class);

	@Value("${server.port}")
	String port;

	String ip = InetAddress.getLocalHost().getHostAddress();

	@Value("${spring.application.name}")
	String appName;

	Map<String, Object> beanMap = new HashMap<>();
	static ApplicationContext ctx = null;

	static Map<Class, Object> bizBeanMap = new ConcurrentHashMap<>();

	public BeanUtil() throws UnknownHostException {
	}

	public String getAppName() {
		return appName;
	}

	public String getPort() {
		return port;
	}

	public String getIp() {
		return ip;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		ctx = contextRefreshedEvent.getApplicationContext();

		for (String s : ctx.getBeanNamesForAnnotation(Service.class)) {
			beanMap.put(s, ctx.getBean(s));
			logger.info("Loading EventAware bean: " + s + " - " + ctx.getBean(s).getClass().getSimpleName());
		}
	}


	public Collection<Object> getBeans() {
		return beanMap.values();
	}

	public static Object getBeanByClass(Class clz) {
		if (clz == null) {
			return null;
		}
		return ctx.getBean(clz);
	}

	public static Object getBeanByName(String clz) {
		if (clz == null) {
			return null;
		}
		return ctx.getBean(clz);
	}
}


