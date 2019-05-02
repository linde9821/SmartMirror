package smartMirror.Command;

import smartMirror.Exception.SmartMirrorException;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help");
		super.addAlias("?");
		super.addAlias("h");
	}

	@Override
	public void runCommand() throws SmartMirrorException {
		System.out.println("List of known Commands:");
		System.out.println(CommandHandler.getKnownCommands());
	}

}
