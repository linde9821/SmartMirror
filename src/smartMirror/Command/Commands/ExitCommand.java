package smartMirror.Command.Commands;

import smartMirror.Command.Command;
import smartMirror.Exception.SmartMirrorException;

public class ExitCommand extends Command {

	public ExitCommand() {
		super("exit");
		addAlias("e");
		addAlias("close");
	}

	@Override
	public void runCommand() throws SmartMirrorException {
		System.out.println("Stop");
		System.exit(0);
	}

}
