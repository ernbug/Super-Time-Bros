package TileMap;

import java.awt.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap {
	//pozycja
	private double x;
	private double y;
	
	//granice
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	//mapa
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	//tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	//rysowanie
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
	}
	
	public void loadTiles (String s) {
		try {
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[8][numTilesAcross];
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(col*tileSize, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				subimage = tileset.getSubimage(col*tileSize, tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
				subimage = tileset.getSubimage(col*tileSize, 2*tileSize, tileSize, tileSize);
				tiles[2][col] = new Tile(subimage, Tile.NEWBLOCKED);
				subimage = tileset.getSubimage(col*tileSize, 3*tileSize, tileSize, tileSize);
				tiles[3][col] = new Tile(subimage, Tile.BUTTON);
				subimage = tileset.getSubimage(col*tileSize, 4*tileSize, tileSize, tileSize);
				tiles[4][col] = new Tile(subimage, Tile.ONBUTTON);
				subimage = tileset.getSubimage(col*tileSize, 5*tileSize, tileSize, tileSize);
				tiles[5][col] = new Tile(subimage, Tile.NEXT);
				subimage = tileset.getSubimage(col*tileSize, 6*tileSize, tileSize, tileSize);
				tiles[6][col] = new Tile(subimage, Tile.CTRL);
				subimage = tileset.getSubimage(col*tileSize, 7*tileSize, tileSize, tileSize);
				tiles[7][col] = new Tile(subimage, Tile.LAVA);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadMap (String s) {
		try {
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols + tileSize;
			height = numRows + tileSize;
			
			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;
			
			String delims = "\\s+";
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
					
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getTileSize() {return tileSize;}
	public int getx() {return (int)x; }
	public int gety() {return (int)y; }
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	
	public int getType(int row, int col) {
		int rc = map [row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles [r][c].getType();
	}
	
	public void setPosition(double x, double y) {
		
		this.x += (x-this.x);
		this.y += (y-this.y);
		
		fixBounds();
	}
	
	private void fixBounds() {
		if(x<xmin) x = xmin;
		if(y<ymin) y = ymin;
		if(x>xmax) x = xmax;
		if(y>ymax) y = ymax;
	}
	
	public void draw(Graphics2D g) {
		for (int row = 0; row < numRowsToDraw; row++) {
			if(row >= numRows) break;
			
			for (int col = 0; col < numColsToDraw; col++) {
				if(col >= numCols) break;
				
				if (map [row][col] == 0) continue;
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage (tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);
			}
		}
			
	}
}