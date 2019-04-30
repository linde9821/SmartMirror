package smartMirror.widget;

import smartMirror.Location.Area;

public class TextWidget extends Widget{	
	
	String testText;
	
	public TextWidget() {
		
	}

	public TextWidget(int x, int y, int width, int hight) {
		Area widgetArea = new Area(x, y, width, hight);
	}

	@Override
	public void run() {
		
	}
	
	public String getText() {
		
		testText = "Moritz stinkt!";
		
		return testText;
	}
	
}
