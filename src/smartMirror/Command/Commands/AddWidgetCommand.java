package smartMirror.Command.Commands;

import java.io.IOException;
import java.util.Scanner;

import smartMirror.Command.Command;
import smartMirror.Exception.SmartMirrorException;
import smartMirror.widget.WidgetHandler;

public class AddWidgetCommand extends Command {
	public static final String TEXTWIDGET = "text widget";
	public static final String DATEANDCLOCKWIDGET = "dateAndClock widget";

	protected WidgetHandler wh;

	public AddWidgetCommand(WidgetHandler wh) {
		super("add widget");
		super.addAlias("add");
		this.wh = wh;
	}

	@Override
	public void runCommand() throws SmartMirrorException, IOException {
		System.out.println("what kind of widget: ");
		Scanner scanner = new Scanner(System.in);
		String input = "";

		input = scanner.nextLine();

		switch (input) {
		case (TEXTWIDGET): {
			new AddTextwidgetCommand(wh).runCommand();
		}
			break;

		case (DATEANDCLOCKWIDGET): {
			new AddDateAndClockWidgetCommand(wh).runCommand();
		}
			break;

		default: {
			throw new SmartMirrorException("Unknown Widget");

		}
		}
	}


}
