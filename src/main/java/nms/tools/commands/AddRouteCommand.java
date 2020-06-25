package nms.tools.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.java_websocket.client.WebSocketClient;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;

import io.vertx.core.json.JsonObject;
import nms.tools.services.RpcCommands;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "add-route", mixinStandardHelpOptions = true, sortOptions = false, description = "adds a route on the forwarder.")
public class AddRouteCommand implements Runnable {

	private final WebSocketClient wsClient;

	@Option(names = { "--prefix" }, required = true)
	private String prefix;

	@Option(names = { "--faceid" }, required = true)
	private int faceId;

	@Option(names = { "--cost" }, required = false)
	private int cost;

	@Option(names = { "--origin" }, required = false)
	private int origin;

	public AddRouteCommand(WebSocketClient client) {
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
			System.out.println("adding route...");
			wsClient.send(makeCommand().toString());
		}

	}

	private JsonObject makeCommand() {
		String method = RpcCommands.ADD_ROUTE.getName();
		String id = UUID.randomUUID().toString();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Prefix", prefix);
		params.put("FaceId", faceId);
		params.put("Cost", cost);
		params.put("Origin", origin);
		JSONRPC2Request reqOut = new JSONRPC2Request(method, params, id);
		String jsonString = reqOut.toString();
		return new JsonObject(jsonString);
	}
}
