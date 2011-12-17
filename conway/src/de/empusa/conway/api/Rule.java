package de.empusa.conway.api;


public interface Rule {
	public void givenThatCellIs(Lifecycle aliveOrDead);
	public void whenCellHasInNeighborCells(Comparator moreLessOrExact, int livingCells);
	public Lifecycle thanCellsLifecyleIs();
}
