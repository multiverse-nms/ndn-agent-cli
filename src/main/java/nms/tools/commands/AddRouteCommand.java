package nms.tools.commands;

import io.vertx.core.json.JsonObject;
import nms.tools.services.ManagementService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "AddRoute", mixinStandardHelpOptions = true, sortOptions = false)
public class AddRouteCommand implements Runnable {

	private final ManagementService service;

	@Option(names = {"--prefix"}, required = true)
    private String prefix;
	
	@Option(names = {"--faceid"}, required = true)
    private int faceId;
	
	@Option(names = {"--cost"}, required = false)
    private int cost;
	
	@Option(names = {"--origin"}, required = false)
    private int origin;
	
	
	public AddRouteCommand(ManagementService service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.sendMessage(makeCommand());
	}

	private JsonObject makeCommand() {
		JsonObject json = new JsonObject();
		json.put("action", "add_route");
		JsonObject payload = new JsonObject();
		payload.put("prefix", "/a/b/c");
		payload.put("faceid", 1001);
		payload.put("cost", 10);
		payload.put("origin", 0);
		json.put("paylaod", payload);
		return json;
	}
}
