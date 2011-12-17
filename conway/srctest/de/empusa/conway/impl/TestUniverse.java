package de.empusa.conway.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.empusa.conway.Cell;
import de.empusa.conway.Universe;

@RunWith(value=Parameterized.class)
public class TestUniverse {
	
	private Lifecycle[] matrix;

	public TestUniverse(Lifecycle[] matrix) {
		super();
		this.matrix = matrix;
	}

	@Parameters
	public static Collection<Object[]> getTestParams(){
		return Arrays.asList(new Object[][] {
			          { new Lifecycle[] {
			  				Lifecycle.ALIVE, Lifecycle.ALIVE, Lifecycle.ALIVE, 
							Lifecycle.ALIVE, Lifecycle.ALIVE, Lifecycle.ALIVE, 
							Lifecycle.ALIVE, Lifecycle.ALIVE, Lifecycle.ALIVE
							} },
			           { new Lifecycle[] {
			   				Lifecycle.DEAD, Lifecycle.DEAD, Lifecycle.DEAD, 
							Lifecycle.DEAD, Lifecycle.DEAD, Lifecycle.DEAD, 
							Lifecycle.DEAD, Lifecycle.DEAD, Lifecycle.DEAD
							} },
						{ new Lifecycle[] {
			  				Lifecycle.ALIVE, Lifecycle.DEAD, Lifecycle.ALIVE, 
							Lifecycle.DEAD, Lifecycle.ALIVE, Lifecycle.DEAD, 
							Lifecycle.ALIVE, Lifecycle.DEAD, Lifecycle.ALIVE
							} }
			       });
	}
	
	@Test
	public void testMatrix() {
		Universe universe = new UniverseImpl(matrix);
		int counter = 0;
		for (Cell cell : universe) {
			assertEquals(matrix[counter], cell.getLifecycle());
			counter++;
		}
	}
}
