package de.empusa.conway;

import de.empusa.conway.impl.Lifecycle;

/**
 * Eine Zelle kann tot oder lebendig sein. Im Grundzustand ist eine Zelle erst einmal tot.
 * @author agutheil
 *
 */
public interface Cell {

	void die();

	boolean isDead();

	void awake();

	boolean isAlive();

	Lifecycle getLifecycle();

}
