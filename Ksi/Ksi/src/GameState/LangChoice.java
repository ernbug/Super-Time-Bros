package GameState;

import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;

public class LangChoice extends GameState {

	private Background bg;

	private int currentChoice = 0;
	private String[] options = { "Polski", "English", "Español", "Pусский", "Quit" };

	private Font font;

	public LangChoice(GameStateManager gsm) {
		this.gsm = gsm;

		try {
			bg = new Background("/Backgrounds/bgLang.jpg");
			
			font = new Font("Arial Black", Font.PLAIN, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
	}

	public void update() {
	}

	public void draw(Graphics2D g) {
		// tlo
		bg.draw(g);

		// menu
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.WHITE);
			} else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 210, 180 + i * 25);
		}
	}

	private void select() {
		if (currentChoice == 0) {
			gsm.currentLang = GameStateManager.POL;
		} else if (currentChoice == 1) {
			gsm.currentLang = GameStateManager.ENG;
		} else if (currentChoice == 2) {
			gsm.currentLang = GameStateManager.ESP;
		} else if (currentChoice == 3) {
			gsm.currentLang = GameStateManager.RUS;
		} else if (currentChoice == 4) {
			System.exit(0);
		}
		gsm.setState(GameStateManager.MENUSTATE);
	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	public void keyReleased(int k) {
	}

}
