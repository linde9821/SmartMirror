package smartMirror.Command;

import java.io.IOException;
import java.util.Scanner;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.File.LogHandler;
import smartMirror.widget.DateAndClockWidget;
import smartMirror.widget.TextWidget;
import smartMirror.widget.WidgetHandler;

public class AddWidgetCommand extends Command {
	public static final String TEXTWIDGET = "text widget";
	public static final String DATEANDCLOCKWIDGET = "dateAndClock widget";

	private WidgetHandler wh;

	public AddWidgetCommand(WidgetHandler wh, LogHandler log) {
		super("add widget");
		this.wh = wh;
	}

	@Override
	public void runCommand() throws SmartMirrorException{
		System.out.println("what kind of widget: ");
		Scanner scanner = new Scanner(System.in);
		String input = "";
		

		input = scanner.nextLine();
		

		switch (input) {
		case (TEXTWIDGET): {
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

			System.out.println("Textwidget text: ");
			input = scanner.nextLine();

			wh.addWidget(new TextWidget(x, y, width, height, input));
		}
			break;

		case (DATEANDCLOCKWIDGET): {
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
			break;

		default: {
			scanner.close();
			throw new SmartMirrorException("Unknown Widget");

		}
		}
		scanner.close();
	}

}
