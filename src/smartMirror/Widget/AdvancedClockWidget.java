/**
* Widget welches eine Analoguhr, mit Stunden-, Minuten- und Sekundenzeiger, 
* entsprechend der, aktuellen Systemzeit, rendert.
* @author  Moritz Lindner
* @version 1.0.0
* @since 15.05.2019 
*/

package smartMirror.Widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import javax.imageio.ImageIO;

import smartMirror.Location.Area;
import smartMirror.Panel.SmartMirrorPanel;

public class AdvancedClockWidget extends Widget {
	private static final long UPDATETIME = 16;
	private SmartMirrorPanel panel;

	private double sekunden, minuten, stunden;
	private int mx, my;

	private LocalTime now;

	private Rectangle2D sekundenzeiger, minutenzeiger, stundenzeiger;

	private BufferedImage img;

	public AdvancedClockWidget(int x, int y, int width, int hight, SmartMirrorPanel panel) {
		super(new Area(x, y, width, hight));
		this.panel = panel;

		update();

		stundenzeiger = new Rectangle2D.Float(mx, my - 0.025f * area.getWidth() / 2, area.getWidth() / 4,
				0.025f * area.getWidth());
		minutenzeiger = new Rectangle2D.Float(mx, my - 3.5f, 0.35f * area.getWidth(), 7);
		sekundenzeiger = new Rectangle2D.Float(mx + 0.45f * area.getWidth(), my - 3, 0.0175f * area.getWidth(),
				0.015f * area.getHight());

		img = null;

		try {
			img = ImageIO.read(new File("res" + File.separator + "clock.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		mx = area.getxCoord() + area.getWidth() / 2;
		my = area.getyCoord() + area.getHight() / 2;

		now = LocalTime.now();
		sekunden = ((double) now.getNano() / 1000000000d) + now.getSecond();
		minuten = now.getMinute() + sekunden / 60;
		stunden = now.getHour();

		if (stunden >= 12)
			stunden -= 12;

		stunden += minuten / 60;

		panel.repaint();
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.drawImage(img, area.getxCoord(), area.getyCoord(), area.getWidth(), area.getHight(), null);
		drawH(g2d);
		drawS(g2d);
		drawM(g2d);
		g2d.setPaint(Color.WHITE);
		// g2d.drawArc(mx - 10, my - 10,(int) (0.05f * area.getWidth()),(int) (0.05f *
		// area.getWidth()), 0, 360);
		g2d.fillArc(mx - 10, my - 10, (int) (0.05f * area.getWidth()), (int) (0.05f * area.getWidth()), 0, 360);
	}

	private void drawH(Graphics2D g2d) {
		double pos = stunden / (12d);

		pos *= 360;
		pos -= 90;

		g2d.rotate(Math.toRadians(pos), area.getxCoord() + area.getWidth() / 2, area.getyCoord() + area.getHight() / 2);

		g2d.setPaint(Color.WHITE);
		g2d.fill(stundenzeiger);
		// g2d.draw(stundenzeiger);

		g2d.rotate(-Math.toRadians(pos), area.getxCoord() + area.getWidth() / 2,
				area.getyCoord() + area.getHight() / 2);
	}

	private void drawM(Graphics2D g2d) {
		double pos = minuten / (60d);

		pos *= 360;
		pos -= 90;

		g2d.rotate(Math.toRadians(pos), area.getxCoord() + area.getWidth() / 2, area.getyCoord() + area.getHight() / 2);

		g2d.setPaint(Color.WHITE);
		g2d.fill(minutenzeiger);
		// g2d.draw(minutenzeiger);

		g2d.rotate(-Math.toRadians(pos), area.getxCoord() + area.getWidth() / 2,
				area.getyCoord() + area.getHight() / 2);
	}

	private void drawS(Graphics2D g2d) {
		double pos = sekunden / (60);

		pos *= 360;
		pos -= 90;

		g2d.rotate(Math.toRadians(pos), area.getxCoord() + area.getWidth() / 2, area.getyCoord() + area.getHight() / 2);

		g2d.setPaint(Color.RED);
		g2d.fill(sekundenzeiger);
		// g2d.draw(sekundenzeiger);

		g2d.rotate(-Math.toRadians(pos), area.getxCoord() + area.getWidth() / 2,
				area.getyCoord() + area.getHight() / 2);
	}
}
