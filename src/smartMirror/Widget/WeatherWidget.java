/**
* Widget, welches das aktuelle Wetter von openweathermap.org holt und anzeigt
* @author  Marvin Saﬂe
* @version 0.1.0
* @since 15.05.2019 
*/


package smartMirror.Widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
//import com.google.gson.reflect.*;
import com.google.gson.reflect.TypeToken;

import smartMirror.Location.Area;
import smartMirror.Panel.SmartMirrorPanel;

public class WeatherWidget extends Widget{

	SmartMirrorPanel panel;
	String API_KEY = "606c4ddff406d41d6beb47980236cc96";
	String LOCATION = "Berlin,de";
	String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY
			+ "&units=metric";
	String temperature = "";
	String humidity = "";
	String windSpeed = "";
	String deg = "";
	String sky = "";
	
	BufferedImage sunImage;
	BufferedImage tempImage;
	BufferedImage humidityImage;
	
	public WeatherWidget(int x, int y, int width, int hight, SmartMirrorPanel panel) {
		super(new Area(x, y, width, hight));
		this.panel = panel;
		getWeatherIcons();
	}
	
	
	public void getWeatherInfos() {

		try {
			StringBuilder result = new StringBuilder();
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
			//gibt die gesamte json datei aus
			System.out.print("Das reslut: " + result.toString());

			Map<String, Object> respMap = jsonToMap(result.toString());
 			//Map<String, Object> weatherMap = jsonToMap(respMap.get("weather").toString());
			Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
			Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

			//sky = "" + weatherMap.get("description");
			temperature = "Aktuelle Temperatur: " +  mainMap.get("temp") + "∞C";
			humidity =  "Aktuelle Luftfeuchtigkeit: " + mainMap.get("humidity") + "%";
			windSpeed = "Atuelle Windgeschwindigkeit: " + windMap.get("speed");
			deg = "Aktuelles irgendwas: " + windMap.get("deg");
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
	}

	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
		return map;
	}
	
	public void render(Graphics g) {
		super.render(g);
		ImageObserver observer = null;
		g.setColor(Color.WHITE);
		g.setFont(new Font("Impact", Font.BOLD, 20));
		getWeatherInfos();
		g.drawImage(tempImage, area.getxCoord(), area.getyCoord() + 5, tempImage.getWidth(), tempImage.getHeight(), observer);
		g.drawString(temperature, area.getxCoord() + tempImage.getWidth() + 5, area.getyCoord() + (tempImage.getHeight() - 15));
		g.drawImage(humidityImage, area.getxCoord(), area.getyCoord() + tempImage.getHeight() + 15, humidityImage.getWidth(), humidityImage.getHeight(), observer);
		g.drawString(humidity, area.getxCoord() + humidityImage.getWidth(), area.getyCoord() + (humidityImage.getHeight() * 2  ));
		//g.drawString(windSpeed, area.getxCoord(), area.getyCoord()+60);
		//g.drawString(deg, area.getxCoord(), area.getyCoord()+80);
		//g.drawImage(sunImage, area.getxCoord(), area.getyCoord()-50, sunImage.getWidth(), sunImage.getHeight(), observer);
	}
	
	@Override
	public void run() {
		long lastUpdate = System.currentTimeMillis();
		while(true) {
			if(System.currentTimeMillis()-lastUpdate >= 900000) {
				update();
				lastUpdate = System.currentTimeMillis();
			}
		}
	}
	
	private void update() {
		panel.repaint();
	}
	
	private void getWeatherIcons() {
		try {
			sunImage = ImageIO.read(new File(getImageFile() + "\\sun.png"));
			tempImage = ImageIO.read(new File(getImageFile() + "\\temp.png"));
			humidityImage = ImageIO.read(new File(getImageFile() + "\\humidity.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private  String getImageFile() {
		String filename = "weatherimg" + File.separator;
		File imageFile = new File(filename);
		return imageFile.toString();
	}

}
