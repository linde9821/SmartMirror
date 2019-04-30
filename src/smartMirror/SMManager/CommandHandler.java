package smartMirror.SMManager;

import java.util.ArrayList;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.widget.WidgetHandler;

public class CommandHandler {
	private ArrayList<String> commandList = new ArrayList<String>();
	private WidgetHandler wh;
	
	public CommandHandler(WidgetHandler wh) {
		this.wh = wh;
		loadCommandList();
	}
	
	private void loadCommandList() {
		commandList.add("add widget");
		
	}
	
	public void command(String command) throws SmartMirrorException {
		if (commandList.contains(command)) {
			if (command.equalsIgnoreCase(commandList.get(0)));{
				
			}
		}else
			throw new SmartMirrorException("Unknown command");
	}
}
