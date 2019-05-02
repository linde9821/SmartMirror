package smartMirror.widget;

import java.awt.EventQueue;
import java.util.ArrayList;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.Location.Map;
import smartMirror.SMPanel.SMPanel;
import smartMirror.Settings.Settings;

public class WidgetHandler {
	ArrayList<Widget> activeWidgets;
	SMPanel panel;
	Settings settings;
	Map map;

	public WidgetHandler(ArrayList<Widget> activeWidgets, SMPanel panel,
			Settings settings) {
		this.activeWidgets = activeWidgets;
		this.panel = panel;
		this.settings = settings;

		map = new Map(settings);
	}

	public void addWidget(Widget widgetToAdd) throws SmartMirrorException {
		if (map.checkAreaFor(widgetToAdd.getArea(), false)) {
			activeWidgets.add(widgetToAdd);
			map.updateArea(widgetToAdd.getArea(), true);
			
			
			new Thread(widgetToAdd).start();
			
			panel.repaint();
		} else
			throw new SmartMirrorException("Area bereits besetzt");
	}

	public void deleteWidget(Widget widgetToDelete) {

	}

	public SMPanel getPanel() {
		return panel;
	}

}
