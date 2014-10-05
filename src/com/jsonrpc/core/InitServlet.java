package com.jsonrpc.core;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.jabsorb.JSONRPCBridge;
import org.jabsorb.callback.InvocationCallback;

import com.jsonrpc.Example;

@SuppressWarnings("serial")
public class InitServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		JSONRPCBridge bridge = JSONRPCBridge.getGlobalBridge();

		//在此处可以将spring引入进来
		InvocationCallback callback = new MyInvocationCallback();
		bridge.registerCallback(callback, HttpServletRequest.class);
		bridge.registerObject("example", new Example());
	}
}
