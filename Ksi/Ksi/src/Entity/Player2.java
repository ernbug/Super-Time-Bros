package Entity;

import TileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player2 extends MapObject {
	//animacje
	BufferedImage spritesheet;
	private ArrayList<BufferedImage[]> sprite;
	public Player2(TileMap tm) {
		super(tm);
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		facingRight = true;
		
		//wczytujemy obrazki
		try 
		{
			spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/kamien.png"));
			
			sprite = new ArrayList<BufferedImage[]>();
			BufferedImage[] bi = new BufferedImage[1];
			bi[0] = spritesheet.getSubimage(0, 0, width, height);
			sprite.add(bi);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g)
	{
		setMapPosition();
		g.drawImage(spritesheet.getSubimage(0, 0, width, height), (int)(x+xmap - width / 2), (int)(y+ymap - height / 2), null);
	}
}
