package playerRelated;

import mapRelated.BasicMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

public class BackUpPlayer {
	
	private final GameContainer gc;
	private long previousTime = 0;
	
	private final StateBasedGame sbg;
	private BasicMap map;
	//Basic Sprite Variables
		private SpriteSheet sheet;
		private Animation currentSprite, up, down,left,right;

		//Limited Vision Effect
		private Image shadow;		
	
	//Current Position of Player
		private int current_x = 4, current_y = 2;
		private float x = current_x*32,y = current_y*32; //Offset for tile Movement. Used for entity Array.

	
	public BackUpPlayer(GameContainer gc, StateBasedGame sbg, BasicMap map) throws SlickException{
		this.gc = gc;
		this.sbg = sbg;
		this.map = map;
		
		sheet = new SpriteSheet("res/player/dummySheet.png", 32,32);
		shadow = new Image("res/player/evenLargerShadow.png");
		loadPlayerSprite(sheet);
		
	}
	
	private void loadPlayerSprite(SpriteSheet playerSheet){
		//Load Sprite Images for Player
				Image [] upSprite = {sheet.getSubImage(4,0),sheet.getSubImage(5, 0)};
				Image [] downSprite = {sheet.getSubImage(0,0), sheet.getSubImage(1,0)};
				Image [] rightSprite = {sheet.getSubImage(6,0), sheet.getSubImage(7,0)};
				Image [] leftSprite = {sheet.getSubImage(2,0),sheet.getSubImage(3,0)} ;
				

			//Set the duration of Animation in Milliseconds	
				int [] duration = {200,200};
				
			//Initialize Animations
				up = new Animation(upSprite, duration, false);
				down = new Animation (downSprite, duration, false);
				left = new Animation(leftSprite, duration, false);
				right = new Animation (rightSprite,duration,false);
				
				currentSprite = down;
	}
	
	public void render(Graphics g){
	currentSprite.draw((int) x, (int) y);//Draw what the Current sprite should look like.
	g.drawImage(shadow,(int)x-1110, (int)y-850); //Draw Shadow with a particular offset for the spotlight.
		
	}
	
	public void setMap(BasicMap map){
		this.map = map;//Just In case we're loading new floors.
	}
	
	public boolean delayUpdate(long delta){
		return false;
	}
	
	
	public void update(){
		long tmp = System.currentTimeMillis();
		long delta = tmp-previousTime;
		previousTime = tmp;
		

		delayUpdate(delta);
		Input input = gc.getInput(); // Get the input class.
		int SIZE = 32;//Amount of pixels per tile.
		float fdelta=delta*0.1f;//Takes the time and turns to a float.
	///USING KEYBOARD FUNCTIONS BECAUSE MY NUM-PAD is M.I.A
		if (input.isKeyDown(Input.KEY_UP))
		{
		
			currentSprite = up;
			//Conditional branch checks that one: Not out of bounds of screen
			//two: If the top pixel of a tile is passable and if the bottom pixel of a tile is passable.
			if (y > 0 & (!(map.hasCollision(x, y - fdelta) || map.hasCollision(x+SIZE-1, y - fdelta))))
			{
			//Adjust position of the sprite.
			currentSprite.update((long)fdelta);
			
			y -= fdelta;
			}
			
		}
		
		else if (input.isKeyDown(Input.KEY_DOWN)){
			currentSprite = down;
			System.out.println("y-value:"+y);
			if (y <= 475 &(!(map.hasCollision(x, y + SIZE + fdelta) || map.hasCollision(x+SIZE-1, y + SIZE + fdelta))))
			{
			currentSprite.update((long)fdelta);
			y += delta *0.1f;	
			}
				
			}
		else if (input.isKeyDown(Input.KEY_LEFT)){
			currentSprite = left;
			if (x > 0 &(!(map.hasCollision(x, y + SIZE + fdelta) || map.hasCollision(x+SIZE-1, y + SIZE + fdelta))))
			{
			currentSprite.update(delta);
			x -= delta *0.1f;	
			}
			
		}
		else if (input.isKeyDown(Input.KEY_RIGHT)){
			currentSprite = right;
			System.out.println("x-value:"+x);
			if (x <= 1080 &(!(map.hasCollision(x + SIZE + fdelta, y) || map.hasCollision(x + SIZE + fdelta, y+SIZE-1))))
			{
			currentSprite.update(delta);
			x += delta *0.1f;	
			}
			
		}
		else if (input.isKeyDown(Input.KEY_D)){
		//wILL use in place of pass key.
			System.out.println("Pass key has been pressed");
		}
	}
}