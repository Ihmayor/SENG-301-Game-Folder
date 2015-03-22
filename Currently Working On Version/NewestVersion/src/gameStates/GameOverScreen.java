package gameStates;

import java.awt.Font;
import inputRelated.GameOverButtons;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


///////////////////////////////////////////////////
//GameOver										 //							
//Purpose: Controls the game over state. 		 //
//This occurs when the player character has died //
//Limit: Current does not restart game	         //
///////////////////////////////////////////////////

public class GameOverScreen extends BasicGameState{
	
	//Game Over Screen Font
	private TrueTypeFont font;
	
	//Game Over State
	public static int ID = 3;
	
	//Game container is used to shut down program
	private GameContainer gc;
	
	private Image skull;
	private GameOverButtons buttons;
	
	//Initialize Game Over State
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
	    // load a default java font
		this.gc = gc;
	    Font awtFont = new Font("Times New Roman", Font.BOLD, 50);
	    font = new TrueTypeFont(awtFont, false);
	    skull = new Image ("res/interface/skull.png");
	    buttons = new GameOverButtons(gc, sbg);
	}

	
	// Displays the game over screen
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		skull.draw(330, 100);
		font.drawString(360, 60, "GAME OVER!", Color.red);
		buttons.render(gc, g);
	}


	// Updates the state of the game (the player is now dead)
	// No images appear
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
	}
	
	// Controls key input in the game over screen
	@Override
	public void keyReleased (int key,char c){
	switch (key){
	case Input.KEY_Q:
		gc.exit();//Exits game. 	
		break;
		}	
	}

	//Returns ID of state for state manager that controls
	//Flow of game
	@Override
	public int getID() {
		return ID;
	}

}