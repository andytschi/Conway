package de.empusa.conway.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.empusa.conway.api.Cell;
import de.empusa.conway.api.CellFactory;
import de.empusa.conway.api.Lifecycle;
import de.empusa.conway.api.Universe;


class UniverseImpl implements Universe {
	private List<Cell> _matrix;
	private CellFactory cellFactory;
	
	public UniverseImpl(Lifecycle[] matrix, CellFactory cellFactory) {
		super();
		this.cellFactory = cellFactory;
		init(matrix);
	}

	public UniverseImpl(Lifecycle[] matrix) {
		this(matrix, CellFactoryImpl.getFactory());
	}

	public UniverseImpl() {
		super();
		
	}


	private void init(Lifecycle[] matrix) {
		this._matrix = new ArrayList<Cell>();
		for (int i = 0; i < matrix.length; i++) {
			Lifecycle lifecycle = matrix[i];
			this._matrix.add(cellFactory.createCell(lifecycle));
		}
	}

	@Override
	public Iterator<Cell> iterator() {
		return _matrix.iterator();
	}
}
