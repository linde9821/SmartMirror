/**
* Widget, welches die aktuellen Nachrichten von newsapi.org holt und anzeigt
* @author  Marvin Saße
* @version 0.1.0
* @since 24.05.2019 
*/


package smartMirror.Widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import smartMirror.Location.Area;
import smartMirror.Panel.SmartMirrorPanel;

public class NewsWidget extends Widget{

	SmartMirrorPanel panel;
	
	String API_KEY = "5a1bdd8f37214f0897aa23fc2b1f6f7d";
	String COUNTRY = "de";
	String CATEGORY = "technology";
	String urlString = "https://newsapi.org/v2/top-headlines?country=" + COUNTRY + "&category=" + CATEGORY + "&apiKey=" + API_KEY;
	
	String firstTitle = "";
	String second = "";
	String third = "";
	String fourth = "";
	String fifth = "";
	
	
	public NewsWidget(int x, int y, int width, int hight, SmartMirrorPanel panel) {
		super(new Area(x, y, width, hight));
		this.panel = panel;
		getNews();
	}
	
	@SuppressWarnings("resource")
	public void getNews() {

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
                JsonElement articles = jsonObject.get("articles");
                //JsonElement main = jsonObject.get("main");

                //if (main.isJsonObject()) {
                	//JsonObject main_infos = main.getAsJsonObject();
                	
               // }
                
                if (articles.isJsonArray()) {
                    JsonArray temp = articles.getAsJsonArray();
                    JsonObject firstArticle = temp.get(0).getAsJsonObject();
                    firstTitle = firstArticle.get("title").toString();

                    JsonObject secondArticle = temp.get(1).getAsJsonObject();
                    second = secondArticle.get("title").toString();
                    
                    JsonObject thirdArticle = temp.get(2).getAsJsonObject();
                    third = thirdArticle.get("title").toString();
                    
                    JsonObject fourthArticle = temp.get(3).getAsJsonObject();
                    fourth = fourthArticle.get("title").toString();
                    
                    JsonObject fifthArticle = temp.get(4).getAsJsonObject();
                    fifth = fifthArticle.get("title").toString();
                }
            }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.PLAIN, 14));
		g.drawString(firstTitle, area.getxCoord(), area.getyCoord() + 50);
		g.drawString(second, area.getxCoord(), area.getyCoord() + 70);
		g.drawString(third, area.getxCoord(), area.getyCoord() + 90);
		g.drawString(fourth, area.getxCoord(), area.getyCoord() + 110);
		g.drawString(fifth, area.getxCoord(), area.getyCoord() + 130);
	}
	
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

}
