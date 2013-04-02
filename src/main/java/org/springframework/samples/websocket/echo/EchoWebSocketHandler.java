package org.springframework.samples.websocket.echo;

import org.springframework.websocket.WebSocketHandlerAdapter;
import org.springframework.websocket.WebSocketSession;

/**
 * Echo messages by implementing a Spring {@link WebSocketHandler} abstraction.
 */
public class EchoWebSocketHandler extends WebSocketHandlerAdapter {

	@Override
	public void handleTextMessage(WebSocketSession session, String message) {
		try {
			session.sendText(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
