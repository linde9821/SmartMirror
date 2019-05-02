package smartMirror.Command.Commands;

import java.util.Scanner;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.SMManager.SMManager;

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
		
		SMManager.changeyDim(Integer.parseInt(input));
	}
}
