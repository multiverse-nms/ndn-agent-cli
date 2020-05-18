package nms.tools.commands;

import io.vertx.core.json.JsonObject;
import nms.tools.services.ManagementService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "EraseFace", mixinStandardHelpOptions = true, sortOptions = false)
public class EraseRouteCommand implements Runnable {

	private final ManagementService service;

	@Option(names = { "--prefix" }, required = true)
	private String prefix;

	@Option(names = { "--faceid" }, required = true)
	private int faceId;

	@Option(names = { "--origin" }, required = true)
	private int origin;

	public EraseRouteCommand(ManagementService service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.sendMessage(makeCommand());
	}

	private JsonObject makeCommand() {
		JsonObject json = new JsonObject();
		json.put("action", "erase-route");
		JsonObject payload = new JsonObject();
		payload.put("prefix", prefix);
		payload.put("faceid", faceId);
		payload.put("origin", origin);
		json.put("paylaod", payload);
		return json;
	}

}
