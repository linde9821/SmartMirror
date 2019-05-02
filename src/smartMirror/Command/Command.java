package smartMirror.Command;

import java.util.ArrayList;

import smartMirror.Exception.SmartMirrorException;

public abstract class Command {
	public Command(String command) {
		this.command = command;
		alias = new ArrayList<String>();
	}

	protected String command;
	protected ArrayList<String> alias;

	public abstract void runCommand() throws SmartMirrorException;

	public String getCommand() {
		return command + alias;
	}

	protected void addAlias(String newAlias) {
		alias.add(newAlias);
	}

	public boolean checkForMatch(String input) {
		if (command.equalsIgnoreCase(input)) {
			return true;
		} else if (getAllAllias().contains(input.toLowerCase()))
			return true;
		else
			return false;
	}

	public String getAllAllias() {
		if (alias.size() == 0)
			return "no known alias";
		else {
			StringBuffer strBf = new StringBuffer();

			for (String a : alias) {
				strBf.append(a);
			}

			return strBf.toString();
		}
	}
}
