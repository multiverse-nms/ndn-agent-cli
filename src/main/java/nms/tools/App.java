package nms.tools;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import io.vertx.core.json.JsonObject;
import nms.tools.commands.AddRouteCommand;
import nms.tools.commands.CreateFaceCommand;
import nms.tools.commands.DestroyFaceCommand;
import nms.tools.commands.GetFaceCommand;
import nms.tools.commands.ListFacesCommand;
import nms.tools.commands.ListFibCommand;
import nms.tools.commands.ListPortsCommand;
import nms.tools.commands.RemoveRouteCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "nmscli", mixinStandardHelpOptions = true, version = "nms-cli 1.0")
public class App implements Runnable {

	public static void main(String[] args) {

		String address = "ws://localhost:9000";

		WebSocketClient wsClient = null;

		try {
			wsClient = new WebSocketClient(new URI(address)) {
				@Override
				public void onOpen(ServerHandshake handshakedata) {
					System.out.println("connected successfully to the websocket server");
				}

				@Override
				public void onMessage(String message) {
					JsonObject json = new JsonObject(message);
					System.out.println("result:\n" + json.encodePrettily());
					
				}

				@Override
				public void onClose(int code, String reason, boolean remote) {
					System.out.println("client closed, reason: " + reason);
				}

				@Override
				public void onError(Exception ex) {
					System.out.println("error: " + ex.getCause());
				}
			};
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final CommandLine cmd = new CommandLine(new App());
		cmd.addSubcommand("create-face", new CreateFaceCommand(wsClient));
		cmd.addSubcommand("destroy-face", new DestroyFaceCommand(wsClient));
		cmd.addSubcommand("get-face", new GetFaceCommand(wsClient));
		cmd.addSubcommand("list-faces", new ListFacesCommand(wsClient));
		cmd.addSubcommand("list-ports", new ListPortsCommand(wsClient));
		cmd.addSubcommand("list-fib", new ListFibCommand(wsClient));
		cmd.addSubcommand("add-route", new AddRouteCommand(wsClient));
		cmd.addSubcommand("remove-route", new RemoveRouteCommand(wsClient));

		
		int exitCode = cmd.execute(args);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(exitCode);

	}

	@Override
	public void run() {
		CommandLine.usage(this, System.out);
	}

}
