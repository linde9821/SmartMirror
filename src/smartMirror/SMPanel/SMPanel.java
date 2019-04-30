package smartMirror.SMPanel;

import java.awt.Color;
//Java-original
import java.util.ArrayList;
import javax.swing.JPanel;

//Selfmade
import smartMirror.widget.*;

public class SMPanel extends JPanel{

	ArrayList<Widget> activeWidgets = new ArrayList<Widget>();
	ArrayList<Widget> passiveWidgets = new ArrayList<Widget>();
	
	WidgetHandler wh = new WidgetHandler();
	
	public SMPanel() {
		// TODO Auto-generated constructor stub
		this.setBackground(Color.RED);
	}

	
	
}
