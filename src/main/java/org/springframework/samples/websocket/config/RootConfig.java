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
import org.springframework.samples.websocket.chat.ChatAnnotatedEndpoint;
import org.springframework.samples.websocket.echo.DefaultEchoService;
import org.springframework.samples.websocket.echo.EchoAnnotatedEndpoint;
import org.springframework.samples.websocket.echo.EchoEndpoint;
import org.springframework.samples.websocket.echo.EchoService;
import org.springframework.websocket.server.endpoint.EndpointRegistration;
import org.springframework.websocket.server.endpoint.ServletEndpointExporter;

@Configuration
public class RootConfig {

	// Detect and export beans of type javax.websocket.server.ServerEndpointConfig (type-based API)
	// Detect and export beans annotated with @ServerEndpoint

	@Bean
	public ServletEndpointExporter endpointExporter() {

		// The SCI endpoint scan is disabled in web.xml, so we must list @ServerEndpoint classes here

		ServletEndpointExporter exporter = new ServletEndpointExporter();
		exporter.setAnnotatedEndpointClasses(EchoAnnotatedEndpoint.class, ChatAnnotatedEndpoint.class);
		return exporter;
	}

	// Spring initialized javax.websocket.Endpoint with default spec scope (instance per connection) semantics
	// Resulting instance not managed by Spring

	@Bean
	public EndpointRegistration echoEndpoint() {
		return new EndpointRegistration("/echoEndpoint", EchoEndpoint.class);
	}

	// A javax.websocket.Endpoint singleton (possibly managed by Spring) serves all incoming connections

	@Bean
	public EndpointRegistration echoEndpointSingleton() {
		return new EndpointRegistration("/echoEndpointSingleton", new EchoEndpoint(echoService()));
	}

	@Bean
	public EchoService echoService() {
		return new DefaultEchoService("Did you say \"%s\"?");
	}

}
