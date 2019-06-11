package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

public class Boss extends GameState{
	
	private Background bg;
	
	public Boss(GameStateManager gsm)
	{
		this.gsm = gsm;
		try {
			bg = new Background("/Backgrounds/boss.jpg");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void init() {}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
	}

	@Override
	public void keyReleased(int k) {		
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_B)
		{
			gsm.setState(gsm.getPreviousState());			
		};
		
	}

}
