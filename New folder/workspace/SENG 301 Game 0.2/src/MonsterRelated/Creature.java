package MonsterRelated;

import mapRelated.BasicMap;

public class Creature {
	
	protected int x,y;
	protected char [][] entityArray;
	protected char name;
	
	public void setEntityArray(char[][] entityArray){this.entityArray = entityArray;}
	
	protected boolean search(char name){
			boolean found = false;
			for (int row = x-2; row < x+2; row++)
			{
				for (int column = y-2; column < y+2; column++)
				{
					if (entityArray[row][column] == name)
					{
						found = true;
						break;
					}
				}
			}
			
			return found;
		}
	
	public int[] getPosition(){
		int[] position = new int[2];
		position[0] = x;
		position[1] = y;
		return position;
	}

	protected boolean isTaken(int x, int y){
		boolean isTaken = false;
		if (entityArray[x][y] != ' ')
			isTaken = true;
		return isTaken;
	}
	
	public void setPosition(int [] otherPosition, char otherName){
		char [][] newArray = new char [BasicMap.widthByTiles][BasicMap.heightByTiles];
		newArray[x][y] = name; 
		newArray[otherPosition[0]][otherPosition[1]] = otherName;
		newArray = entityArray;
	}
	

}
