package smartMirror.Command.Commands;

import java.util.Scanner;

import smartMirror.Command.Command;
import smartMirror.Exception.SmartMirrorException;

public class ChangeSettingCommand extends Command {
	public static final String xDim = "xDim";
	public static final String yDim = "yDim";

	public ChangeSettingCommand() {
		super("change setting");
	}

	@Override
	public void runCommand() throws SmartMirrorException {
		System.out.println("what kind of setting: ");
		Scanner scanner = new Scanner(System.in);
		String input = "";
		
		switch (input) {
		case (xDim): {
			new xDimCommand().runCommand();
		}
			break;

		case (yDim): {
			new yDimCommand().runCommand();
		}
			break;

		default: {
			throw new SmartMirrorException("Unknown Setting");
		}
		}
	}

}
