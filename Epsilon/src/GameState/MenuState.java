package GameState;

import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;


public class MenuState extends GameState{
	
	private Background bg;
	
	private int currentChoice = 0;
	private String[] options1 = {"Start", "Wyjdź"};
	private String[] options2 = {"Start", "Quit"};
	private String[] options3 = {"Empezar", "Dejar"};
	private String[] options4 = {"Начать", "Закончить"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public MenuState (GameStateManager gsm) {
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/bg2.jpg");
			
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
		for(int i=0; i < 2; i++) {
			if (i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.BLACK);
			}
			if(gsm.currentLang == 0)
			g.drawString(options1[i], 145, 140 + i*15);
			else if(gsm.currentLang == 1)
			g.drawString(options2[i], 145, 140 + i*15);
			else if(gsm.currentLang == 2)
			g.drawString(options3[i], 145, 140 + i*15);
			else if(gsm.currentLang == 3)
				g.drawString(options4[i], 145, 140 + i*15);
		}
	}
	
	private void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL0ASTATE);
		}
		if (currentChoice == 1) {
			System.exit(0);
		}
		
	}
	
	public void keyPressed (int k) {
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = 2 - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == 2) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased (int k) {}
	
}
