package GameState;

import TileMap.Background;
import java.awt.*;


public class BeginState extends GameState{
	
	private Background bg;
	
	public BeginState (GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/bgBegin.jpg");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {}
	public void update() {}
	
	public void draw (Graphics2D g) {
		//tlo
		bg.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		gsm.setState(GameStateManager.LANGCHOICE);
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
	
}
