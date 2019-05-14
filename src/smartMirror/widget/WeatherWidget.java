package smartMirror.widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import smartMirror.DateAndTime.DateHandler;
import smartMirror.Location.Area;
import smartMirror.SMPanel.SMPanel;
import smartMirror.Weather.WeatherDisplay;
import smartMirror.Weather.WeatherDoc;

public class WeatherWidget extends Widget {

	DateHandler dh;
	SMPanel panel;
	// String city;

	// 2345496 code für Berlin
	// c für metrisches system
	/**WeatherDoc doc = new WeatherDoc("2345496", "c");
	WeatherDisplay disp = new WeatherDisplay();**/

	public WeatherWidget(int x, int y, int width, int hight, SMPanel panel) {
		super(new Area(x, y, width, hight));
		dh = new DateHandler();
		this.panel = panel;
		// this.city = city;
	}

	public void render(Graphics g) {
		super.render(g);
		/**g.setColor(Color.WHITE);
		// schreibt Temperatur
		Image tmpImage;
		try {
			tmpImage = ImageIO.read(new File(".\\img\\weather\\temperature-2-16.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("Bild nicht gefunden!");
		}
		g.drawString(getTempString(), area.getxCoord() + 20, area.getyCoord());
		g.drawString(getSunriseString(), area.getxCoord(), area.getyCoord() + 20);
		g.drawString(getSunsetString(), area.getxCoord(), area.getyCoord() + 40);
		g.drawString(getHeavenCondition(), area.getxCoord(), area.getyCoord() + 60);
		**/

	}

	@Override
	public void run() {
		long lastUpdate = System.currentTimeMillis();
		while (true) {
			if (System.currentTimeMillis() - lastUpdate >= 900000) {
				update();
				lastUpdate = System.currentTimeMillis();
			}
		}
	}

	private void update() {
		panel.repaint();
	}

	/**private String getTempString() {
		String tempString = "Temperatur " + disp.getCity() + "°";
		return tempString;
	}

	private String getSunsetString() {
		String sunsetString = "Sonnenuntergang um " + disp.getSunset();
		return sunsetString;
	}

	private String getSunriseString() {
		String sunriseString = "Sonnenaufgang um " + disp.getSunrise();
		return sunriseString;
	}

	private String getHeavenCondition() {
		String heavenConditionString = disp.getCondition();

		// weitere fälle ergänzen
		// ich weiß aber den genauen wortlaut noch nicht

		if (heavenConditionString != null) {
			switch (heavenConditionString) {
			case "Mostly Cloudy":
				heavenConditionString = "Leicht Bewölkt";
				break;
			case "Rain":
				heavenConditionString = "Regen";
				break;
			case "Mostly Clear":
				heavenConditionString = "Weitestgehend Klar";
				break;
			case "Showers":
				heavenConditionString = "Schauer";
				break;
			case "Mostly Sunny":
				heavenConditionString = "Meist Sonnig";
				break;
			case "Sunny":
				heavenConditionString = "Sonnig";
				break;
			case "Partly Cloudy":
				heavenConditionString = "Teilweise Bewölkt";
				break;
			case "Clear":
				heavenConditionString = "Klar";
				break;
			case "Breezy":
				heavenConditionString = "Windig";
				break;
			default:
				heavenConditionString = "undefiniert";
			}
		}else {
			heavenConditionString = "undefiniert";
		}
		return heavenConditionString;
	}**/

}
