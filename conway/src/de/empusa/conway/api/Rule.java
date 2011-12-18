package de.empusa.conway.api;


public interface Rule {
	public void whenCellIs(Lifecycle aliveOrDead);
	public void whenCellHasLivingNeighborCells(Comparator moreLessOrExact, int livingCells);
	public boolean applyOn(Cell cell);
	public void thanCellsNextLifecyleIs(Lifecycle lifecycle);
}
