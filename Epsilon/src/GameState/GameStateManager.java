package GameState;

import java.util.ArrayList;

public class GameStateManager {
	public int[] Lang= {0,1,2,3};
	public int currentLang;
	
	public static final int POL = 0;
	public static final int ENG = 1;
	public static final int ESP = 2;
	public static final int RUS = 3;
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int LANGCHOICE = 0;
	public static final int MENUSTATE = 1;
	public static final int LEVEL0ASTATE = 2;
	public static final int LEVEL0BSTATE = 3;
	public static final int LEVEL1STATE = 4;
	public static final int LEVEL1STATEON = 5;
	public static final int LEVEL2STATE = 6;
	public static final int LEVEL2STATEON = 7;
	public static final int LEVEL3STATEA = 8;
	public static final int LEVEL3STATEB = 9;
	public static final int LEVEL3STATEC = 10;
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		
		currentState = LANGCHOICE;
		gameStates.add(new LangChoice (this));
		gameStates.add(new MenuState (this));
		gameStates.add(new Level0AState(this));
		gameStates.add(new Level0BState(this));
		gameStates.add(new Level1State(this));
		gameStates.add(new Level1StateON(this));
		gameStates.add(new Level2State(this));
		gameStates.add(new Level2StateON(this));
		gameStates.add(new Level3StateA(this));
		gameStates.add(new Level3StateB(this));
		gameStates.add(new Level3StateC(this));
		
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}

	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased (int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	public int startX=100;
	public int startY=180;
	
	public int startX2;
	public int startY2;
	
	public void setStartX(int X)
	{
		startX = X;
	}
	
	public int getStartX()
	{
		return startX;
	}
	
	public void setStartY(int Y)
	{
		startY = Y;
	}
	
	public int getStartY()
	{
		return startY;
	}
	
	public void setStartX2(int X)
	{
		startX2 = X;
	}
	
	public int getStartX2()
	{
		return startX2;
	}
	
	public void setStartY2(int Y)
	{
		startY2 = Y;
	}
	
	public int getStartY2()
	{
		return startY2;
	}
}
