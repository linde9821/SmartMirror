/**
* @author  Moritz Lindner
* @version 0.2.0
* @since 15.05.2019 
*/


package smartMirror.Widget;

import java.awt.Color;
import java.awt.Graphics;

import smartMirror.Location.Area;

public abstract class Widget implements Runnable{
	
	protected Area area;
	
	public Widget(Area area) {
		this.area = area;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(area.getxCoord(), area.getyCoord(), area.getWidth(), area.getHight());
	}
	
	abstract public void run();
	
	public Area getArea() {
		return area;
	}

}
