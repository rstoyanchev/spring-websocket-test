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
import org.springframework.samples.websocket.echo.DefaultEchoService;
import org.springframework.samples.websocket.echo.EchoAnnotatedEndpoint;
import org.springframework.samples.websocket.echo.EchoEndpoint;
import org.springframework.samples.websocket.echo.EchoService;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;


@Configuration
public class EndpointConfig {

	@Bean
	public ServerEndpointRegistration echo() {
		return new ServerEndpointRegistration("/echo", EchoEndpoint.class);
	}

	@Bean
	public ServerEndpointRegistration echoSingleton() {
		return new ServerEndpointRegistration("/echoSingleton", new EchoEndpoint(echoService()));
	}

//	@Bean
	public EchoAnnotatedEndpoint echoAnnotatedSingleton() {
		return new EchoAnnotatedEndpoint(echoService());
	}

	@Bean
	public EchoService echoService() {
		return new DefaultEchoService("Did you say \"%s\"?");
	}

	@Bean
	public ServerEndpointExporter endpointExporter() {
		return new ServerEndpointExporter();
	}

}
