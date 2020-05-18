package nms.tools.services;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import io.vertx.core.json.JsonObject;

@WebSocket(maxTextMessageSize = 64 * 1024, maxIdleTime = Integer.MAX_VALUE)
public class SimpleSocket {
	private final CountDownLatch closeLatch;
	private Session session;

	public SimpleSocket() {
		this.closeLatch = new CountDownLatch(1);
	}

	public boolean awaitClose(int duration, TimeUnit unit) throws InterruptedException {
		return this.closeLatch.await(duration, unit);
	}

	public CountDownLatch getLatch() {
		return this.closeLatch;
	}

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		System.out.printf("connection closed: %d - %s%n", statusCode, reason);
		this.session = null;
		
	}

	public void sendMessage(JsonObject json) {
		try {
			Future<Void> fut;
			fut = session.getRemote().sendStringByFuture(json.toString());
			fut.get(2, TimeUnit.SECONDS);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		System.out.printf("connected: %s%n", session);
		this.session = session;
		this.closeLatch.countDown(); // trigger latch
	}

	@OnWebSocketMessage
	public void onMessage(String msg) {
		System.out.printf("received message: %s%n", msg);
		if (msg.contains("goodbye")) {
			session.close(StatusCode.NORMAL, "close");
		}
	}

	@OnWebSocketError
	public void onError(Throwable cause) {
		System.out.print("WebSocket Error: ");
		cause.printStackTrace(System.out);
	}
}
