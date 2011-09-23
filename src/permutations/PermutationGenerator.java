package permutations;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PermutationGenerator<T> implements Iterator<List<T>>, Iterable<List<T>> {
	
	private final List<T> permutationObjects;
	private final int permutationSize;
	private int currentPermutationIndex = -1; //TODO: think about numbering
	private final int lastPermutationIndex;
	private final List<T> permutation;

	public PermutationGenerator(final List<T> list) {
		this.permutationObjects = list;
		this.permutationSize = list.size();
		this.lastPermutationIndex = factorial(permutationSize);
		permutation = new LinkedList<T>();
		permutation.addAll(list);
	}

	public List<T> getPermutation(final int index) {
		final PermutationGenerator<T> pg = new PermutationGenerator<T>(permutationObjects);
		List<T> permutationByIndex = null;
		for (int i=0; i<index; ++i) {
			permutationByIndex = pg.next();
		}
		return permutationByIndex;
	}
	
	//TODO: replace with fast implementation
	public int factorial(final int n) {
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
	
	private List<T> permutationByIndex(int index)
	{
		final List<T> permutation = new LinkedList<T>(permutationObjects);
	    int r = 1, m;
	    
	    while (r < permutationSize) {
			m = index % (r+1);
	        index = index / (r+1);
	        final T tR = permutation.get(r);
	        final T tM = permutation.get(m);
	        permutation.set(r, tM);
	        permutation.set(m, tR);
	        r++;
	    }
	    return permutation;
	}
	
	private List<T> permutationByIndex2(int index) {
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
	
	public List<T> get(final int index) {
		return permutationByIndex2(index);
	}
}
