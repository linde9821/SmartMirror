package smartMirror.Location;

public class Cell {
	private Position pos;
	private boolean active;
	
	public Cell(int x, int y, boolean active) {
		this(new Position(x, y), active);
	}
	
	public Cell(Position pos, boolean active) {
		this.pos = pos;
		this.active = active; 
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
