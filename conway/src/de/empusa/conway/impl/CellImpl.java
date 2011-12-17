package de.empusa.conway.impl;

import de.empusa.conway.api.Cell;
import de.empusa.conway.api.Lifecycle;


class CellImpl implements Cell {
	private Lifecycle lifecycle;

	public CellImpl(Lifecycle lifecycle) {
		super();
		this.lifecycle = lifecycle;
	}

	/**
	 * Erzeugt eine tote Zelle.
	 */
	public CellImpl() {
		super();
		lifecycle = Lifecycle.DEAD;
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

}
