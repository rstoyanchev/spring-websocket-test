/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http:www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.websocket.server.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.util.NestedServletException;
import org.springframework.websocket.server.endpoint.EndpointHandshakeHandler;


/**
 * A thin wrapper around {@link EndpointHandshakeRequestHandler} for use in Spring MVC.
 *
 */
public class EndpointHttpRequestHandler implements HttpRequestHandler {

	private final EndpointHandshakeHandler handshakeHandler;


	public EndpointHttpRequestHandler(EndpointHandshakeHandler handshakeHandler) {
		this.handshakeHandler = handshakeHandler;
	}

	@Override
	public void handleRequest(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
			throws ServletException, IOException {

		ServerHttpRequest request = new ServletServerHttpRequest(servletRequest);
		ServerHttpResponse response = new ServletServerHttpResponse(servletResponse);

		try {
			this.handshakeHandler.doHandshake(request, response);
		}
		catch (Exception e) {
			throw new NestedServletException("Handshake failed", e);
		}
	}

}
