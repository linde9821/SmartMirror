package smartMirror.Command.Commands;

import java.util.Scanner;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.File.LogHandler;
import smartMirror.widget.TextWidget;
import smartMirror.widget.WidgetHandler;

public class AddTextwidgetCommand extends AddWidgetCommand {

	public AddTextwidgetCommand(WidgetHandler wh) {
		super(wh);	
		this.command = "add textwidget";
	}
	
	@Override
	public void runCommand() throws SmartMirrorException {
		Scanner scanner = new Scanner(System.in);
		String input;
		
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

}
