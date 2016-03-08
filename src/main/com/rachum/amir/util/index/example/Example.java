/**
 * 
 */
package com.rachum.amir.util.index.example;

import java.util.Arrays;
import java.util.List;

import com.rachum.amir.util.index.IndexedElement;
import com.rachum.amir.util.index.Indexer;

/**
 * @author Rachum
 *
 */
public class Example {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final List<String> list = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
		for (final IndexedElement<String> element : new Indexer<String>(list)) {
			System.out.println(element.getElement() + " : " + element.getIndex());
		}
	}

}
