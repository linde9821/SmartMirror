/**
* @author  Marvin Saße
* @version 0.1.0
* @since 15.05.2019 
*/

package smartMirror.Widget;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import smartMirror.DateAndTime.DateHandler;
import smartMirror.Location.Area;
import smartMirror.Panel.SmartMirrorPanel;

public class DateAndClockWidget extends Widget {

	DateHandler dh;
	SimpleDateFormat sdf;
	String timeNow;
	SmartMirrorPanel panel;
	
	public DateAndClockWidget(int x, int y, int width, int hight, SmartMirrorPanel panel) {
		super(new Area(x, y, width, hight));
		dh = new DateHandler();
		this.panel = panel;
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.WHITE);
		dh = null;
		dh = new DateHandler();
		g.drawString(dh.getFullDate(), area.getxCoord()+10, area.getyCoord()+30);
		g.drawString(getTime(), area.getxCoord()+10, area.getyCoord()+10 );
	}
	
	@Override
	public void run() {
		long lastUpdate = System.currentTimeMillis();
		while(true) {
			if(System.currentTimeMillis()-lastUpdate >= 1000) {
				update();
				lastUpdate = System.currentTimeMillis();
			}
		}
	}
	
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeNow = sdf.format(new Date());
		return timeNow;
	}
	
	private void update() {
		panel.repaint();
	}
}
