package smartMirror.Widget;

import java.awt.Color;
import java.awt.Graphics;

import smartMirror.Location.Area;
import smartMirror.Location.Position;

public class SevenSegment extends Widget {
	boolean seg[];
	private int scl;

	public SevenSegment(Area area) {
		super(area);

		seg = new boolean[7];

		for (int i = 0; i < 7; i++) {
			seg[i] = false;
		}
		
		scl = 10;
	}

	public SevenSegment(int x, int y, int width, int hight) {
		this(new Area(x, y, width, hight));
	}
	
	

	public int getScale() {
		return scl;
	}

	public void setScale(int scl) {
		this.scl = scl;
	}

	@Override
	public void run() {

	}

	public void render(Graphics g) {
		Position top = new Position(area.getxCoord(), area.getyCoord());
		int x = top.getxCoord();
		int y = top.getyCoord();

		// a
		if (seg[0]) {
			g.setColor(Color.RED);
		} else
			g.setColor(Color.BLACK);

		g.drawLine((x + 1) * scl, (y + 0) * scl, (x + 3) * scl, (y + 0) * scl);

		// b
		if (seg[1]) {
			g.setColor(Color.RED);
		} else
			g.setColor(Color.BLACK);

		g.drawLine((x + 4) * scl, (y + 1) * scl, (x + 4) * scl, (y + 3) * scl);
		
		//c
		if (seg[2]) {
			g.setColor(Color.RED);
		} else
			g.setColor(Color.BLACK);

		g.drawLine((x + 4) * scl, (y + 5) * scl, (x + 4) * scl, (y + 7) * scl);

		// d
		if (seg[3]) {
			g.setColor(Color.RED);
		} else
			g.setColor(Color.BLACK);
	
		g.drawLine((x + 1) * scl, (y + 8) * scl, (x + 3) * scl, (y + 8) * scl);
		
		// e
		if (seg[4]) {
			g.setColor(Color.RED);
		} else
			g.setColor(Color.BLACK);

		g.drawLine((x + 0) * scl, (y + 5) * scl, (x + 0) * scl, (y + 7) * scl);

		// f
		if (seg[5]) {
			g.setColor(Color.RED);
		} else
			g.setColor(Color.BLACK);

		g.drawLine((x + 0) * scl, (y + 1) * scl, (x + 0) * scl, (y + 3) * scl);
		
		
		// g
		if (seg[6]) {
			g.setColor(Color.RED);
		} else
			g.setColor(Color.BLACK);

		g.drawLine((x + 1) * scl, (y + 4) * scl, (x + 3) * scl, (y + 4) * scl);
	}
	
	public void setSegment(char c) {
		setSegment(Character.getNumericValue(c));
	}

	public void setSegment(int v) {
		String val = "";

		switch (v) {
		case (1):
			val = "0110000";
			break;
		case (2):
			val = "1101101";
			break;
		case (3):
			val = "1111001";
			break;
		case (4):
			val = "0110011";
			break;
		case (5):
			val = "1011011";
			break;
		case (6):
			val = "1011111";
			break;
		case (7):
			val = "1110000";
			break;
		case (8):
			val = "1111111";
			break;
		case (9):
			val = "1111011";
			break;
		case (0):
			val = "1111110";
			break;
		default:
			val = "1111110";
		}

		setSegment(val);
	}

	public void setSegment(String s) {
		for (int i = 0; i < 7; i++) {
			seg[i] = (s.charAt(i) == '1') ? true : false;
		}
	}

}
