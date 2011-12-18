package de.empusa.conway.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.empusa.conway.api.Cell;
import de.empusa.conway.api.CellFactory;
import de.empusa.conway.api.Lifecycle;

public class CellFactoryTest {
	
	private CellFactory cellFactory;

	@Test
	public void testGetFactory() {
		assertNotNull(cellFactory);
	}

	@Before
	public void setUp() {
		cellFactory = CellFactoryImpl.getFactory();
	}

	@Test
	public void testCreateCellAlive() {
		Cell cell = cellFactory.createCell(Lifecycle.ALIVE);
		assertTrue("Cell should be alive", cell.isAlive());
	}
	
	@Test
	public void testCreateCellDead() {
		Cell cell = cellFactory.createCell(Lifecycle.DEAD);
		assertTrue("Cell should be dead", cell.isDead());
	}
	@Test
	public void testSingleton() {
		CellFactory secondFactory = CellFactoryImpl.getFactory();
		assertEquals("Factories should be the same", secondFactory, cellFactory);
	}

}
