package com.awakeee.hodgepodge.netty.server.proxy;

import com.awakeee.hodgepodge.netty.client.util.RemoteCall;
import com.awakeee.hodgepodge.netty.server.request.RequestInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ServiceProxy
{
	private static Logger LOG = LogManager.getLogger(ServiceProxy.class);

	public static Object getService(final Class<?> clz){
		Object newProxyInstance = Proxy.newProxyInstance(
				clz.getClassLoader(),
				new Class[] { clz },
				new InvocationHandler()
				{
					@Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
					{
							RequestInfo req = new RequestInfo(clz,method,args);
							Object obj = RemoteCall.call(req);
							return obj;
					}
				});
		return (Object)newProxyInstance;
	}
}
