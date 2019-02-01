package com.awakeee.hodgepodge.netty.server.request;

import java.io.Serializable;
import java.lang.reflect.Method;

public class RequestInfo implements Serializable {

    private String className;

    private String methodName;

    private Object[] args;

    private Class<?> returnType;

    private Class<?>[] parameterTypes;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public RequestInfo(Class<?> clz, Method method, Object[] args){
        this.className = clz.getSimpleName().toLowerCase();
        this.methodName = method.getName();
        this.returnType = method.getReturnType();
        this.args = args;
        this.parameterTypes = method.getParameterTypes();
    }
}
