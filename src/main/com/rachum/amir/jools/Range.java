package com.rachum.amir.jools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Rachum
 *
 */
public class Range implements Iterable<Integer>, Iterator<Integer> {
	final int start, stop, step;
	int next;

	public Range(final Integer stop) {
		this(0, stop, 1);
	}

	public Range(final Integer start, final Integer stop) {
		this(start, stop, 1);
	}

	public Range(final Integer start, final Integer stop, final Integer step) {
		if (step.equals(0)) {
			throw new IllegalArgumentException();
		}
		this.start = start;
		this.stop = stop;
		this.step = step;
		this.next = start;
	}

	public List<Integer> toList() {
		final List<Integer> list = new ArrayList<Integer>();
		for (int i = start; (step > 0) ? i < stop : i > stop; i += step) {
			list.add(i);
		}
		return list;
	}

	@Override
	public boolean hasNext() {
		return (next != stop);
	}

	@Override
	public Integer next() {
		if (next == stop) {
			throw new NoSuchElementException();
		}
		final Integer ret = next;
		next += step;
		return ret;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<Integer> iterator() {
		return this;
	}

}
