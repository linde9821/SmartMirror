package smartMirror.Command;

import smartMirror.Exception.SmartMirrorException;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help");
		super.addAlias("?");
	}

	@Override
	public void runCommand() throws SmartMirrorException {
		// TODO Auto-generated method stub
		System.out.println("send help!");
	}

}
