package de.empusa.conway.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import static org.easymock.EasyMock.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.empusa.conway.api.Cell;
import de.empusa.conway.api.Comparator;
import de.empusa.conway.api.Lifecycle;
import de.empusa.conway.api.Rule;

@RunWith(value=Parameterized.class)
public class RuleTest {
	// class under test
	private Rule rule;
	// and its params
	private Lifecycle currentLifecycle;
	private Lifecycle expectedLifecycle;
	private Lifecycle nextLifecycle;
	private Comparator comparator;
	private int expectedLivingCells;
	private int currentLivingCells;
	private boolean ruleWillBeApplied;
	// used as mock
	private Cell mockCell;

	public RuleTest(Lifecycle currentLifecycle, int currentLivingCells, Comparator comparator, int expectedLivingCells, Lifecycle expectedLifecycle, Lifecycle nextLifecycle, boolean ruleWillBeApplied) {
		super();
		this.currentLifecycle = currentLifecycle;
		this.currentLivingCells = currentLivingCells;
		this.comparator = comparator;
		this.expectedLivingCells = expectedLivingCells;
		this.expectedLifecycle = expectedLifecycle;
		this.nextLifecycle = nextLifecycle;
		this.ruleWillBeApplied = ruleWillBeApplied;
	}
	
	@Parameters
	public static Collection<Object[]> getTestParams() {
		return Arrays.asList(new Object[][] {
				//     Eine tote Zelle mit genau drei lebenden Nachbarn wird in der Folgegeneration neu geboren.
				{
					Lifecycle.DEAD, 3, Comparator.EXACT, 3, Lifecycle.DEAD, Lifecycle.ALIVE, true
				},
				{
					Lifecycle.DEAD, 4, Comparator.EXACT, 3, Lifecycle.DEAD, Lifecycle.ALIVE, false
				},
				{
					Lifecycle.DEAD, 2, Comparator.EXACT, 3, Lifecycle.DEAD, Lifecycle.ALIVE, false
				},
				{
					Lifecycle.DEAD, 0, Comparator.EXACT, 3, Lifecycle.DEAD, Lifecycle.ALIVE, false
				},
				{
					Lifecycle.ALIVE, 3, Comparator.EXACT, 3, Lifecycle.DEAD, Lifecycle.ALIVE, false
				},
				// Lebende Zellen mit weniger als zwei lebenden Nachbarn sterben in der Folgegeneration an Einsamkeit.
				{
					Lifecycle.ALIVE, 1, Comparator.LESSTHAN, 2, Lifecycle.ALIVE, Lifecycle.DEAD, true
				},
				{
					Lifecycle.ALIVE, 0, Comparator.LESSTHAN, 2, Lifecycle.ALIVE, Lifecycle.DEAD, true
				},
				{
					Lifecycle.ALIVE, 2, Comparator.LESSTHAN, 2, Lifecycle.ALIVE, Lifecycle.DEAD, false
				},
				{
					Lifecycle.ALIVE, 3, Comparator.LESSTHAN, 2, Lifecycle.ALIVE, Lifecycle.DEAD, false
				},
				{
					Lifecycle.DEAD, 1, Comparator.LESSTHAN, 2, Lifecycle.ALIVE, Lifecycle.DEAD, false
				},
				{
					Lifecycle.DEAD, 0, Comparator.LESSTHAN, 2, Lifecycle.ALIVE, Lifecycle.DEAD, false
				},
				//Eine lebende Zelle mit zwei oder drei lebenden Nachbarn bleibt in der Folgegeneration lebend.
				{
					Lifecycle.ALIVE, 2, Comparator.EXACT, 2, Lifecycle.ALIVE, Lifecycle.ALIVE, true
				},
				{
					Lifecycle.ALIVE, 3, Comparator.EXACT, 3, Lifecycle.ALIVE, Lifecycle.ALIVE, true
				},
				{
					Lifecycle.ALIVE, 0, Comparator.EXACT, 2, Lifecycle.ALIVE, Lifecycle.ALIVE, false
				},
				{
					Lifecycle.ALIVE, 1, Comparator.EXACT, 3, Lifecycle.ALIVE, Lifecycle.ALIVE, false
				},
				{
					Lifecycle.ALIVE, 4, Comparator.EXACT, 2, Lifecycle.ALIVE, Lifecycle.ALIVE, false
				},
				{
					Lifecycle.ALIVE, 5, Comparator.EXACT, 3, Lifecycle.ALIVE, Lifecycle.ALIVE, false
				},
				{
					Lifecycle.DEAD, 2, Comparator.EXACT, 2, Lifecycle.ALIVE, Lifecycle.ALIVE, false
				},
				{
					Lifecycle.DEAD, 3, Comparator.EXACT, 3, Lifecycle.ALIVE, Lifecycle.ALIVE, false
				},
				//Lebende Zellen mit mehr als drei lebenden Nachbarn sterben in der Folgegeneration an †berbevšlkerung.
				{
					Lifecycle.ALIVE, 4, Comparator.MORETHAN, 3, Lifecycle.ALIVE, Lifecycle.DEAD, true
				},
				{
					Lifecycle.ALIVE, 5, Comparator.MORETHAN, 3, Lifecycle.ALIVE, Lifecycle.DEAD, true
				},
				{
					Lifecycle.ALIVE, 3, Comparator.MORETHAN, 3, Lifecycle.ALIVE, Lifecycle.DEAD, false
				},
				{
					Lifecycle.ALIVE, 2, Comparator.MORETHAN, 3, Lifecycle.ALIVE, Lifecycle.DEAD, false
				},
				{
					Lifecycle.ALIVE, 0, Comparator.MORETHAN, 3, Lifecycle.ALIVE, Lifecycle.DEAD, false
				},
				{
					Lifecycle.DEAD, 4, Comparator.MORETHAN, 3, Lifecycle.ALIVE, Lifecycle.DEAD, false
				}
		});
	}

	@Before
	public void setUp() {
		//create mock
		mockCell = createMock("mockCell", Cell.class);
		// set up 
		rule = new RuleImpl();
		rule.givenThatCellIs(expectedLifecycle);
		rule.whenCellHasInNeighborCells(comparator, expectedLivingCells);
		rule.thanCellsNextLifecyleIs(nextLifecycle);
	}

	@Test
	public void testApplyOn() {
		//set expectations
		expect(mockCell.getLifecycle()).andReturn(currentLifecycle);
		expect(mockCell.sumLivingCell()).andReturn(currentLivingCells);
		if(ruleWillBeApplied){
			mockCell.translate(nextLifecycle);
		}
		//replay expectations
		replay(mockCell);
		//and test
		assertEquals(ruleWillBeApplied, rule.applyOn(mockCell));
	}
	
	@After
	public void tearDown(){
		verify(mockCell);
	}

}
