package smartMirror.Command.Commands;

import java.util.Scanner;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.widget.AdvancedClock;
import smartMirror.widget.WidgetHandler;

public class AddAdvancedClockCommand extends AddWidgetCommand {


	public AddAdvancedClockCommand(WidgetHandler wh) {
		super(wh);
		this.command = "add AddAdvancedClockWidget";
	}

	@Override
	public void runCommand() throws SmartMirrorException {
		Scanner scanner = new Scanner(System.in);
		String input;

		System.out.println("AddAdvancedClockWidget xCord: ");
		input = scanner.nextLine();
		int x = Integer.parseInt(input);

		System.out.println("AddAdvancedClockWidget yCord: ");
		input = scanner.nextLine();
		int y = Integer.parseInt(input);

		System.out.println("AddAdvancedClockWidget width: ");
		input = scanner.nextLine();
		int width = Integer.parseInt(input);

		System.out.println("AddAdvancedClockWidget height: ");
		input = scanner.nextLine();
		int height = Integer.parseInt(input);

		wh.addWidget(new AdvancedClock(x, y, width, height, wh.getPanel()));
	}

}
