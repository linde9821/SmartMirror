package smartMirror.SMPanel;

import java.awt.Color;
import java.awt.Graphics;
//Java-original
import java.util.ArrayList;

import javax.swing.JPanel;

//Selfmade
import smartMirror.widget.Widget;
import smartMirror.widget.WidgetHandler;

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
	
	public void paintcomponent(Graphics g) {
		super.paintComponent(g);
		for(Widget w: activeWidgets) {
			w.render(g);
		}
	}
}
