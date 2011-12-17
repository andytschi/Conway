package de.empusa.conway.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.empusa.conway.Cell;
import de.empusa.conway.Universe;

public class UniverseImpl implements Universe {
	private List<Cell> _matrix;

	public UniverseImpl(Lifecycle[] matrix) {
		super();
		this._matrix = new ArrayList<Cell>();
		for (int i = 0; i < matrix.length; i++) {
			Lifecycle lifecycle = matrix[i];
			this._matrix.add(new CellImpl(lifecycle));
		}
	}

	@Override
	public Iterator<Cell> iterator() {
		return _matrix.iterator();
	}
}
