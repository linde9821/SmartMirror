package smartMirror.widget;

import java.awt.Color;
import java.awt.Graphics;
import java.time.LocalTime;

import smartMirror.DateAndTime.DateHandler;
import smartMirror.Location.Area;

public class DateAndClockWidget extends Widget {

	DateHandler dh;
	
	public DateAndClockWidget(int x, int y, int width, int hight) {
		super(new Area(x, y, width, hight));
		dh = new DateHandler();
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.WHITE);
		g.drawString(dh.getFullDate(), area.getxCoord()+10, area.getyCoord()+30);
		g.drawString(LocalTime.now().toString(), area.getxCoord()+10, area.getyCoord()+10 );
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
