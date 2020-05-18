package nms.tools;

import nms.tools.commands.AddFaceCommand;
import nms.tools.commands.AddRouteCommand;
import nms.tools.commands.DestroyFaceCommand;
import nms.tools.commands.EraseRouteCommand;
import nms.tools.commands.RegisterPrefixCommand;
import nms.tools.commands.UnregisterPrefixCommand;
import nms.tools.services.ManagementService;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "nmscli", mixinStandardHelpOptions = true, version = "nms-cli 1.0")
public class App implements Runnable {

	public static void main(String[] args) {

		final ManagementService service = new ManagementService();

		final CommandLine cmd = new CommandLine(new App());
		cmd.addSubcommand("add-face", new AddFaceCommand(service));
		cmd.addSubcommand("destroy-face", new DestroyFaceCommand(service));
		cmd.addSubcommand("add-route", new AddRouteCommand(service));
		cmd.addSubcommand("erase-route", new EraseRouteCommand(service));
		cmd.addSubcommand("register-prefix", new RegisterPrefixCommand(service));
		cmd.addSubcommand("unregister-prefix", new UnregisterPrefixCommand(service));

		String[] args1 = {"add-face"};
		int exitCode = cmd.execute(args1);
		System.exit(exitCode);
		

	}

	@Override
	public void run() {
		CommandLine.usage(this, System.out);
	}

}
