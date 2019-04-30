package smartMirror.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.widget.DateAndClockWidget;
import smartMirror.widget.TextWidget;
import smartMirror.widget.WidgetHandler;

public class AddWidgetCommand extends Command {
	public static final String TEXTWIDGET = "text widget";
	public static final String DATEANDCLOCKWIDGET = "dateAndClock widget";

	private WidgetHandler wh;

	public AddWidgetCommand(WidgetHandler wh) {
		super("add widget");
		this.wh = wh;
	}

	@Override
	public void runCommand() throws SmartMirrorException, IOException{
		System.out.println("what kind of widget: ");
		//Scanner scanner = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		
		//input = scanner.nextLine();
		input = reader.readLine();

		switch (input) {
		case (TEXTWIDGET): {
			System.out.println("Textwidget xCord: ");
			//input = scanner.nextLine();
			input = reader.readLine();
			int x = Integer.parseInt(input);

			System.out.println("Textwidget yCord: ");
			//input = scanner.nextLine();
			input = reader.readLine();
			int y = Integer.parseInt(input);

			System.out.println("Textwidget width: ");
			//input = scanner.nextLine();
			input = reader.readLine();
			int width = Integer.parseInt(input);

			System.out.println("Textwidget height: ");
			//input = scanner.nextLine();
			input = reader.readLine();
			int height = Integer.parseInt(input);

			System.out.println("Textwidget text: ");
			//input = scanner.nextLine();
			input = reader.readLine();

			wh.addWidget(new TextWidget(x, y, width, height, input));
		}
			break;

		case (DATEANDCLOCKWIDGET): {
			System.out.println("Textwidget xCord: ");
			//input = scanner.nextLine();
			input = reader.readLine();
			int x = Integer.parseInt(input);

			System.out.println("Textwidget yCord: ");
			//input = scanner.nextLine();
			input = reader.readLine();
			int y = Integer.parseInt(input);

			System.out.println("Textwidget width: ");
			//input = scanner.nextLine();
			input = reader.readLine();
			int width = Integer.parseInt(input);

			System.out.println("Textwidget height: ");
			//input = scanner.nextLine();
			input = reader.readLine();
			int height = Integer.parseInt(input);

			wh.addWidget(new DateAndClockWidget(x, y, width, height, wh.getPanel()));
		}
			break;

		default: {
			//scanner.close();
			reader.close();
			throw new SmartMirrorException("Unknown Widget");

		}
		}
		reader.close();
		//scanner.close();
	}

}
