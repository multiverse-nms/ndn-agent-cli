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

@Command(name = "get-face", mixinStandardHelpOptions = true, description = "retrieves face information.")
public class GetFaceCommand implements Runnable {

	private final WebSocketClient wsClient;
	
	@Option(names = { "--faceid" }, required = true)
	private int faceId;

	public GetFaceCommand(WebSocketClient client) {
		this.wsClient = client;
	}

	@Override
	public void run() {
		wsClient.connect();
		System.out.println("retrieving face info...");
		wsClient.send(makeCommand().toString());
	}

	private JsonObject makeCommand() {
		String method = RpcCommands.GET_FACE.getName();
		String id = UUID.randomUUID().toString();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Id", faceId);
		JSONRPC2Request reqOut = new JSONRPC2Request(method, params, id);
		String jsonString = reqOut.toString();
		return new JsonObject(jsonString);
	}
}