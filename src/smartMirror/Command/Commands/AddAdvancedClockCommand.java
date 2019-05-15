/**
* Command zu einem Widget welches eine Analoguhr, mit Stunden-, Minuten- und Sekundenzeiger, 
* entsprechend der, aktuellen Systemzeit, rendert.
* @author  Moritz Lindner
* @version 0.1.1
* @since 15.05.2019 
*/

package smartMirror.Command.Commands;

import java.io.IOException;
import java.util.Scanner;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.Widget.AdvancedClockWidget;
import smartMirror.Widget.WidgetHandler;

public class AddAdvancedClockCommand extends AddWidgetCommand {

	public AddAdvancedClockCommand(WidgetHandler wh) {
		super(wh);
		this.command = "add AddAdvancedClockWidget";
	}

	@Override
	/**
	 * Erfragt die benötigten Informationen zum hinzufügen des Widgets.
	 */
	public void runCommand() throws SmartMirrorException, IOException {
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

		wh.addWidget(new AdvancedClockWidget(x, y, width, height, wh.getPanel()));
	}

}
