package smartMirror.Command;

public abstract class Command {
	public Command(String command){
		this.command = command;
	}
	
	protected String command;
	
	abstract void runCommand();
}
