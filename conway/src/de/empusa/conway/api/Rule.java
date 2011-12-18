package de.empusa.conway.api;


public interface Rule {
	public void givenThatCellIs(Lifecycle aliveOrDead);
	public void whenCellHasInNeighborCells(Comparator moreLessOrExact, int livingCells);
	public void thanCellsNextLifecyleIs(Lifecycle lifecycle);
	public boolean applyOn(Cell cell);
}
