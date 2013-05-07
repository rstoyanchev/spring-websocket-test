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
package org.springframework.samples.websocket.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.adapter.WebSocketHandlerAdapter;


public class IntermittentlyFailingEchoWebSocketHandler extends WebSocketHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(IntermittentlyFailingEchoWebSocketHandler.class);

	private boolean fail;


	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.debug("Echoing message: " + message);
		session.sendMessage(message);

		this.fail = !this.fail;

		if (this.fail) {
			logger.debug("Failing with SERVER_ERROR (1011)");
			session.close(CloseStatus.SERVER_ERROR);
		}
		else {
			logger.debug("Letting this one through");
		}
	}

}
