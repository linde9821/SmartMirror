/**
* Widget, welches das aktuelle Wetter von openweathermap.org holt und anzeigt
* @author  Marvin Saße
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

import javax.imageio.ImageIO;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import smartMirror.File.LogHandler;
import smartMirror.Location.Area;
import smartMirror.Panel.SmartMirrorPanel;

public class WeatherWidget extends Widget{

	SmartMirrorPanel panel;
	String API_KEY = "606c4ddff406d41d6beb47980236cc96";
	String LOCATION = "Berlin,de";
	String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY
			+ "&units=metric";
	
	String temperature = "";
	String temp_min = "";
	String temp_max = "";
	String humidity = "";
	String pressure = "";
	String sky = "";
	
	BufferedImage image;
	
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
			//gibt die gesamte json datei aus
			//System.out.print(result.toString());


			JsonParser parser = new JsonParser();
            String weatherString = result.toString();
            JsonElement jsonTree = parser.parse(weatherString);

            if (jsonTree.isJsonObject()) {
                JsonObject jsonObject = jsonTree.getAsJsonObject();

                JsonElement weather = jsonObject.get("weather");
                JsonElement main = jsonObject.get("main");

                if (main.isJsonObject()) {
                	JsonObject main_infos = main.getAsJsonObject();
                	temperature = main_infos.get("temp").toString() + "°C";
                	temp_min = "Tiefsttemperatur: " + main_infos.get("temp_min").toString() + "°C";
                	temp_max = "Höchsttemperatur: " + main_infos.get("temp_max").toString() + "°C";
                	humidity = "Luftfeuchtigkeit: " + main_infos.get("humidity") +  "%";
                	pressure = "Druck: " + main_infos.get("pressure") + " hPa";
                }
                
                if (weather.isJsonArray()) {
                    JsonArray temp = weather.getAsJsonArray();
                    JsonObject entry = temp.get(0).getAsJsonObject();
            
                    sky = entry.get("description").getAsString();
                }
            }
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		ImageObserver observer = null;
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.BOLD, 30));
		getWeatherInfos();
		try {
			getWeatherImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(image, area.getxCoord() + 2, area.getyCoord(),image.getWidth(), image.getHeight(), observer);
		g.drawString(temperature, area.getxCoord() + image.getWidth() + 10, area.getyCoord() + 40);
		g.setFont(new Font("SansSerif", Font.PLAIN, 14));
		g.drawString(temp_min, area.getxCoord() + 2, area.getyCoord() + image.getHeight() + 25);
		g.drawString(temp_max, area.getxCoord() + 2, area.getyCoord() + image.getHeight() + 45);
		g.setFont(new Font("SansSerif", Font.PLAIN, 19));
		g.drawString(humidity, area.getxCoord() + 2, area.getyCoord() + image.getHeight() + 75);
		g.drawString(pressure, area.getxCoord() + 2, area.getyCoord() + image.getHeight() + 100);
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
	
	private  String getImageFile() {
		String filename = "weatherimg" + File.separator;
		File imageFile = new File(filename);
		return imageFile.toString();
	}
	
	public void getWeatherImage() throws IOException {
		
		System.out.println(sky);
		
		// gegebenenfalls muss noch für jede discription alles angepasst werden siehe folgenden link:
		// https://openweathermap.org/weather-conditions
		
		switch(sky) {
		case "few clouds":
			image = ImageIO.read(new File(getImageFile() + "\\fewclouds.png"));;
			break;
		
		case "clear sky":
			image = ImageIO.read(new File(getImageFile() + "\\sun.png"));
			break;
			
		case "light rain":
			image = ImageIO.read(new File(getImageFile() + "\\rain.png"));
			break;
			
		case "scattered clouds":
			// anpassen an wolken
			image = ImageIO.read(new File(getImageFile() + "\\fewclouds.png"));;
			break;
		
		case "broken clouds":
			// ebenfalls nur als wolken handzuhaben
			image = ImageIO.read(new File(getImageFile() + "\\fewclouds.png"));
			break;
			
		case "shower rain":
			//schauer
			image = ImageIO.read(new File(getImageFile() + "\\rain.png"));
			break;
			
		case "thunderstorm":
			//gewitter
			image = ImageIO.read(new File(getImageFile() + "\\fewclouds.png"));;
			break;
		
		case "snow":
			//schnee
			image = ImageIO.read(new File(getImageFile() + "\\fewclouds.png"));
			break;
			
		case "mist":
			//nebel
			image = ImageIO.read(new File(getImageFile() + "\\rain.png"));
			break;
		
		default:
			LogHandler.addTextToLogFile(LogHandler.WEATHERERROR, "No weather solution!");
		}
		
	}

}

