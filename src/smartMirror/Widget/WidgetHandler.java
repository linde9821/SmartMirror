/**
* @author  Moritz Lindner
* @version 0.1.0
* @since 15.05.2019 
*/

package smartMirror.Widget;

import java.util.ArrayList;

import smartMirror.Exception.SmartMirrorException;
import smartMirror.Location.Map;
import smartMirror.Panel.SmartMirrorPanel;
import smartMirror.Settings.Settings;

public class WidgetHandler {
	ArrayList<Widget> activeWidgets;
	SmartMirrorPanel panel;
	Settings settings;
	Map map;

	public WidgetHandler(ArrayList<Widget> activeWidgets, SmartMirrorPanel panel, Settings settings) {
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

	/**
	 * Versucht den übergeben Widget zu löschen. Liefert true falls erfolgreich
	 * andernfalls false zurück
	 * 
	 * @param widgetToDelete
	 * @return boolean
	 */
	public boolean removeWidget(Widget widgetToRemove) {
		return activeWidgets.remove(widgetToRemove);
	}

	/**
	 * Liefert das vom WidgetHandel genutzte SmartMirrorPanel
	 * 
	 * @return SmartMirrorPanel
	 */
	public SmartMirrorPanel getPanel() {
		return panel;
	}

	/**
	 * Überprüft alle aktiven Widgets ob sie noch laufen und noch gezeichnet werden
	 * können und löscht diese falls eines davon nicht mehr der Fall ist.
	 */
	public void proofAllWidgets() {
		for (Widget w : activeWidgets) {
			if (map.checkAreaFor(w.getArea(), true)) {
				removeWidget(w);
			}
		}
	}
	
	/**
	 * Updated die Map entsprechend der übergeben Settings
	 * @param settings
	 */
	public void updateMap(Settings settings) {
		map.setCells(settings);
	}
}
