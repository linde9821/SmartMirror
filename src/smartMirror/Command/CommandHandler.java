package smartMirror.Command;

import java.util.ArrayList;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.widget.WidgetHandler;

public class CommandHandler {
	private ArrayList<Command> commandList;
	private WidgetHandler wh;

	public CommandHandler(WidgetHandler wh) {
		this.wh = wh;
		commandList = new ArrayList<Command>();
		loadCommandList();
	}

	private void loadCommandList() {
		commandList.add(new AddWidgetCommand(wh));

	}

	public void command(String command) throws SmartMirrorException {
		boolean commandFound = false;
		for (Command c : commandList) {
			commandFound = true;

			c.runCommand();
		}

		if (!commandFound)
			throw new SmartMirrorException(command + " is a unknown command. Known Commands: " + getKnownCommands());
	}

	public void printKnownCommands() {
		System.out.println(getKnownCommands());
	}

	public String getKnownCommands() {
		StringBuilder strBld = new StringBuilder();

		for (Command c : commandList) {
			strBld.append(c.getCommand() + ",");
		}

		return strBld.toString();
	}
}
