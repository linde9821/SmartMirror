/**
* @author  Moritz Lindner
* @version 0.1
* @since 15.05.2019 
*/

package smartMirror.Command.Commands;

import java.util.Scanner;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.Manager.SmartMirrorManager;

public class yDimCommand extends ChangeSettingCommand {

	public yDimCommand() {
		this.command = "change yDim";
	}

	@Override
	public void runCommand() throws SmartMirrorException {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		System.out.print("value: ");
		input = scanner.nextLine();
		
		SmartMirrorManager.changeyDim(Integer.parseInt(input));
	}
}
