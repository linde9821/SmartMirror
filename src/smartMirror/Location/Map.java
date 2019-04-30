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
	
	public void updateArea(Area area, boolean status) {
		for (int y = 0; y < area.getHigth(); y++) {
			for (int x = 0; x < area.getWidth(); x++) {
				cells[x][y].setActive(true);
			}
		}
	}
	
	public boolean checkAreaFor(Area area, boolean status) {
		for (int y = 0; y < area.getHigth(); y++) {
			for (int x = 0; x < area.getWidth(); x++) {
				if (cells[x][y].isActive() != status) {
					return false;
				}
			}
		}
		
		return true;
	}
}
