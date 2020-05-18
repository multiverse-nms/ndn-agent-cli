package nms.tools.services;

import io.vertx.core.json.JsonObject;

public class ManagementService {

	WSClient client;

	public ManagementService() {
		try {
			client = new WSClient("ws://localhost:8080");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMessage(JsonObject json) {
		client.sendMessage(json);
	}

}
