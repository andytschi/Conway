package de.empusa.conway.impl;

import de.empusa.conway.api.Cell;
import de.empusa.conway.api.Comparator;
import de.empusa.conway.api.Lifecycle;
import de.empusa.conway.api.Rule;

public class RuleImpl implements Rule{
	private Lifecycle currentLifecycle, nextLifecycle;
	private Comparator comparator;
	private int livingCells;
	@Override
	public void givenThatCellIs(Lifecycle aliveOrDead) {
		currentLifecycle = aliveOrDead;
	}

	@Override
	public void whenCellHasInNeighborCells(Comparator moreLessOrExact,
			int livingCells) {
		comparator = moreLessOrExact;
		this.livingCells = livingCells;
		
	}

	@Override
	public void thanCellsNextLifecyleIs(Lifecycle lifecycle) {
		nextLifecycle = lifecycle;
	}

	@Override
	public boolean applyOn(Cell cell) {
		boolean changed = false;
		boolean lifecycleOk = cellsLifecycleOK(cell);
		boolean neighborsOk = cellsNeighborsOK(cell);
		if(lifecycleOk && neighborsOk){
			cell.translate(nextLifecycle);
			changed = true;
		}
		return changed;
	}

	private boolean cellsNeighborsOK(Cell cell) {
		boolean ok;
		int currentLivingCells = cell.sumLivingCell();
		switch (comparator) {
		case EXACT:
			ok = (currentLivingCells == livingCells)?true:false;
			break;
		case LESSTHAN:
			ok = (currentLivingCells < livingCells)?true:false;
			break;
		case MORETHAN:
			ok = (currentLivingCells > livingCells)?true:false;
			break;
		default:
			ok = false;
			break;
		}
		return ok;
	}

	public boolean cellsLifecycleOK(Cell cell) {
		Lifecycle cellsLifecycle = cell.getLifecycle();
		return currentLifecycle.equals(cellsLifecycle);
	}

}
