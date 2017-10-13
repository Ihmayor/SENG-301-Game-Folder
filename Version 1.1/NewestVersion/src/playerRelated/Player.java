package playerRelated;

import gameStates.GameScreen;
import gameStates.GameScreenAssets;
import managers.CombatManager;
import managers.SoundManager;
import mapRelated.BasicMap;
import monsterRelated.Entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Entity{
	
	//Variables used for Slick 2 Game Components
	private final GameContainer gc;
	
	//Used for stair case movement 
	private boolean onStairs;
	
	//Basic Sprite Variables
	private SpriteSheet sheet;
	private Animation currentSprite, up, down,left,right;

	//Limited Vision Effect
	private Image shadow;		

	private PlayerStatus playerStats;
	private PlayerMovement playerMovement;
	
	////FOR TEST PURPOSES ONLY////
	public Player (int x, int y){
		super(x,y);
		gc = null;
		name = "P";
		playerStats = new PlayerStatus(this);
		playerMovement = new PlayerMovement();
	}
	
	public Player (int x, int y, BasicMap map){
		super(x,y);
		this.map = map;
		gc = null;
		name = "P";
		playerStats = new PlayerStatus(this);
		playerMovement = new PlayerMovement();
	}
		
	public void mockKeyBoard(char c){
		switch(c) 
		{
		case 'u'://Up
			moveUp();
			break;
		case 'd'://Down
			moveDown();
			break;
		case 'l'://Left
			moveLeft();
			break;
		case 'r'://Right
			moveRight();
			break;
		case 'a'://Diagonal Up Left
			moveDiagonalUpLeft();
			break;
		case 'b'://Diagonal Up Right
			moveDiagonalUpRight();
			break;
		case 'c'://Diagonal Down Left
			moveDiagonalDownLeft();
			break;
		case 'f'://Diagonal Down Right
			moveDiagonalDownRight();
			break;
		case 'g':
			moveNowhere();
			break;
		default:
			break;
		}
		
		
	}
	
	
	////FOR TEST PURPOSES ONLY////
	public Player(GameContainer gc, StateBasedGame sbg, BasicMap currentMap,int x, int y) throws SlickException{
		//Constructor used to 
		super(x,y);
		super.maxHealthPoints = 30;
		super.healthPoints = maxHealthPoints;
		
		//Variables for the usage outside functions
		this.gc = gc;
		map = currentMap;
		
		//Initialize Variables
		name = "P";
		sheet = new SpriteSheet("res/player/template2.png", BasicMap.TILESIZE,BasicMap.TILESIZE);
		shadow = new Image("res/player/largerShadow.png");
		loadPlayerSprite(sheet);
		
	}
	
	//Load the animations used for the player's sprite
	private void loadPlayerSprite(SpriteSheet playerSheet){
				//Load Sprite Images for Player
				Image [] upSprite = {sheet.getSubImage(0,3),
								     sheet.getSubImage(1,3),
								     sheet.getSubImage(2,3),
								     sheet.getSubImage(3,3)};
				Image [] downSprite = {sheet.getSubImage(0,0),
					     			  sheet.getSubImage(1,0),
					     			  sheet.getSubImage(2,0),
					     			  sheet.getSubImage(3,0)};
				Image [] rightSprite = {sheet.getSubImage(0,2),
										sheet.getSubImage(1,2),
										sheet.getSubImage(2,2),
										sheet.getSubImage(3,2)};
				
				Image [] leftSprite = {sheet.getSubImage(0,1),
					     			   sheet.getSubImage(1,1),
					     			   sheet.getSubImage(2,1),
					     			   sheet.getSubImage(3,1)};

				//Set the duration of Animation in Milliseconds	
				int [] duration = {300,300,300,300};
				
				//Initialize Animations
				up = new Animation(upSprite, duration, false);
				down = new Animation (downSprite, duration, false);
				left = new Animation(leftSprite, duration, false);
				right = new Animation (rightSprite,duration,false);		
				currentSprite = down;
				
				//Allow animations to automatically play through
				currentSprite.setAutoUpdate(true);
				up.setAutoUpdate(true);
				left.setAutoUpdate(true);
				right.setAutoUpdate(true);
	}
	
	
	
	public void render(Graphics graphics){
	currentSprite.draw((int) x, (int) y);//Draw what the Current sprite should look like.
	graphics.drawImage(shadow,(int)x-1110, (int)y-850); //Draw Shadow with a particular offset for the spotlight
	}
	
	
	public void update(long delta){
		//If the player is not alive change game state.
		//Do not allow the player to move.
		if (!alive){
			return;
			}
		//Input used to get keyboard controls
		Input input = gc.getInput();
		 
///		int[] LeftKeys = new int[] {Input.KEY_NUMPAD7, Input.KEY_7,
	///			Input.KEY_NUMPAD1,Input.KEY_J,
		///		Input.KEY_LEFT,Input.KEY_U,Input.KEY_NUMPAD4};
		
		
		//Diagonal Up Left
		if (input.isKeyPressed(Input.KEY_NUMPAD7)||input.isKeyPressed(Input.KEY_7)){
			 moveDiagonalUpLeft();
		 }
		
		//Normal Up
		else if (input.isKeyPressed(Input.KEY_UP)||input.isKeyPressed(Input.KEY_8)
				||input.isKeyPressed(Input.KEY_NUMPAD8)){
				moveUp();
		}
		
		//Diagonal Up Right
		else if (input.isKeyPressed(Input.KEY_NUMPAD9)||input.isKeyPressed(Input.KEY_9)){
				moveDiagonalUpRight();
		}

		//Normal Left
		else if (input.isKeyPressed(Input.KEY_LEFT)||input.isKeyPressed(Input.KEY_U)
				||input.isKeyPressed(Input.KEY_NUMPAD4)){
				moveLeft();
		}
		
		//PASS TURN
		else if (input.isKeyPressed(Input.KEY_NUMPAD5)||input.isKeyPressed(Input.KEY_I))
			{
			moveNowhere();
			}
		
		//Normal Right
		else if (input.isKeyPressed(Input.KEY_RIGHT)||input.isKeyPressed(Input.KEY_O)||
				input.isKeyPressed(Input.KEY_NUMPAD6)){
			moveRight();
			}
		
		//Diagonal Down Left
		else if (input.isKeyPressed(Input.KEY_NUMPAD1)||input.isKeyPressed(Input.KEY_J)){
				moveDiagonalDownLeft();
				}
		
		//Normal Down
		else if (input.isKeyPressed(Input.KEY_DOWN)||input.isKeyPressed(Input.KEY_K)||
				input.isKeyPressed(Input.KEY_NUMPAD2)){
			moveDown();
		}

		//Diagonal Down Right
		else if (input.isKeyPressed(Input.KEY_NUMPAD3)||input.isKeyPressed(Input.KEY_L)){
			moveDiagonalDownRight();
		}
		 
	}

	
////////////METHODS DEALING WITH MOVEMENT//////////////////////
	private void moveDiagonalUpLeft(){
		    //Update Sprite
   			 currentSprite = left;
			 playerMovement.moveDiagonalUpLeft(this, map);
		 }
	
	
	private void moveUp(){
			currentSprite = up;
			playerMovement.moveUp(this, map);
		}
		
		
	private void moveDiagonalUpRight(){
			currentSprite = right;
			playerMovement.moveDiagonalUpRight(this,map);
		}

	private void moveLeft(){	
			currentSprite = left;
			playerMovement.moveLeft(this,map);
		}
		
	private void moveNowhere()
		{
			currentSprite = down;
			playerMovement.moveNowhere(this, map);	
		}
		
	private void moveRight(){
			currentSprite = right;
			playerMovement.moveRight(this, map);
		}	
		
		
	private void moveDiagonalDownLeft(){
				currentSprite = left;
				playerMovement.moveDiagonalDownLeft(this, map);
				}
				
	private void moveDown(){
			currentSprite = down;
			playerMovement.moveDown(this, map);
			
	}

	private void moveDiagonalDownRight(){
			currentSprite = right;
			playerMovement.moveDiagonalDownRight(this, map);
	}
	
	///Methods dealing with Player Status////
	public PlayerStatus getPlayerStatus() {return playerStats;}

	public void loadStats(int newLevel, int newExp, int newHealth)
	{
		playerStats = new PlayerStatus(this,newLevel, newExp, newHealth);
	}
			
	
///////////METHODS DEALING WITH COMBAT///////////////////	
	
	void attack(int monsterX, int monsterY){
		int criticalHitLimit = playerStats.getCriticaHitLimit();
		int missFactor = playerStats.getMissFactor();
		CombatManager.attackLoop(this,criticalHitLimit, missFactor, monsterX, monsterY);	
	}
	
	
//////////Method dealing with stairs///////////////

	public boolean getOnStairs(){return onStairs;}
	
	public void setOnStairs(boolean var){onStairs = var;}
	
		
	public void setPosition(int newX, int newY)
	{
			x = newX;
			y = newY;
	}
	
}

