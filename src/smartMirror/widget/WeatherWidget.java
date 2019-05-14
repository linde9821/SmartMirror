package smartMirror.widget;

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
import com.google.gson.reflect.TypeToken;

import smartMirror.DateAndTime.DateHandler;
import smartMirror.Location.Area;
import smartMirror.SMPanel.SMPanel;

public class WeatherWidget extends Widget{

	SMPanel panel;
	
	public WeatherWidget(int x, int y, int width, int hight, SMPanel panel) {
		super(new Area(x, y, width, hight));
		this.panel = panel;
	}
	
	
	public void xyz() {

		String API_KEY = "606c4ddff406d41d6beb47980236cc96";
		String LOCATION = "Berlin,de";
		String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY
				+ "&units=metric";

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
			System.out.print(result);

			Map<String, Object> respMap = jsonToMap(result.toString());
			Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
			Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

			System.out.println("Aktuelle Temperatur: " + mainMap.get("temp"));
			System.out.println("Aktuelle Luftfeuchtigkeit: " + mainMap.get("humidity"));
			System.out.println("Aktuelle Windgeschwindigkeit: " + windMap.get("speed"));
			System.out.println("Aktuelle noch auszufüllem: " + windMap.get("deg"));

		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
	}

	public static Map<String, Object> jsonToMap(String str) {
		Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
		}.getType());
		return map;
	}
	
	public void render(Graphics g) {
		super.render(g);
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
