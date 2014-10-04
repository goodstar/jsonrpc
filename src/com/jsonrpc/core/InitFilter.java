package com.jsonrpc.core;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.jsonrpc.Example;
import com.metaparadigm.jsonrpc.JSONRPCBridge;

public class InitFilter implements Filter {

	public static final String JSONRPC_BRIDGE = "JSONRPCBridge";

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletrequest;

		if (req.getSession().getAttribute(JSONRPC_BRIDGE) == null) {
			JSONRPCBridge bridge = new JSONRPCBridge();
			bridge.registerObject("example", new Example());
			req.getSession().setAttribute(JSONRPC_BRIDGE, bridge);
		}

		filterchain.doFilter(servletrequest, servletresponse);
	}

	@Override
	public void init(FilterConfig filterconfig) throws ServletException {

	}

}
