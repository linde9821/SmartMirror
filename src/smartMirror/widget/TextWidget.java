package smartMirror.widget;

import java.awt.Color;
import java.awt.Graphics;

import smartMirror.Location.Area;

public class TextWidget extends Widget{	
	
	String testText;
	

	public TextWidget(int x, int y, int width, int hight, String givenText) {
		super( new Area(x, y, width, hight));
		testText = givenText;
	}

	@Override
	public void run() {
		
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.WHITE);
		g.drawString(testText, area.getxCoord()+10, area.getyCoord()+20);
	}
		
	
}