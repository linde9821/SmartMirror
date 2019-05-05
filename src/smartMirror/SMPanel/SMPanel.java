package smartMirror.SMPanel;

import java.awt.Color;
import java.awt.Graphics;
//Java-original
import java.util.ArrayList;

import javax.swing.JPanel;

import smartMirror.Settings.Settings;
//Selfmade
import smartMirror.widget.Widget;
import smartMirror.widget.WidgetHandler;

public class SMPanel extends JPanel{

	ArrayList<Widget> activeWidgets;
	
	WidgetHandler wh;
	
	public SMPanel(Settings settings) {
		setBackground(Color.WHITE);
		activeWidgets = new ArrayList<Widget>();
		wh = new WidgetHandler(activeWidgets, this, settings);
	}

	public WidgetHandler getWidgetHandler(){
		return wh;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Widget w: activeWidgets) {
			w.render(g);
		}
	}
}
