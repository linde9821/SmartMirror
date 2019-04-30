package smartMirror.widget;

import java.awt.Graphics;

import smartMirror.Location.Area;

public abstract class Widget implements Runnable{
	
	private Area area;
	
	public Widget() {
		
	}
	
	abstract public void render(Graphics g);
	
	abstract public void run();
	
	public Area getArea() {
		return area;
	}

}
