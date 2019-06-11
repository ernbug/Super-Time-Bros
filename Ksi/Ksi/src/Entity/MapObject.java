package Entity;

import java.awt.Rectangle;

import TileMap.TileMap;
import TileMap.Tile;

public abstract class MapObject {
	
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	//dla bohatera
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	//wymiary
	protected int width;
	protected int height;
	
	//realny wymiar
	protected int cwidth;
	protected int cheight;
	
	//koloizyjne
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	protected boolean nextCollision; //wykrywanie przejscia na nastepny poziom
	protected boolean buttonCollision;
	protected boolean transButtonCollision;
	protected boolean lavaCollision;
	
	//animacja
	protected Animation animation;
	protected int currentAction;
	protected int prevoiousAction;
	
	//ruch
	protected boolean left;
	protected boolean right;
	protected boolean jumping;
	protected boolean falling;
	
	//atrybuty ruchu
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;
	protected boolean facingRight;
	
	//konstruktor
	public MapObject (TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}
	
	public boolean intersects (MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle() {
		return new Rectangle((int)x - cwidth, (int)y - cheight, cwidth, cheight);
	}
	
	public void calculateCorners (double x, double y) {
		int leftTile = (int)(x - cwidth/2) / tileSize;
		int rightTile = (int)(x + cwidth/2-1) / tileSize;
		int topTile = (int)(y - cheight/2) / tileSize;
		int bottomTile = (int)(y + cheight/2-1) / tileSize;
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		topLeft = (tl == Tile.BLOCKED || tl == Tile.BUTTON || tl == Tile.NEWBLOCKED || tl == Tile.ONBUTTON);
		topRight = (tr == Tile.BLOCKED || tr == Tile.BUTTON || tr == Tile.NEWBLOCKED || tr == Tile.ONBUTTON);
		bottomLeft = (bl == Tile.BLOCKED || bl == Tile.BUTTON || bl == Tile.NEWBLOCKED || bl == Tile.ONBUTTON);
		bottomRight = (br == Tile.BLOCKED || br == Tile.BUTTON || br == Tile.NEWBLOCKED || br == Tile.ONBUTTON);
		nextCollision = (tl == Tile.NEXT || tr == Tile.NEXT || bl == Tile.NEXT || br == Tile.NEXT);
		buttonCollision = (tl == Tile.BUTTON || tr == Tile.BUTTON || bl == Tile.BUTTON || br == Tile.BUTTON);
		transButtonCollision = (tl == Tile.CTRL || tr == Tile.CTRL || bl == Tile.CTRL || br == Tile.CTRL);
		lavaCollision = (tl == Tile.LAVA || tr == Tile.LAVA || bl == Tile.LAVA || br == Tile.LAVA);
	}
	
	public void checkTileMapCollision() {
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;
		
		xdest = x + dx;
		ydest = y + dy;
		
		xtemp = x;
		ytemp = y;
		
		calculateCorners(x, ydest);

		if(dy<0) {
			if(topLeft||topRight) {
				dy = 0;
				ytemp = currRow *tileSize + cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
			
		if(dy>0) {
			if(bottomLeft||bottomRight) {
					dy = 0;
					falling = false;
					ytemp = (currRow+1) * tileSize - cheight / 2;
			}
			else {
				ytemp += dy;
			}
		}
		
		calculateCorners (xdest, y);
		
		if(dx<0 ) {
			if(topLeft||bottomLeft) {
				dx = 0;
				xtemp = currCol * tileSize + cwidth /2;
			}
			else {
				xtemp += dx;
			}
		}
		
		if(dx>0) {
			if(topRight||bottomRight) {
				dx = 0;
				xtemp = (currCol + 1) * tileSize - cwidth / 2;
			}
			else {
				xtemp += dx;
			}
		}
		
		if(!falling) {
			calculateCorners (x, ydest + 1);
			if(!bottomLeft && !bottomRight) {
				falling = true;
			}
			
		}
	}
	
	public int getx() {return (int)x;}
	public int gety() {return (int)y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public int getCwidth () {return cwidth;}
	public int getCheight () {return cheight;}
	public boolean getNext() {return nextCollision;}
	public boolean getButton() {return buttonCollision;}
	public boolean getTransButton() {return transButtonCollision;}
	public boolean getLavaCollision() {return lavaCollision;}
	
	public void setPosition (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setVector (double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition() {
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}
	
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public void setJumping(boolean b) {jumping = b;}
}
	