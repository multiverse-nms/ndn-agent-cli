package nms.tools.commands;

import io.vertx.core.json.JsonObject;
import nms.tools.services.ManagementService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "UnregisterPrefix", mixinStandardHelpOptions = true, sortOptions = false)
public class UnregisterPrefixCommand implements Runnable {

	private final ManagementService service;

	@Option(names = { "--prefix" }, required = true)
	private String prefix;

	public UnregisterPrefixCommand(ManagementService service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.sendMessage(makeCommand());
	}

	private JsonObject makeCommand() {
		JsonObject json = new JsonObject();
		json.put("action", "unregister-prefix");
		JsonObject payload = new JsonObject();
		payload.put("prefix", prefix);
		json.put("paylaod", payload);
		return json;
	}

}
