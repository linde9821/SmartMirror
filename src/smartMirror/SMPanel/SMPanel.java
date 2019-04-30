package smartMirror.SMPanel;

import java.awt.Color;
//Java-original
import java.util.ArrayList;
import javax.swing.JPanel;

//Selfmade
import smartMirror.widget.*;

public class SMPanel extends JPanel{

	ArrayList<Widget> activeWidgets;
	ArrayList<Widget> passiveWidgets;
	
	WidgetHandler wh;
	
	public SMPanel() {
		setBackground(Color.RED);
		activeWidgets = new ArrayList<Widget>();
		passiveWidgets = new ArrayList<Widget>();
		wh = new WidgetHandler(activeWidgets, passiveWidgets, this);
	}

	
	public WidgetHandler getWidgetHandler(){
		return wh;
	}
}
