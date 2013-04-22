package org.springframework.samples.websocket.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.websocket.TextMessage;
import org.springframework.websocket.TextMessageHandler;
import org.springframework.websocket.WebSocketHandler;
import org.springframework.websocket.WebSocketHandlerAdapter;
import org.springframework.websocket.WebSocketSession;

/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 */
public class EchoWebSocketHandler extends WebSocketHandlerAdapter implements TextMessageHandler {

	private static Logger logger = LoggerFactory.getLogger(EchoWebSocketHandler.class);

	private final EchoService echoService;

	@Autowired
	public EchoWebSocketHandler(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.debug("Opened new session in instance " + this);
	}

	@Override
	public void handleTextMessage(TextMessage message, WebSocketSession session) throws Exception {
		logger.debug("Echoing message: " + message);
		String responsePayload = this.echoService.getMessage(message.getPayload());
		session.sendMessage(new TextMessage(responsePayload));
	}

}
