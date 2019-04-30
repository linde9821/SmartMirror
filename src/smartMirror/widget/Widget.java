package smartMirror.widget;

import java.awt.Graphics;

public abstract class Widget implements Runnable{
	
	public Widget() {
		
	}
	
	abstract public void render(Graphics g);
	
	abstract public void run();

}
