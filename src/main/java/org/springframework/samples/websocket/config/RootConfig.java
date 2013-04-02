/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.DefaultEchoService;
import org.springframework.samples.websocket.EchoAnnotatedEndpoint;
import org.springframework.samples.websocket.EchoEndpoint;
import org.springframework.samples.websocket.EchoService;
import org.springframework.samples.websocket.EchoWebSocketHandler;
import org.springframework.websocket.server.endpoint.EndpointRegistration;
import org.springframework.websocket.server.endpoint.ServletEndpointExporter;

@Configuration
public class RootConfig {

	// ----------------------------------------------------------
	// JSR-356 deployment via ServerContainer.addEndpoint

	@Bean
	public ServletEndpointExporter endpointExporter() {
		return new ServletEndpointExporter();
	}

	// Spring initialized javax.websocket.Endpoint instance per connection (default spec scope semantics)
	// Resulting instance not managed by Spring

	@Bean
	public EndpointRegistration echoEndpoint() {
		return new EndpointRegistration("/echoEndpoint", EchoEndpoint.class);
	}

	// A single javax.websocket.Endpoint instance (possibly managed by Spring) to serve all incoming connections

	@Bean
	public EndpointRegistration echoEndpointSingleton() {
		return new EndpointRegistration("/echoEndpointSingleton", new EchoEndpoint(echoService()));
	}

	// A single @ServerEndpoint instance (possibly managed by Spring) to serve all incoming connections
	// The configurator attribute must be set to SpringConfigurator

	@Bean
	public EchoAnnotatedEndpoint echoAnnotatedEndpointSingleton() {
		return new EchoAnnotatedEndpoint(echoService());
	}

	@Bean
	public EchoService echoService() {
		return new DefaultEchoService("Did you say \"%s\"?");
	}

	// ----------------------------------------------------------
	// programmatic JSR-356 deployment w/ WebSocketHandler

	@Bean
	public EndpointRegistration echoWebSocketHandler() {
		return new EndpointRegistration("/echoWebSocketHandler", new EchoWebSocketHandler());
	}

}
