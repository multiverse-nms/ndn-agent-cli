package nms.tools.services;

import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import io.vertx.core.json.JsonObject;

public class WSClient {

	WebSocketClient client;
	SimpleSocket socket;
	Session session;

	public WSClient(String destUri) throws Exception {
		client = new WebSocketClient();
		socket = new SimpleSocket();

		client.start();
		URI echoUri = new URI(destUri);
		ClientUpgradeRequest request = new ClientUpgradeRequest();
		Future<Session> future = client.connect(socket, echoUri, request);
		session = future.get(2, TimeUnit.SECONDS);
		System.out.printf("connecting to : %s%n", echoUri);

		// wait for closed socket connection.
		socket.awaitClose(30, TimeUnit.SECONDS);
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

}
