package com.jsonrpc.core;

import java.lang.reflect.AccessibleObject;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.jabsorb.callback.InvocationCallback;

@SuppressWarnings("serial")
public class MyInvocationCallback implements InvocationCallback {

	@SuppressWarnings("unchecked")
	@Override
	public void preInvoke(Object request, Object arg1, AccessibleObject aco,
			Object[] arg3) throws Exception {
		System.out.println("preInvoke--->");

		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) request;
			StringBuilder sb = new StringBuilder();

			/**
			 * 1、第一种拦截方式，可以再header中加入消息鉴权信息
			 */
			Enumeration<String> e = req.getHeaderNames();

			while (e.hasMoreElements()) {
				String key = e.nextElement();
				sb.append(key + "=" + req.getHeader(key) + ",");
			}

			System.out.println("header--->" + sb.toString());
			
			/**
			 * 1、打印请求消息体，记录日志，可以从request中获取用户登录信息
			 * 
			 * 备注：如果单纯作为服务端提供json rpc方式的服务，可以考虑改造，
			 * 消息体通过base64加密，解密过程放到JSONRPCServlet中（在获取_jabsorb_beenHere时候），然后解密
			 * 
			 */
			System.out.println(req.getAttribute("_jabsorb_beenHere"));
		}
		/**
		 * 2、第二种拦截方式，可以在被调用的方法上面加入RunAnnotation
		 */
		RunAnnotation ra = aco.getAnnotation(RunAnnotation.class);
		if (ra != null) {
			System.out.println(ra.value());

			if ("auth".equals(ra.value())) {
				throw new RuntimeException("No Auth!");
			}

		}

	}

	@Override
	public void postInvoke(Object arg0, Object arg1, AccessibleObject arg2,
			Object arg3) throws Exception {

		System.out.println("postInvoke--->");

	}

}
