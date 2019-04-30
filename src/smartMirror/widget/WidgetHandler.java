package smartMirror.widget;

import java.util.ArrayList;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.Location.Map;
import smartMirror.SMPanel.SMPanel;
import smartMirror.Settings.Settings;

public class WidgetHandler {
	ArrayList<Widget> activeWidgets;
	ArrayList<Widget> passiveWidgets;
	SMPanel panel;
	Settings settings;
	Map map;

	public WidgetHandler(ArrayList<Widget> activeWidgets, ArrayList<Widget> passiveWidgets, SMPanel panel,
			Settings settings) {
		this.activeWidgets = activeWidgets;
		this.passiveWidgets = passiveWidgets;
		this.panel = panel;
		this.settings = settings;

		map = new Map(settings);
	}

	public void addWidget(Widget widgetToAdd) throws SmartMirrorException {
		if (map.checkAreaFor(widgetToAdd.getArea(), false)) {
			activeWidgets.add(widgetToAdd);
			map.updateArea(widgetToAdd.getArea(), true);
			panel.repaint();
		} else
			throw new SmartMirrorException("Area bereits besetzt");
	}

	public void deleteWidget(Widget widgetToDelete) {

	}

}
