package TileMap;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import Main.GamePanel;

public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;

	public Background (String s) {
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void draw(Graphics2D g) {
			
		g.drawImage(image, (int)x, (int)y, null);
		if (x<0) {
			g.drawImage(image, (int)x + GamePanel.WIDTH, (int) y, null);
		}
		if(x>0) {
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
		}
	}
	
}
