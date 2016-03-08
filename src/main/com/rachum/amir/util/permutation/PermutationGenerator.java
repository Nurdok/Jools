package com.rachum.amir.util.permutation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Gets a list of objects and allows to either iterate over all the
 * permutations on it or get specific permutation by lexicographic ordering.
 *
 * @author Amir Rachum
 * @version 1.0
 * 
 * @param <T> the type of objects in the permutations
 */
public class PermutationGenerator<T> implements Iterator<List<T>>, Iterable<List<T>> {
	
	private final List<T> permutationObjects;
	private final int permutationSize;
	private int currentPermutationIndex = -1; //TODO: think about numbering
	private final int lastPermutationIndex;
	private final List<T> permutation;
	
	/**
	 * @param ts a list of objects
	 * 
	 * @see PermutationGenerator#PermutationGenerator(List)
	 */
	public PermutationGenerator(final T... ts) {
		this(Arrays.asList(ts));
	}

	/**
	 * @param list a list of objects.
	 * The order of the list will define the natural ordering of the objects.
	 * i.e., the given list order is considered the first permutation.
	 * 
	 * @see PermutationGenerator#PermutationGenerator(Object...)
	 */
	public PermutationGenerator(final List<T> list) {
		this.permutationObjects = list;
		this.permutationSize = list.size();
		this.lastPermutationIndex = factorial(permutationSize) - 1;
		permutation = new LinkedList<T>();
		permutation.addAll(list);
	}

	//TODO: replace with fast implementation
	private static int factorial(final int n) {
		return (n == 1 || n == 0) ? 1 : factorial(n-1) * n;
	}
	
	@Override
	public boolean hasNext() {
		if (currentPermutationIndex < lastPermutationIndex) {
			return true;
		}
		return false;
	}

	@Override
	public List<T> next() {
		if (!this.hasNext()) {
			throw new NoSuchElementException();
		}
		currentPermutationIndex++;
		if (currentPermutationIndex == 0) {
			return permutation;
		}
		
		//Find the largest index k such that a[k] < a[k + 1].
		final int k = findMaxK();
		if (k == -1) {
			throw new RuntimeException("Called next when there are no more permutations");
		}
		
		//Find the largest index l such that a[k] < a[l]. Since k + 1 is such an index, l is well defined and satisfies k < l.
		final int l = findMaxL(k);
		
		//Swap a[k] with a[l].
		swap(k,l);
		
		//Reverse the sequence from a[k + 1] up to and including the final element a[n].
		reverseFrom(k+1);
		
		return permutation;
	}
	
	private List<T> permutationByIndex(int index) {
		if (index > lastPermutationIndex || index < 0) {
			throw new IllegalArgumentException();
		}
		final List<T> permutation = new LinkedList<T>();
		final List<T> permutationObjectsBank = new LinkedList<T>(permutationObjects);
		
		for (int i=0; i<permutationSize; ++i) {
			final int f = factorial((permutationSize - i - 1));
			final int objectIndex = index / f;
			index %= f;
			final T t = permutationObjectsBank.get(objectIndex);
			permutationObjectsBank.remove(objectIndex);
			permutation.add(t);
		}
		
		return permutation;
	}
	
	private void reverseFrom(final int i) {
		Collections.reverse(permutation.subList(i, permutationSize));
	}

	private void swap(final int k, final int l) {
		final T tK = permutation.get(k);
		final T tL = permutation.get(l);
		permutation.set(l, tK);
		permutation.set(k, tL);
	}

	//Find the largest index l such that a[k] < a[l]. Since k + 1 is such an index, l is well defined and satisfies k < l.
	private int findMaxL(final int k) {
		int l = k+1;
		final T tK = permutation.get(k);
		for (int i=k+1; i<permutationSize; ++i) {
			final T tL= permutation.get(i);
			if (permutationObjects.indexOf(tK) < permutationObjects.indexOf(tL)) {
				l = i;
			}
		}
		return l;
	}

	private int findMaxK() {
		int k = -1;
		for (int i=0; i<permutationSize-1; ++i) {
			final T tK = permutation.get(i);
			final T tKNext= permutation.get(i+1);
			if (permutationObjects.indexOf(tK) < permutationObjects.indexOf(tKNext)) {
				k = i;
			}
		}
		return k;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<List<T>> iterator() {
		return this;
	}
	
	/**
	 * @param index the index of the requested permutation, in lexicographic
	 * order.
	 * @return the permutation with the specified index.
	 */
	public List<T> get(final int index) {
		return permutationByIndex(index);
	}
	
	/**
	 * @return the number of permutations available.
	 */
	public int size() {
		return lastPermutationIndex+1;
	}
}
