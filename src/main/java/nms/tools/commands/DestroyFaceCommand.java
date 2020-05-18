package nms.tools.commands;

import io.vertx.core.json.JsonObject;
import nms.tools.services.ManagementService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "DestroyFace", mixinStandardHelpOptions = true, sortOptions = false)
public class DestroyFaceCommand implements Runnable {

	private final ManagementService service;

	@Option(names = { "--faceid" }, required = true)
	private int faceId;

	public DestroyFaceCommand(ManagementService service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.sendMessage(makeCommand());
	}

	private JsonObject makeCommand() {
		JsonObject json = new JsonObject();
		json.put("action", "destroy-face");
		JsonObject payload = new JsonObject();
		payload.put("faceid", faceId);
		json.put("paylaod", payload);
		return json;
	}

}
