/**
 * Steuert die Verwendung von Commands zur Laufzeit
* @author  Moritz Lindner
* @version 0.1.1
* @since 15.05.2019 
*/

package smartMirror.Command;

import java.io.IOException;
import java.util.ArrayList;

import smartMirror.Command.Commands.AddAdvancedClockCommand;
import smartMirror.Command.Commands.AddDateAndClockWidgetCommand;
import smartMirror.Command.Commands.AddTextwidgetCommand;
import smartMirror.Command.Commands.AddWidgetCommand;
import smartMirror.Command.Commands.ChangeSettingCommand;
import smartMirror.Command.Commands.ExitCommand;
import smartMirror.Command.Commands.HelpCommand;
import smartMirror.Command.Commands.xDimCommand;
import smartMirror.Command.Commands.yDimCommand;
import smartMirror.Exception.SmartMirrorException;
import smartMirror.Widget.WidgetHandler;

public class CommandHandler {
	private static ArrayList<Command> commandList;
	private WidgetHandler wh;

	public CommandHandler(WidgetHandler wh) {
		this.wh = wh;
		commandList = new ArrayList<Command>();
		loadCommandList();
	}

	private void loadCommandList() {
		commandList.add(new AddWidgetCommand(wh));
		commandList.add(new AddTextwidgetCommand(wh));
		commandList.add(new AddDateAndClockWidgetCommand(wh));
		commandList.add(new AddAdvancedClockCommand(wh));

		commandList.add(new ChangeSettingCommand());
		commandList.add(new xDimCommand());
		commandList.add(new yDimCommand());

		commandList.add(new ExitCommand());
		commandList.add(new HelpCommand());
	}

	/**
	 * Untersucht ob der Übergebene String einen Command entspricht und führt es
	 * ggf. aus. Sonst wirft es eine SmartMirrorException.
	 * 
	 * @param command String welcher überprüft werden soll ob er einem Command
	 *                entspricht
	 * @throws SmartMirrorException
	 * @throws IOException
	 */
	public void command(String command) throws SmartMirrorException, IOException {
		boolean commandFound = false;
		
		for (Command c : commandList) {
			if (c.checkForMatch(command)) {
				commandFound = true;

				c.runCommand();
			}
		}

		if (!commandFound)
			throw new SmartMirrorException(
					"<" + command + "> is a unknown command. Known Commands:\n" + getKnownCommands());
	}

	/**
	 * Gibt alle bekannten Commands aus
	 */
	public void printKnownCommands() {
		System.out.println(getKnownCommands());
	}

	/**
	 * Liefert String welcher alle bekannten Commands und deren Alias enthält.
	 * @return String mit allen Commands und deren Alias.
	 */
	public static String getKnownCommands() {
		StringBuilder strBld = new StringBuilder();

		for (Command c : commandList) {
			strBld.append(c.getCommand() + "\n");
		}

		return strBld.toString();
	}
}
