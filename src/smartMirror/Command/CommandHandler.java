package smartMirror.Command;

import java.io.IOException;
import java.util.ArrayList;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.File.LogHandler;
import smartMirror.widget.WidgetHandler;

public class CommandHandler {
	private static ArrayList<Command> commandList;
	private WidgetHandler wh;
	
	public LogHandler log;

	public CommandHandler(WidgetHandler wh, LogHandler log) {
		this.wh = wh;
		this.log = log;
		commandList = new ArrayList<Command>();
		loadCommandList();
	}

	private void loadCommandList() {
		commandList.add(new AddWidgetCommand(wh, log));
		commandList.add(new HelpCommand());
	}

	public void command(String command) throws SmartMirrorException, IOException {
		boolean commandFound = false;
		for (Command c : commandList) {
			if (c.checkForMatch(command)) {
				commandFound = true;

				c.runCommand();
			}
		}
		
		

		if (!commandFound)
			throw new SmartMirrorException(command + " is a unknown command. Known Commands: " + getKnownCommands());
	}

	public void printKnownCommands() {
		System.out.println(getKnownCommands());
	}

	public static String getKnownCommands() {
		StringBuilder strBld = new StringBuilder();

		for (Command c : commandList) {
			strBld.append(c.getCommand() + "\n");
		}

		return strBld.toString();
	}
}
