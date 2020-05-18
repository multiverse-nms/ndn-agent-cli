package nms.tools.commands;

import io.vertx.core.json.JsonObject;
import nms.tools.services.ManagementService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "add-face", mixinStandardHelpOptions = true)
public class AddFaceCommand implements Runnable {

	@Option(names = { "--local" }, required = false)
	private String local;
	
	@Option(names = { "--port" }, required = false)
	private String port;

	@Option(names = { "--remote" }, required = false)
	private String remote;

	@Option(names = { "--scheme" }, required = false)
	private String scheme;

	private final ManagementService service;

	public AddFaceCommand(ManagementService service) {
		this.service = service;
	}

	@Override
	public void run() {
//		service.sendMessage(makeCommand());
		service.sendMessage(exampleCommand());
	}

	private JsonObject makeCommand() {
		JsonObject json = new JsonObject();
		json.put("action", "add_face");
		JsonObject payload = new JsonObject();
		payload.put("local", "");
		payload.put("port", "");
		payload.put("remote", "");
		payload.put("scheme", "");
		json.put("paylaod", payload);
		return json;
	}
	
	private JsonObject exampleCommand() {
		JsonObject json = new JsonObject();
		json.put("action", "add_face");
		JsonObject payload = new JsonObject();
		payload.put("local", "02:00:00:00:00:02");
		payload.put("port", "net_af_packet0");
		payload.put("remote", "02:00:00:00:00:01");
		payload.put("scheme", "ether");
		json.put("paylaod", payload);
		return json;
	}
	
	

}
