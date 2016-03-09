package com.rachum.amir.jools;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Rachum
 *
 */
public class RangeTest {
	private Range r1, r2, r3, r4;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		r1 = new Range(10);
		r2 = new Range(20, 25);
		r3 = new Range(30, 40, 3);
		r4 = new Range(5, -5, -2);
	}

	/**
	 * Test method for {@link com.rachum.amir.util.range.Range#toList()}.
	 */
	@Test
	public void testToList() {
		assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), r1.toList());
		assertEquals(Arrays.asList(20, 21, 22, 23, 24), r2.toList());
		assertEquals(Arrays.asList(30, 33, 36, 39), r3.toList());
		assertEquals(Arrays.asList(5, 3, 1, -1, -3), r4.toList());
	}

	/**
	 * Test method for {@link com.rachum.amir.util.range.Range#next()}.
	 */
	@Test
	public void testNext() {
		for (int i = 0; i < 10; ++i) {
			assertEquals(new Integer(i), r1.next());
		}

		for (int i = 20; i < 25; ++i) {
			assertEquals(new Integer(i), r2.next());
		}

		for (int i = 30; i < 40; i += 3) {
			assertEquals(new Integer(i), r3.next());
		}

		for (int i = 5; i > -5; i -= 2) {
			assertEquals(new Integer(i), r4.next());
		}
	}

	/**
	 * Test method for {@link com.rachum.amir.util.range.Range#next()}.
	 */
	@Test(expected = NoSuchElementException.class)
	public void testNext2() {
		for (int i = 0; i < 11; ++i) {
			r1.next();
		}
	}
}
