package org.springframework.samples.websocket;

import org.springframework.websocket.WebSocketHandlerAdapter;
import org.springframework.websocket.WebSocketSession;

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
