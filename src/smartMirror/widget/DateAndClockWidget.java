package smartMirror.widget;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Date;

import smartMirror.DateAndTime.DateHandler;
import smartMirror.Location.Area;
import smartMirror.SMPanel.SMPanel;

public class DateAndClockWidget extends Widget {

	DateHandler dh;
	SimpleDateFormat sdf;
	String timeNow;
	SMPanel panel;
	
	public DateAndClockWidget(int x, int y, int width, int hight, SMPanel panel) {
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
