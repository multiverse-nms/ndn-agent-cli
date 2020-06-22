package nms.tools.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.java_websocket.client.WebSocketClient;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;

import io.vertx.core.json.JsonObject;
import nms.tools.services.RpcCommands;
import picocli.CommandLine.Command;

@Command(name = "list-fib", mixinStandardHelpOptions = true, description = "retrieves the fib.")
public class ListFibCommand implements Runnable {
	private final WebSocketClient wsClient;

	public ListFibCommand(WebSocketClient client) {
		this.wsClient = client;
	}

	@Override
	public void run() {
		wsClient.connect();
		System.out.println("retrieving FIB...");
		wsClient.send(makeCommand().toString());
	}

	private JsonObject makeCommand() {
		String method = RpcCommands.LIST_FIB.getName();
		String id = UUID.randomUUID().toString();
		Map<String, Object> params = new HashMap<String, Object>();
		JSONRPC2Request reqOut = new JSONRPC2Request(method, params, id);
		String jsonString = reqOut.toString();
		return new JsonObject(jsonString);
	}
}