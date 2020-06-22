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

@Command(name = "create-face", mixinStandardHelpOptions = true, description = "creates a face on the forwarder.")
public class CreateFaceCommand implements Runnable {

	@Option(names = { "--local" }, required = false)
	private String local;

	@Option(names = { "--port" }, required = false)
	private String port;

	@Option(names = { "--remote" }, required = false)
	private String remote;

	@Option(names = { "--scheme" }, required = false)
	private String scheme;

//	private final ManagementService service;
	private final WebSocketClient wsClient;

	public CreateFaceCommand(WebSocketClient client) {
		this.wsClient = client;
	}

	@Override
	public void run() {
		wsClient.connect();
		System.out.println("creating face...");
		wsClient.send(makeCommand().toString());
	}

	@SuppressWarnings("unused")
	private JsonObject makeCommand() {
		String method = RpcCommands.CREATE_FACE.getName();
		String id = UUID.randomUUID().toString();
		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("Local", local);
		params.put("Port", port);
		params.put("Remote", remote);
		params.put("Scheme", scheme);
		JSONRPC2Request reqOut = new JSONRPC2Request(method, params, id);
		String jsonString = reqOut.toString();
		return new JsonObject(jsonString);
	}

	private JsonObject dummyCommand() {
		// The remote method to call
		String method = "Face.Create";

		// The required parameters to pass
		Map<String, Object> params = new HashMap<String, Object>();
//				params.put("local", "02:00:00:00:00:02");
		params.put("Port", "0000:00:0a.0");
		params.put("Remote", "02:00:00:00:04:02");
		params.put("Scheme", "ether");

		// The mandatory request ID
		String id = UUID.randomUUID().toString();

		System.out.println("creating new request with properties :");
		System.out.println("\tmethod     : " + method);
		System.out.println("\tparameters : " + params);
		System.out.println("\tid         : " + id + "\n\n");

		// Create request
		JSONRPC2Request reqOut = new JSONRPC2Request(method, params, id);

		// Serialize request to JSON-encoded string
		String jsonString = reqOut.toString();
		System.out.println("serialised request to JSON-encoded string :");
		System.out.println("\t" + jsonString + "\n\n");

		return new JsonObject(jsonString);
	}

}
