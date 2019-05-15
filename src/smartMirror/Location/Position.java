/**
* @author  Moritz Lindner
* @version 0.1
* @since 15.05.2019 
*/

package smartMirror.Location;

public class Position {

	int xCoord, yCoord;
	
	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public Position() {
		// TODO Auto-generated constructor stub
	}
	
	public Position(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}

}
