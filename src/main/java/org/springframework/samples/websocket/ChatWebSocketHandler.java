package org.springframework.samples.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.websocket.WebSocketHandlerAdapter;
import org.springframework.websocket.WebSocketSession;

public class ChatWebSocketHandler extends WebSocketHandlerAdapter {

	private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<WebSocketSession>();

	@Override
	public void newSession(WebSocketSession session) throws Exception {
		this.sessions.add(session);
		String message = String.format("* %s %s", session, "has joined.");
		broadcast(message);
	}

	@Override
	public void sessionClosed(WebSocketSession session, int statusCode, String reason) throws Exception {
		this.sessions.remove(session);
		String message = String.format("* %s %s", session, "has disconnected.");
		broadcast(message);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, String message) throws Exception {
		// Never trust the client
		String filteredMessage = String.format("%s: %s", session, message);
		broadcast(filteredMessage);
	}

	private void broadcast(String msg) throws Exception {
		for (WebSocketSession session : sessions) {
			try {
				session.sendText(msg);
			} catch (IOException e) {
				this.sessions.remove(session);
				session.close(1006, null);
				String message = String.format("* %s %s", session, "has been disconnected.");
				broadcast(message);
			}
		}
	}

}
