package Entity;

import TileMap.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject {
	
	//animacje
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {2, 8, 1, 2}; //liczba klatek danej animacji
	
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	
	public Player(TileMap tm) {
		super(tm);
		width = 30;
		height = 30;
		cwidth = 20;
		cheight = 20;
		
		moveSpeed = 0.3;
		maxSpeed = 1.8;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		//wczytujemy obrazki
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/jajko.png"));//zmiana na zebane obrazki
			
			sprites = new ArrayList<BufferedImage[]>();
			
			for(int i = 0; i < 4; i++) {
				BufferedImage[] bi = new BufferedImage[numFrames [i]];
				for (int j = 0; j < numFrames[i]; j++) {
					bi[j] = spritesheet.getSubimage(j*width, i*height, width, height);
				}
				sprites.add(bi);
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
	}
	
	private void getNextPosition() {
		//ruch bohatera
		if (left) {
			dx -= moveSpeed;
			if(dx < maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if (right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if (dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		if(jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}
		
		if(falling) {
			dy += fallSpeed;
			if(dy>0) jumping = false;
			if(dy<0 && !jumping) dy += stopJumpSpeed;
			if (dy > maxFallSpeed) dy = maxFallSpeed;
		}
	}
	
	public void update () {
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		//animacje
		if(dy>0) {
			if (currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if (dy<0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
			}
		}
		else if (left||right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
			}
		}
			
		animation.update();
		
		//kierunki
		if (right) facingRight = true;
		if (left) facingRight = false;
	}
	
	public void draw(Graphics2D g) {
		setMapPosition();
		
		if(facingRight) {
			g.drawImage(animation.getImage(), (int)(x+xmap - width / 2), (int)(y+ymap - height / 2), null);
		}
		else {
			g.drawImage(animation.getImage(), (int)(x+xmap - width / 2 + width), (int)(y+ymap - height / 2), -width, height, null);
		}
	}
}
