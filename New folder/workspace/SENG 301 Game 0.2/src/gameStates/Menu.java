package gameStates;

import inputRelated.MainMenuButtons;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	
	public static final int ID = 0; //Set the state's I.D 
	private MainMenuButtons menuButtons;
	
//	private StateBasedGame game; 
	
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
		menuButtons = new MainMenuButtons(gc,stateGame);
		
		}
	
	

	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		gc.setShowFPS(false); //Annoying FPS counter is hidden

		g.setColor(Color.white); //Sets strings to draw in the color white
	    g.drawString("Menu Screen", 440, 110); // Draws string to screen.
	    menuButtons.render(gc,g);//Renders the menu buttons

	}

	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
			//In game loops this is how it changes what's seen on screen. 
			//Say A character is supposed to move 5 pixels left because of 
			//Player input. Well this is where we would put that stuff.
	}
	
	@Override
	public int getID() {
		return ID;//ALSO very important. This is how the stateManager knows what we set the ID's of our states.
	}

}
