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
package org.springframework.samples.websocket.client;

import java.io.IOException;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleClientEndpoint extends Endpoint {

	private Log logger = LogFactory.getLog(SimpleClientEndpoint.class);

	private final GreetingService greetingService;


	@Autowired
	public SimpleClientEndpoint(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@Override
	public void onOpen(final Session session, EndpointConfig config) {
		try {
			String message = this.greetingService.getGreeting();
			session.getBasicRemote().sendText(message);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				logger.debug("Received message: " + message);
				try {
					session.close();
					logger.debug("Closed session");
				}
				catch (IOException e) {
					logger.error("Failed to close", e);
				}
			}
		});
	}

}
