package smartMirror.Command;

import java.io.IOException;

import smartMirror.Exception.SmartMirrorException;

public abstract class Command {
	public Command(String command) {
		this.command = command;
	}

	protected String command;

	abstract public void runCommand() throws SmartMirrorException, IOException;

	public String getCommand() {
		return command;
	}

}
