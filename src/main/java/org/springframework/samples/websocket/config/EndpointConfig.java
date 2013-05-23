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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.websocket.echo.EchoEndpoint;
import org.springframework.web.socket.server.endpoint.ServerEndpointExporter;
import org.springframework.web.socket.server.endpoint.ServerEndpointRegistration;

@Configuration
public class EndpointConfig {

	@Autowired
	private RootConfig rootConfig;

	@Bean
	public ServerEndpointExporter endpointExporter() {
		return new ServerEndpointExporter();
	}

	@Bean
	public ServerEndpointRegistration echoEndpoint() {
		return new ServerEndpointRegistration("/echoEndpoint", EchoEndpoint.class);
	}

	@Bean
	public ServerEndpointRegistration echoEndpointSingleton() {
		return new ServerEndpointRegistration("/echoEndpointSingleton", new EchoEndpoint(this.rootConfig.echoService()));
	}

}
