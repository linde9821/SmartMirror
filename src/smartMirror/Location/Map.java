/**
* @author  Moritz Lindner
* @version 0.1.1
* @since 15.05.2019 
*/

package smartMirror.Location;

import smartMirror.Settings.Settings;

public class Map {
	private Cell[][] cells;

	public Map(Settings settings) {
		cells = new Cell[settings.getX()][settings.getY()];

		for (int y = 0; y < settings.getY(); y++) {
			for (int x = 0; x < settings.getX(); x++) {
				cells[x][y] = new Cell(x, y, false);
			}
		}
	}

	public void setCells(Settings settings) {
		cells = new Cell[settings.getX()][settings.getY()];

		for (int y = 0; y < settings.getY(); y++) {
			for (int x = 0; x < settings.getX(); x++) {
				cells[x][y] = new Cell(x, y, false);
			}
		}
	}

	public void updateArea(Area area, boolean status) {
		for (int y = 0; y < area.getHight(); y++) {
			for (int x = 0; x < area.getWidth(); x++) {
				cells[x][y].setActive(true);
			}
		}
	}

	public boolean checkAreaFor(Area area, boolean status) {
		try {

			for (int y = area.getyCoord(); y < area.getHight(); y++) {
				for (int x = area.getxCoord(); x < area.getWidth(); x++) {
					if (cells[x][y].isActive() != status) {
						return false;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}

		return true;
	}
}
