package de.empusa.conway.impl;

import java.util.HashMap;
import java.util.Map;

import de.empusa.conway.api.Cell;
import de.empusa.conway.api.Direction;
import de.empusa.conway.api.Lifecycle;


class CellImpl implements Cell {
	private Lifecycle lifecycle;
	private Map<Direction,Cell> neighbors;

	public CellImpl(Lifecycle lifecycle, Map<Direction, Cell> neighbors) {
		super();
		this.lifecycle = lifecycle;
		this.neighbors = neighbors;
	}

	public CellImpl(Lifecycle lifecycle) {
		this(lifecycle,new HashMap<Direction, Cell>());
	}

	/**
	 * Erzeugt eine tote Zelle.
	 */
	public CellImpl() {
		this(Lifecycle.DEAD);
	}

	@Override
	public void die() {
		lifecycle = Lifecycle.DEAD;
	}

	@Override
	public boolean isDead() {
		return Lifecycle.DEAD.equals(lifecycle);
	}

	@Override
	public void awake() {
		lifecycle = Lifecycle.ALIVE;
	}

	@Override
	public boolean isAlive() {
		return Lifecycle.ALIVE.equals(lifecycle);
	}

	@Override
	public Lifecycle getLifecycle() {
		return lifecycle;
	}

	@Override
	public void put(Direction d, Cell cell) {
		neighbors.put(d, cell);
		
	}

	@Override
	public Cell get(Direction d) {
		return neighbors.get(d);
	}

	@Override
	public int sumLivingCell() {
		int livingCells=0;
		for (Direction d: Direction.values()){
			if(neighbors.get(d).isAlive()){
				livingCells++;
			}
		}
		return livingCells;
	}

	@Override
	public void translate(Lifecycle nextLifecycle) {
		this.lifecycle = nextLifecycle;
	}

}
