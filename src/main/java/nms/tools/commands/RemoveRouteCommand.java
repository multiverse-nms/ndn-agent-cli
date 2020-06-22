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

@Command(name = "remove-route", mixinStandardHelpOptions = true, description = "removes a route from the forwarder.")
public class RemoveRouteCommand implements Runnable {

	@Option(names = { "--prefix" }, required = true)
	private String prefix;

	@Option(names = { "--faceid" }, required = true)
	private int faceId;

	@Option(names = { "--origin" }, required = true)
	private int origin;

	private final WebSocketClient wsClient;

	public RemoveRouteCommand(WebSocketClient client) {
		this.wsClient = client;
	}

	@Override
	public void run() {
		wsClient.connect();
		System.out.println("attempting to remove route...");
		wsClient.send(makeCommand().toString());
	}

	private JsonObject makeCommand() {
		
		String method = RpcCommands.REMOVE_ROUTE.getName();
		String id = UUID.randomUUID().toString();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Prefix", prefix);
		params.put("FaceId", faceId);
		params.put("Origin", origin);
		JSONRPC2Request reqOut = new JSONRPC2Request(method, params, id);
		String jsonString = reqOut.toString();
		return new JsonObject(jsonString);
	}

}
