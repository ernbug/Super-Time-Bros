package GameState;

import TileMap.*;

import java.awt.Graphics2D;

import com.sun.glass.events.KeyEvent;

import Entity.Player;

public class Level0BState extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	public Level0BState (GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	
	public void init () {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/bricksfinal.png");
		tileMap.loadMap("/Maps/mapa0b.map");
		tileMap.setPosition(0, 0);
		if(gsm.currentLang == 0)
			bg = new Background ("/Backgrounds/0plB.jpg");
		else if(gsm.currentLang == 1)
			bg = new Background ("/Backgrounds/0engB.jpg");
		else if(gsm.currentLang == 2)
			bg = new Background ("/Backgrounds/0espB.jpg");
		else if(gsm.currentLang == 3)
			bg = new Background ("/Backgrounds/0rusB.jpg");

		player = new Player(tileMap);
		player.setPosition (gsm.getStartX(),gsm.getStartY());
	}

	public void update() {
		if(player.getNext())
		{
			gsm.setState(GameStateManager.LEVEL1STATE);
			
		}
//		if(player.getButton())
//		{
//			gsm.setStartX(player.getx());
//			gsm.setStartY(player.gety());
//			gsm.setState(GameStateManager.LEVEL1STATEON);
//		}
//		if(player.getTransButton())
//		{
//			gsm.setStartX(player.getx());
//			gsm.setStartY(player.gety());
//			gsm.setState(GameStateManager.LEVEL0BSTATE);
//		}
		if(player.getLavaCollision())
		{
			player.setPosition(60, 180);
		}
		player.update();
	}
	
	
	public void draw(Graphics2D g) {
		bg.draw(g);

		tileMap.draw(g);
		
		player.draw(g);
	}
	public void keyPressed (int k) {
		if(k==KeyEvent.VK_LEFT) player.setLeft(true);
		if(k==KeyEvent.VK_RIGHT) player.setRight(true);
		if(k==KeyEvent.VK_UP) player.setJumping(true);
		if(k==KeyEvent.VK_B)
		{
			gsm.setPreviousState(GameStateManager.LEVEL0BSTATE);
			gsm.setState(GameStateManager.BOSS);
		}
	}
	
	public void keyReleased(int k) {
		if(k==KeyEvent.VK_LEFT) player.setLeft(false);
		if(k==KeyEvent.VK_RIGHT) player.setRight(false);
		if(k==KeyEvent.VK_UP) player.setJumping(false);
	}
}