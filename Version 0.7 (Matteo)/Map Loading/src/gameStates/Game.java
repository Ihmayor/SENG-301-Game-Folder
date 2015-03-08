package gameStates;

import mapRelated.BasicMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import playerRelated.Player;
import MonsterRelated.BasicMonster;
import MonsterRelated.Creature;

public class Game extends BasicGameState {

	private StateBasedGame game;
	private BasicMap currentMap;
	private BasicMap nextMap;
	//Can maybe have an array of levels (maps) in the future
	int floorCounter = 1;

	private Player player;
	private String[][] entityArray;
	private BasicMonster monster;
	
	private long prevTime;
	
	public static final int ID = 1;
	@Override
	public void init(GameContainer gc, StateBasedGame stateGame)
			throws SlickException {
	this.game = stateGame;
	prevTime = 0;
	currentMap = new BasicMap("res/map/floor1.tmx");
	nextMap = new BasicMap("res/map/floor2.tmx");
	SpriteSheet monsterSheet = new SpriteSheet("res/player/dummySheet.png",32,32); 
//	Image monsterImage = monsterSheet.getSubImage(0, 0);
	player = new Player(gc, stateGame,currentMap);
//	monster = new BasicMonster(floorOne, monsterImage);
	//initEntityArray();
///	player.setEntityArray(entityArray);
///	monster.setEntityArray(entityArray);
	}

//	private void initEntityArray (){
//		String [][] newArray = new String [BasicMap.widthByTiles][BasicMap.heightByTiles];
//		entityArray = newArray;
//		for (int i = 0; i < BasicMap.widthByTiles-1; i++)
//		{
//			for (int c =0; c < BasicMap.heightByTiles-1; c++)
//			{
//				entityArray[i][c] = " ";
//			}
//		}
//		entityArray[((Creature)player).getPosition()[0]/32][((Creature)player).getPosition()[1]/32] = "P";
//		entityArray[monster.getPosition()[0]/32][monster.getPosition()[1]/32] = monster.getName();
//	}
//	
	@Override
	public void render(GameContainer gc, StateBasedGame stateGame, Graphics g)
			throws SlickException {
		currentMap.render();
	//	monster.render(g);
		player.render(g);
		g.setColor(Color.white);
	    g.drawString("GAME IS NOW IN SESSION", 100, 490);
	    g.drawString("Press Q to quit", 400, 490);
	}
	
	public void keyReleased (int key, char c){
	switch (key){
	case Input.KEY_Q:
		GameContainer gc = game.getContainer();//Had to instantiate. I could've also made this another class variable.
		gc.exit();//Exits game. 
		
		break;
		}	
	}

	int counter = 0;
	@Override
	public void update(GameContainer gc, StateBasedGame stateGame, int delta)
			throws SlickException {
		long tmp = System.currentTimeMillis();
	//	long deltaTime = tmp - prevTime;
		prevTime = tmp;
		player.update(counter);
		if (player.getOnStairs()){
			player.setMap(nextMap);
			currentMap = nextMap;
			floorCounter++;
			player.setOnStairs(false);
		}
	//	monster.update(player.getPosition());

	}

	@Override
	public int getID() {
		return ID;
	}

}
