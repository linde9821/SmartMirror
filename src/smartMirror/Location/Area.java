package smartMirror.Location;

public class Area extends Position{
	
	int width, hight;
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	}
	
	public Area(int x, int y, int width, int hight) {
		super(x, y);
		this.width = width;
		this.hight = hight;
	}

}
