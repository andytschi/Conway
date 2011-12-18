package de.empusa.conway.impl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.empusa.conway.api.Cell;
import de.empusa.conway.api.Lifecycle;
import de.empusa.conway.api.Universe;

@RunWith(value=Parameterized.class)
public class UniverseTest {
	
	private Lifecycle[] matrix;

	public UniverseTest(Lifecycle[] matrix) {
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
