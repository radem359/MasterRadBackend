package com.radosav.master.rad.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
				
		StringBuilder sb = new StringBuilder();
		
		String headers =
		        Collections.list(request.getHeaderNames()).stream()
		            .map(headerName -> headerName + " : " + Collections.list(request.getHeaders(headerName)) )
		            .collect(Collectors.joining(", "));

	    if (headers.isEmpty()) {
	        sb.append("Request headers: NONE,");
	    } else {
	        sb.append("Request headers: ["+headers+"],");
	    }
		
		String parameters =
	        Collections.list(request.getParameterNames()).stream()
	            .map(p -> p + " : " + Arrays.asList( request.getParameterValues(p)) )
	            .collect(Collectors.joining(", "));             
		
	    if (parameters.isEmpty()) {
	        sb.append("Request parameters: NONE.");
	    } else {
	        sb.append("Request parameters: [" + parameters + "].");
	    }
	    
	    System.err.println("Sb "+sb.toString());
		
		
		response.setHeader("Access-Control-Allow-Headers", 
	    		  "Access-Control-Expose-Headers,"
	    		  + " Content-Type, "
	    		  + "Access-Control-Allow-Headers, "
	    		  + "Authorization, "
	    		  + "X-Requested-With");
		
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		
		if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			try {
				chain.doFilter(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
