package org.springframework.samples.websocket.echo;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.websocket.CloseStatus;
import org.springframework.websocket.TextMessage;
import org.springframework.websocket.WebSocketHandler;
import org.springframework.websocket.WebSocketSession;
import org.springframework.websocket.adapter.TextWebSocketHandlerAdapter;

/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 */
public class EchoWebSocketHandler extends TextWebSocketHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(EchoWebSocketHandler.class);

	private final EchoService echoService;

	@Autowired
	public EchoWebSocketHandler(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		logger.debug("Opened new session in instance " + this);
//		throw new UnsupportedOperationException("!");
	}

	@Override
	public void handleMessage(WebSocketSession session, TextMessage message) {
		logger.debug("Echoing message: " + message);
		String responsePayload = this.echoService.getMessage(message.getPayload());
		try {
			session.sendMessage(new TextMessage(responsePayload));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) {
		try {
			session.close(CloseStatus.SERVER_ERROR);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
