/**
* Widget welches eine Digitaluhr, mit Stunden-, Minuten- und Sekunden, 
* entsprechend der, aktuellen Systemzeit, rendert.
* 
* @author  Moritz Lindner
* @version 0.1
* @since 17.05.2019 
* 
*/

package smartMirror.Widget;

import java.awt.Graphics;
import java.time.LocalTime;

import smartMirror.Panel.SmartMirrorPanel;

public class AdvancedDigitalClock extends AdvancedClockWidget {
	private int minuten, stunden;
	private double sekunden;

	public AdvancedDigitalClock(int x, int y, int width, int hight, SmartMirrorPanel panel) {
		super(x, y, width, hight, panel);

		update();
	}

	@Override
	public void run() {
		long lastUpdate = System.currentTimeMillis();

		while (true) {
			if (System.currentTimeMillis() - lastUpdate > UPDATETIME) {
				update();
				lastUpdate = System.currentTimeMillis();
			}
		}
	}

	private void update() {
		now = LocalTime.now();
		sekunden = ((double) now.getNano() / 1000000000d) + now.getSecond();
		minuten = now.getMinute();
		stunden = now.getHour();

		panel.repaint();
	}

	public void render(Graphics g) {
		String stu = Integer.toString(stunden);
		
		if (Math.round(stunden) < 10) {
			stu = "0" + stu;
		}else if (Math.round(stunden) == 24) {
			stu = "00";
		}
		
		SevenSegment s1 = new SevenSegment(area.getxCoord(), area.getyCoord(), 10, 10);
		s1.setSegment(stu.charAt(0));
		s1.render(g);

		SevenSegment s2 = new SevenSegment(area.getxCoord() + 5, area.getyCoord() + 0, 10, 10);
		s2.setSegment(stu.charAt(1));
		s2.render(g);
		 
		
		if (sekunden == 60)
			minuten+=2;
		
			String min = Integer.toString((int) Math.round(minuten));
		if (Math.round(minuten) < 10) {
			min = "0" + min;
		}else if (Math.round(minuten) == 60) {
			min = "00";
		}
		
		SevenSegment m1 = new SevenSegment(area.getxCoord() + 11, area.getyCoord() + 0, 10, 10);
		m1.setSegment(min.charAt(0));
		m1.render(g);

		SevenSegment m2 = new SevenSegment(area.getxCoord() + 16, area.getyCoord() + 0, 10, 10);
		m2.setSegment(min.charAt(1));
		m2.render(g);

		String sek = Integer.toString((int) Math.round(sekunden));
		
		if (Math.round(sekunden) < 10) {
			sek = "0" + sek;
		}else if (Math.round(sekunden) == 60) {
			sek = "00";
		}

		SevenSegment se1 = new SevenSegment(area.getxCoord() + 22, area.getyCoord() + 0, 10, 10);
		se1.setSegment(sek.charAt(0));
		se1.render(g);

		SevenSegment se2 = new SevenSegment(area.getxCoord() + 27, area.getyCoord() + 0, 10, 10);
		se2.setSegment(sek.charAt(1));
		se2.render(g);
	}

}
