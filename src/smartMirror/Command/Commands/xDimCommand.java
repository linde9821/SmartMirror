package smartMirror.Command.Commands;

import java.util.Scanner;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.SMManager.SMManager;

public class xDimCommand extends ChangeSettingCommand {

	public xDimCommand() {
		this.command = "change xDim";
	}

	@Override
	public void runCommand() throws SmartMirrorException {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		System.out.print("value: ");
		input = scanner.nextLine();
		
		SMManager.changexDim(Integer.parseInt(input));
	}
}
