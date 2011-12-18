package de.empusa.conway.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.empusa.conway.api.Cell;
import de.empusa.conway.api.Direction;
import de.empusa.conway.api.Lifecycle;
import de.empusa.conway.impl.CellImpl;

@RunWith(value=Parameterized.class)
public class CellTest {

	private Cell cell;
	private Map<Direction, Cell> neighbors;
	private int sumLivingCells;
	private Lifecycle[] matrix;
	
	public CellTest(Cell cell, int sumLivingCells, Lifecycle[] matrix) {
		super();
		this.cell = cell;
		this.sumLivingCells = sumLivingCells;
		this.matrix = matrix;
	}

	
	@Parameters
	public static Collection<Object[]> getTestParams(){
		return Arrays.asList(new Object[][] {
		          { new CellImpl(), 3, new Lifecycle[] {
		  				Lifecycle.ALIVE, Lifecycle.ALIVE, Lifecycle.ALIVE, 
						Lifecycle.DEAD, Lifecycle.DEAD, Lifecycle.DEAD, 
						Lifecycle.DEAD, Lifecycle.DEAD
						} },
		           { new CellImpl(), 0, new Lifecycle[] {
		  				Lifecycle.DEAD, Lifecycle.DEAD, Lifecycle.DEAD, 
						Lifecycle.DEAD, Lifecycle.DEAD, Lifecycle.DEAD, 
						Lifecycle.DEAD, Lifecycle.DEAD
						} },
					{ new CellImpl(), 4, new Lifecycle[] {
		  				Lifecycle.ALIVE, Lifecycle.DEAD, Lifecycle.ALIVE, 
						Lifecycle.DEAD, Lifecycle.ALIVE, Lifecycle.DEAD, 
						Lifecycle.ALIVE, Lifecycle.DEAD
						} },
					{ new CellImpl(), 8, new Lifecycle[] {
		  				Lifecycle.ALIVE, Lifecycle.ALIVE, Lifecycle.ALIVE, 
						Lifecycle.ALIVE, Lifecycle.ALIVE, Lifecycle.ALIVE, 
						Lifecycle.ALIVE, Lifecycle.ALIVE
						} }
		       });
	}

	@Before
	public void setUp() {
		neighbors = new HashMap<Direction, Cell>();
		int counter = 0;
		for(Direction d : Direction.values()){
			neighbors.put(d, new CellImpl(matrix[counter]));
			counter++;
			cell.put(d, neighbors.get(d));
		}
	}
	
	@Test
	public void testDie() {
		//im Grundzustand ist eine Zelle erstmal tot
		assertTrue("Cell should be dead",cell.isDead());
	}
	
	@Test
	public void testLive() {
		//im Grundzustand ist eine Zelle erstmal tot
		//deshalb erstmal leben einhauchen
		cell.awake();
		assertTrue("Cell should be alive", cell.isAlive());
	}
	
	@Test
	public void testNeighbors(){
		for(Direction d: Direction.values()){
			assertEquals("Not the right neighbor",neighbors.get(d), cell.get(d));
		}
	}
	
	@Test 
	public void testSumLivingCells(){
		assertEquals(sumLivingCells, cell.sumLivingCell());
	}

}
