package GameState;

import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;


public class EndState extends GameState{
	
	private Background bg;
	
	public EndState (GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {}
	public void update() {}
	
	public void draw (Graphics2D g) {
		if(gsm.currentLang == 0)
			bg = new Background ("/Backgrounds/Epol.jpg");
		else if(gsm.currentLang == 1)
			bg = new Background ("/Backgrounds/Eeng.jpg");
		else if(gsm.currentLang == 2)
			bg = new Background ("/Backgrounds/Eesp.jpg");
		else if(gsm.currentLang == 3)
			bg = new Background ("/Backgrounds/Erus.jpg");
		bg.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
	
}
