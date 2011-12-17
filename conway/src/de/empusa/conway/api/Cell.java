package de.empusa.conway.api;


/**
 * Eine Zelle kann tot oder lebendig sein. 
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
