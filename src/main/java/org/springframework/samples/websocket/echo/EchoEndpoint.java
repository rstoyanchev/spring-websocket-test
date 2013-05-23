/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.springframework.samples.websocket.echo;

import java.io.IOException;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EchoEndpoint extends Endpoint {

	private static Logger logger = LoggerFactory.getLogger(EchoEndpoint.class);

	private final EchoService echoService;

	@Autowired
	public EchoEndpoint(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public void onOpen(final Session session, EndpointConfig endpointConfig) {

		logger.debug("Opened new session in instance " + this);

		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				try {
					logger.debug("Echoing message: " + message);
					session.getBasicRemote().sendText(echoService.getMessage(message));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
