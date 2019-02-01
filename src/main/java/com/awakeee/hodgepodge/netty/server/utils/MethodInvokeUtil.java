package com.awakeee.hodgepodge.netty.server.utils;

import com.awakeee.hodgepodge.netty.server.request.RequestInfo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MethodInvokeUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    public static ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<String, Object>();

    public Object processMethod(Object msg) {
        RequestInfo req = (RequestInfo) msg;
        Object claszz = null;
        if (!classMap.containsKey(req.getClassName())) {
            try {
                claszz = BeanUtil.getBeanByName(req.getClassName());
                classMap.put(req.getClassName(), claszz);
            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {
            claszz = classMap.get(req.getClassName());
        }
        Method method = null;
        try {
            method = claszz.getClass().getMethod(req.getMethodName(), req.getParameterTypes());
            Object result = method.invoke(claszz, req.getArgs());
            return result;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
