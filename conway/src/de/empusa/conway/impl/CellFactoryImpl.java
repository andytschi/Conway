package de.empusa.conway.impl;

import de.empusa.conway.api.Cell;
import de.empusa.conway.api.CellFactory;
import de.empusa.conway.api.Lifecycle;

class CellFactoryImpl implements CellFactory {
	private static final CellFactory cellFactory = new CellFactoryImpl();
	private CellFactoryImpl() {
		
	}
	public static CellFactory getFactory(){
		return cellFactory;
	}
	/* (non-Javadoc)
	 * @see de.empusa.conway.impl.CellFactory#createCell(de.empusa.conway.api.Lifecycle)
	 */
	@Override
	public Cell createCell(Lifecycle lifecycle){
		return new CellImpl(lifecycle);
	}
	
	
}
