package TileMap;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage image;
	private int type;
	
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	public static final int NEWBLOCKED = 2;
	public static final int BUTTON = 3;
	public static final int ONBUTTON = 4;
	public static final int NEXT = 5;
	public static final int CTRL = 6;
	public static final int LAVA = 7;
	
	public Tile (BufferedImage image, int type) {
		this.image = image;
		this.type = type;
	}
	
	public BufferedImage getImage() {return image;}
	public int getType() {return type;} 
}
