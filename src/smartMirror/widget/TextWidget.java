package smartMirror.widget;

import java.awt.Color;
import java.awt.Graphics;

import smartMirror.Location.Area;

public class TextWidget extends Widget{	
	
	String testText;
	Area widgetArea = new Area();
	
	public TextWidget() {
		
	}

	public TextWidget(int x, int y, int width, int hight, String givenText) {
		Area widgetArea = new Area(x, y, width, hight);
		testText = givenText;
	}

	@Override
	public void run() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(widgetArea.getxCoord(), widgetArea.getyCoord(), widgetArea.getWidth(), widgetArea.getHigth());
		g.drawString(testText, 10, 10);
	}
		
	
}