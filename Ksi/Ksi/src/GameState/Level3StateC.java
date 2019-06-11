package GameState;

import TileMap.*;

import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import Entity.Player;
import Entity.Player2;

public class Level3StateC extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	private Player2 player2;
	private Player2 player3;
	
	public Level3StateC (GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init () {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/bricksfinal.png");
		tileMap.loadMap("/Maps/mapa3c.map");
		tileMap.setPosition(0, 0);
		
		if(gsm.currentLang == 0)
			bg = new Background ("/Backgrounds/newBGdark.jpg");
		else if(gsm.currentLang == 1)
			bg = new Background ("/Backgrounds/newBGdark.jpg");
		else if(gsm.currentLang == 2)
			bg = new Background ("/Backgrounds/newBGdark.jpg");
		else if(gsm.currentLang == 3)
			bg = new Background ("/Backgrounds/newBGdark.jpg");
	
		player = new Player(tileMap);
		player2 = new Player2(tileMap);
		player3 = new Player2(tileMap);
		player2.setPosition (gsm.getStartX(),gsm.getStartY());
		player3.setPosition(gsm.getStartX2(), gsm.getStartY2());
		player.setPosition(60, 180);
	}

	public void update() {
		if(player.getNext())
		{
			gsm.setState(GameStateManager.LEVEL4STATEA);
		}
		player.update();
	}
	
	
	public void draw(Graphics2D g) {
		bg.draw(g);

		tileMap.draw(g);
		
		player.draw(g);
		player2.draw(g);
		player3.draw(g);
	}
	public void keyPressed (int k) {
		if(k==KeyEvent.VK_LEFT) player.setLeft(true);
		if(k==KeyEvent.VK_RIGHT) player.setRight(true);
		if(k==KeyEvent.VK_UP) player.setJumping(true);
	}
	
	public void keyReleased(int k) {
		if(k==KeyEvent.VK_LEFT) player.setLeft(false);
		if(k==KeyEvent.VK_RIGHT) player.setRight(false);
		if(k==KeyEvent.VK_UP) player.setJumping(false);
	}
}