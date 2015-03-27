package test;

import static org.junit.Assert.*;
import mapRelated.BasicMap;

import org.junit.Test;

public class BasicMapTest {

	@Test
	public void testHasCollision() {
		char [][] test = new char [35][16];
		test[3][4] = 'B';
		BasicMap map = new BasicMap(test);
		assertEquals(true, map.hasCollision(3*32,4*32));		
		assertEquals(false, map.isStairs(4*32,6*32));
		
	}
	
	@Test
	public void testIsStairs(){
		char [][] test = new char [35][16];
		test[3][4] = 'S';
		BasicMap map = new BasicMap(test);
		assertEquals(true, map.isStairs(3*32,4*32));		
		assertEquals(false, map.isStairs(4*32,6*32));
	}
	
	
	@Test
	public void testInvalidStairsInput(){
		char [][] test = new char [35][16];
		test [3][4] = 'S';
		BasicMap map = new BasicMap(test);
		assertEquals(false,map.isStairs(-40,-21));
		
	}
	
	@Test
	public void testInvalidCollisioninput(){
		char [][] test = new char [35][16];
		test[3][4] = 'S';
		BasicMap map = new BasicMap(test);
		assertEquals(false, map.hasCollision(-40,-21));
		
	}
	
}
