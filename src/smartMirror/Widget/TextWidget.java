/**
* @author  Marvin Saﬂe
* @author Moritz Lindner
* @version 1.0.1
* @since 15.05.2019 
*/


package smartMirror.Widget;

import java.awt.Color;
import java.awt.Graphics;

import smartMirror.Location.Area;

public class TextWidget extends Widget {

	String testText;

	public TextWidget(int x, int y, int width, int hight, String givenText) {
		super(new Area(x, y, width, hight));
		testText = givenText;
	}

	@Override
	public void run() {

	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.WHITE);
		g.drawString(testText, area.getxCoord() + 10, area.getyCoord() + 20);
	}

}