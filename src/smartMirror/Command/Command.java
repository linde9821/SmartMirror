package smartMirror.Command;

import smartMirror.Exception.SmartMirrorException;

public abstract class Command {
	public Command(String command) {
		this.command = command;
	}

	protected String command;

	abstract public void runCommand() throws SmartMirrorException;

	public String getCommand() {
		return command;
	}

}
