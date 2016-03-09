package com.rachum.amir.jools;

import java.util.Iterator;

/**
 * @author Rachum
 *
 */
public class Indexer<T> implements Iterable<IndexedElement<T>>, Iterator<IndexedElement<T>> {
	private int index = 0;
	private final Iterator<T> iterator;

	public Indexer(final Iterable<T> wrapped) {
		super();
		this.iterator = wrapped.iterator();
	}

	@Override
	public Iterator<IndexedElement<T>> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public IndexedElement<T> next() {
		return new IndexedElement<T>(iterator.next(), index++);
	}

	@Override
	public void remove() {
		// TODO: index?
		// TODO: throw exception?
		iterator.remove();
	}

}
