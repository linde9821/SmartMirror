package smartMirror.widget;

import java.util.ArrayList;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.Location.Map;
import smartMirror.SMPanel.SMPanel;

public class WidgetHandler {
	ArrayList<Widget> activeWidgets;
	ArrayList<Widget> passiveWidgets;
	SMPanel panel;
	Map map;

	public WidgetHandler(ArrayList<Widget> activeWidgets, ArrayList<Widget> passiveWidgets, SMPanel panel) {
		this.activeWidgets = activeWidgets;
		this.passiveWidgets = passiveWidgets;
		this.panel = panel;
	}

	public void addWidget(Widget widgetToAdd) throws SmartMirrorException {
		if(checkArea()) {
			activeWidgets.add(widgetToAdd);
			panel.repaint();
		}
	}
	
	public void deleteWidget(Widget widgetToDelete) {
		
	}
	
	public boolean checkArea() throws SmartMirrorException {
		boolean status = false;
		for(Widget w: activeWidgets) {
			status = map.checkAreaFor(w.getArea(), true);
			if (!status) 
				throw new SmartMirrorException("Area schon vergeben!");
		}return status;
	}
	
}
