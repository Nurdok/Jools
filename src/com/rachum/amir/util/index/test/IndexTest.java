/**
 * 
 */
package com.rachum.amir.util.index.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.rachum.amir.util.index.IndexedElement;
import com.rachum.amir.util.index.Indexer;

/**
 * @author Rachum
 *
 */
public class IndexTest {
	
	/**
	 * @throws java.lang.Exception
	 */
	@Test
	public void loopTest() {
		int i=0;
		final List<String> list = Arrays.asList("A", "B", "C", "D", "E");
		final Iterator<String> it = list.iterator();
		for (final IndexedElement<String> element : new Indexer<String>(list)) {
			assertTrue(it.hasNext());
			assertEquals(i++, element.getIndex());
			assertEquals(it.next(), element.getElement());
		}
	}

}
