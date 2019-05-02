package smartMirror.widget;

import java.awt.Color;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import smartMirror.Location.Area;
import smartMirror.SMPanel.SMPanel;

public class AdvancedClock extends Widget {
	private static final long UPDATETIME = 1000;
	private SMPanel panel;

	private int hour, minutes, seconds;

	public AdvancedClock(int x, int y, int width, int hight, SMPanel panel) {
		super(new Area(x, y, width, hight));
		this.panel = panel;
	}

	@Override
	public void run() {
		long lastUpdate = System.currentTimeMillis();
		update();

		while (true) {
			if (System.currentTimeMillis() - lastUpdate > UPDATETIME) {
				update();
				lastUpdate = System.currentTimeMillis();
			}
		}
	}

	private void update() {
		hour = LocalTime.now().getHour();

		if (hour >= 12)
			hour -= 12;

		minutes = LocalTime.now().getMinute();

		seconds = LocalTime.now().getSecond();

		panel.repaint();
	}

	public void render(Graphics g) {
		super.render(g);
		drawH(g);
		drawM(g);
		drawS(g);
	}

	private void drawH(Graphics g) {
		// h
		int x, y;
		final int r = 80;

		double teilwert = (hour * 60 + minutes);

		double pos = teilwert / (60 * 12);

		pos *= 360;

		pos = pos - 90;

		int mx = area.getxCoord() + area.getWidth() / 2;
		int my = area.getyCoord() + area.getHight() / 2;
		
		g.fillOval(mx - 5, my - 5, 10, 10);

		x = (int) (r * Math.cos(Math.toRadians(pos))) + mx;
		y = (int) (r * Math.sin(Math.toRadians(pos))) + my;

		g.drawLine(mx, my, x, y);
	}

	private void drawM(Graphics g) {
		// h
		int x, y;
		final int r = 100;

		double teilwert = (minutes);

		double pos = teilwert / (60);

		pos *= 360;

		pos = pos - 90;

		int mx = area.getxCoord() + area.getWidth() / 2;
		int my = area.getyCoord() + area.getHight() / 2;

		x = (int) (r * Math.cos(Math.toRadians(pos))) + mx;
		y = (int) (r * Math.sin(Math.toRadians(pos))) + my;

		g.drawLine(mx, my, x, y);
	}

	private void drawS(Graphics g) {
		// h
		int x, y;
		final int r = 95;

		double teilwert = (seconds);

		double pos = teilwert / (60);

		pos *= 360;

		pos = pos - 90;

		int mx = area.getxCoord() + area.getWidth() / 2;
		int my = area.getyCoord() + area.getHight() / 2;

		x = (int) (r * Math.cos(Math.toRadians(pos))) + mx;
		y = (int) (r * Math.sin(Math.toRadians(pos))) + my;

		g.drawLine(mx, my, x, y);
	}

}
