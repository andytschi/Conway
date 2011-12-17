package de.empusa.conway.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import de.empusa.conway.api.Cell;
import de.empusa.conway.impl.CellImpl;

public class TestCell {

	private Cell cell;

	@Before
	public void setUp() {
		cell = new CellImpl();
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

}
