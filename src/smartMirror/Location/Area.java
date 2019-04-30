package smartMirror.Location;

public class Area extends Position{

	int width, higth;
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHigth() {
		return higth;
	}

	public void setHigth(int higth) {
		this.higth = higth;
	}

	public Area() {
		// TODO Auto-generated constructor stub
	}
	
	public Area(int x, int y, int width, int hight) {
		super(x, y);
		this.width = width;
		this.higth = higth;
	}

}
