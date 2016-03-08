/**
 * 
 */
package com.rachum.amir.util.index;

/**
 * @author Rachum
 *
 */
public class IndexedElement<T> {
	private final T element;
	private final int index;

	public IndexedElement(final T element, final int index) {
		this.element = element;
		this.index = index;
	}

	/**
	 * @return the element
	 */
	public T getElement() {
		return element;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	
}
