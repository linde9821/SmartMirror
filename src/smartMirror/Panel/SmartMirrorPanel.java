/**
* Panel welches als Container für alle Widgets dient.
* @author  Moritz Lindner
* @version 0.3.0
* @since 15.05.2019 
*/

package smartMirror.Panel;

import java.awt.Color;
import java.awt.Graphics;
//Java-original
import java.util.ArrayList;

import javax.swing.JPanel;

import smartMirror.Settings.Settings;
import smartMirror.Widget.Widget;
import smartMirror.Widget.WidgetHandler;

public class SmartMirrorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Widget> activeWidgets;
	private WidgetHandler wh;
	private Color backgroundColor;

	public SmartMirrorPanel(Settings settings) {
		backgroundColor = Color.BLACK;
		setBackground(backgroundColor);
		activeWidgets = new ArrayList<Widget>();
		wh = new WidgetHandler(activeWidgets, this, settings);
	}

	/**
	 * Liefert dem vom Panel verwendeten WidgetHandler
	 * @return WidgetHandler
	 */
	public WidgetHandler getWidgetHandler() {
		return wh;
	}

	/**
	 * Rendert das Panel und alle vorhanden Widgets
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Widget w : activeWidgets) {
			w.render(g);
		}
	}

	/**
	 * Liefert die aktuell benutzte Hintergrundfarbe des Panels
	 * @return Color
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Setzt den Hintergrund des Panels auf die Übergeben Farbe
	 * @param Color backgroundColor 
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		this.setBackground(backgroundColor);
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
	}
}
