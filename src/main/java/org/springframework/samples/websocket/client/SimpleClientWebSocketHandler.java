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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.websocket.TextMessage;
import org.springframework.websocket.WebSocketHandlerAdapter.TextMessageHandlerAdapter;
import org.springframework.websocket.WebSocketSession;

public class SimpleClientWebSocketHandler extends TextMessageHandlerAdapter {

	private Log logger = LogFactory.getLog(SimpleClientWebSocketHandler.class);

	private final GreetingService greetingService;


	@Autowired
	public SimpleClientWebSocketHandler(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		try {
			TextMessage message = new TextMessage(this.greetingService.getGreeting());
			session.sendMessage(message);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleTextMessage(TextMessage message, WebSocketSession session) throws Exception {
		logger.debug("Received: " + message);
		try {
			session.close();
		}
		catch (IOException e) {
			logger.error("Failed to close", e);
		}
	}

}
