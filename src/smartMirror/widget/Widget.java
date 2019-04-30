package smartMirror.widget;


public abstract class Widget implements Runnable{
	
	public Widget() {
		
	}
	
	abstract public void Delete();
	
	abstract public void  Add();
	
	abstract public void run();

}
