package smartMirror.widget;

import java.util.ArrayList;

import smartMirror.SMPanel.SMPanel;

public class WidgetHandler {
	ArrayList<Widget> activeWidgets;
	ArrayList<Widget> passiveWidgets;
	SMPanel panel;

	public WidgetHandler(ArrayList<Widget> activeWidgets, ArrayList<Widget> passiveWidgets, SMPanel panel) {
		this.activeWidgets = activeWidgets;
		this.passiveWidgets = passiveWidgets;
		this.panel = panel;
	}

	public void addWidget(Widget widgetToAdd) {
		activeWidgets.add(widgetToAdd);
		panel.repaint();
	}
	
	public void deleteWidget(Widget widgetToDelete) {
		
	}
	
}
