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
		commandList.add(new AddTextWidgetCommand());
		
	}
	
	public void command(String command) throws SmartMirrorException {
		if (commandList.contains(command)) {
			if (command.equalsIgnoreCase(commandList.get(0)));{
				
			}
		}else
			throw new SmartMirrorException("Unknown command");
	}
}
