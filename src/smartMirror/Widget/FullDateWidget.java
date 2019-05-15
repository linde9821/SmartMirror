/**
* @author  Marvin Saße
* @version 0.1.0
* @since 15.05.2019 
*/

package smartMirror.Widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;

import smartMirror.DateAndTime.DateHandler;
import smartMirror.Location.Area;
import smartMirror.Panel.SmartMirrorPanel;

public class FullDateWidget extends Widget{

	DateHandler dh;
	SimpleDateFormat sdf;
	SmartMirrorPanel panel;
	
	public FullDateWidget(int x, int y, int width, int hight, SmartMirrorPanel panel) {
		super(new Area(x, y, width, hight));
		dh = new DateHandler();
		this.panel = panel;
	}

	public void rernder(Graphics g) {
		super.render(g);
		Font newFont = new Font("Serif", Font.BOLD, 20);
		g.setFont(newFont);
		g.setColor(Color.WHITE);
		dh = null;
		dh = new DateHandler();
		g.drawString(dh.getFullDate(),area.getxCoord(), area.getyCoord());
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
	
	private void update() {
		panel.repaint();
	}

}
