package smartMirror.Command.Commands;

import java.util.Scanner;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.widget.DateAndClockWidget;
import smartMirror.widget.WidgetHandler;

public class AddDateAndClockWidgetCommand extends AddWidgetCommand {

	public AddDateAndClockWidgetCommand(WidgetHandler wh) {
		super(wh);
		this.command = "add dateandclockwidget";
	}
	
	@Override
	public void runCommand() throws SmartMirrorException {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		System.out.println("Textwidget xCord: ");
		input = scanner.nextLine();
		int x = Integer.parseInt(input);

		System.out.println("Textwidget yCord: ");
		input = scanner.nextLine();
		int y = Integer.parseInt(input);

		System.out.println("Textwidget width: ");
		input = scanner.nextLine();
		int width = Integer.parseInt(input);

		System.out.println("Textwidget height: ");
		input = scanner.nextLine();
		int height = Integer.parseInt(input);

		wh.addWidget(new DateAndClockWidget(x, y, width, height, wh.getPanel()));
	}

}
