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

	abstract public void runCommand() throws SmartMirrorException;

	public String getCommand() {
		return command;
	}
	
	protected void addAlias(String newAlias) {
		alias.add(newAlias);
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
