package GameState;

import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;


public class LangChoice extends GameState{
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options = {"Polski","English", "Español","Pусский","Quit"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public LangChoice (GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/bg1.jpg");
			
			titleColor = Color.WHITE;
			titleFont = new Font ("Georgia", Font.ITALIC, 28);
			
			font = new Font("Georgia", Font.PLAIN, 12);
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
		
		//tytul
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Super TIME Bros.", 20, 75);
		
		//menu
		g.setFont(font);
		for(int i=0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 145, 140 + i*15);
		}
	}
	
	private void select() {
		if (currentChoice == 0) {
			gsm.currentLang = GameStateManager.POL;
		}
		else if (currentChoice == 1) {
			gsm.currentLang = GameStateManager.ENG;
		}
		else if (currentChoice == 2) {
			gsm.currentLang = GameStateManager.ESP;
		}
		else if (currentChoice == 3) {
			gsm.currentLang = GameStateManager.RUS;
		}
		else if (currentChoice == 4) {
			System.exit(0);
		}
		gsm.setState(GameStateManager.MENUSTATE);
	}
	
	public void keyPressed (int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased (int k) {}
	
}
