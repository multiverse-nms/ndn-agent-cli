package nms.tools.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.java_websocket.client.WebSocketClient;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;

import io.vertx.core.json.JsonObject;
import nms.tools.services.RpcCommands;
import picocli.CommandLine.Command;

@Command(name = "list-faces", mixinStandardHelpOptions = true, description = "retrieves the list of faces on the forwarder.")
public class ListFacesCommand implements Runnable {
	private final WebSocketClient wsClient;

	public ListFacesCommand(WebSocketClient client) {
		this.wsClient = client;
	}

	@Override
	public void run() {
		boolean success = false;
		try {
			success = wsClient.connectBlocking();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (success) {
			System.out.println("retrieving list of faces...");
			wsClient.send(makeCommand().toString());
		}
	}

	private JsonObject makeCommand() {
		String method = RpcCommands.LIST_FACES.getName();
		String id = UUID.randomUUID().toString();
		Map<String, Object> params = new HashMap<String, Object>();
		JSONRPC2Request reqOut = new JSONRPC2Request(method, params, id);
		String jsonString = reqOut.toString();
		return new JsonObject(jsonString);
	}
}