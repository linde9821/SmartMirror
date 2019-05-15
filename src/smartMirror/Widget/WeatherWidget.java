/**
* Widget, welches das aktuelle Wetter von openweathermap.org holt und anzeigt
* @author  Marvin Saﬂe
* @version 0.1.0
* @since 15.05.2019 
*/


package smartMirror.Widget;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
//import com.google.gson.reflect.*;
import com.google.gson.reflect.TypeToken;

import smartMirror.Location.Area;
import smartMirror.Panel.SmartMirrorPanel;

public class WeatherWidget extends Widget{

	//Gson g = new Gson();
	
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
	
	public WeatherWidget(int x, int y, int width, int hight, SmartMirrorPanel panel) {
		super(new Area(x, y, width, hight));
		this.panel = panel;
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
			System.out.print("Das reslut: " + result.toString());

			Map<String, Object> respMap = jsonToMap(result.toString());
 			Map<String, Object> weatherMap = jsonToMap(respMap.get("coord").toString());
			Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
			Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

			//sky = "" + weatherMap.get("description");
			temperature = "Aktuelle Temperatur: " +  mainMap.get("temp");
			humidity =  "Aktuelle Luftfeuchtigkeit: " + mainMap.get("humidity");
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
		g.setColor(Color.WHITE);
		getWeatherInfos();
		//g.drawString(sky, area.getxCoord(), area.getyCoord());
		g.drawString(temperature, area.getxCoord(), area.getyCoord()+20);
		g.drawString(humidity, area.getxCoord(), area.getyCoord()+40);
		g.drawString(windSpeed, area.getxCoord(), area.getyCoord()+60);
		g.drawString(deg, area.getxCoord(), area.getyCoord()+80);
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
	
	public String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeNow = sdf.format(new Date());
		return timeNow;
	}
	
	private void update() {
		panel.repaint();
	}

}
