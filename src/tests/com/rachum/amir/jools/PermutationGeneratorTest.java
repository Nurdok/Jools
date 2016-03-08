/**
 * 
 */
package com.rachum.amir.util.permutation.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rachum.amir.util.permutation.PermutationGenerator;


/**
 * @author Rachum
 *
 */
public class PermutationGeneratorTest {

	private PermutationGenerator<String> pg;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pg = new PermutationGenerator<String>("a", "b", "c");
	}

	/**
	 * Test method for {@link com.rachum.amir.util.permutation.PermutationGenerator#next()}.
	 */
	@Test
	public void testNext() {
		assert(pg.hasNext());
		List<String> permutation = pg.next();
		assertEquals(permutation, Arrays.asList("a", "b", "c"));
		assert(pg.hasNext());
		permutation = pg.next();
		assertEquals(permutation, Arrays.asList("a", "c", "b"));
	}
	
	@Test
	public void testGet() {
		assertEquals(Arrays.asList("a", "b", "c"), pg.get(0));
		assertEquals(Arrays.asList("a", "c", "b"), pg.get(1));
		assertEquals(Arrays.asList("b", "a", "c"), pg.get(2));
		assertEquals(Arrays.asList("b", "c", "a"), pg.get(3));
		assertEquals(Arrays.asList("c", "a", "b"), pg.get(4));
		assertEquals(Arrays.asList("c", "b", "a"), pg.get(5));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalGetMinus() {
		pg.get(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalGetTooBig() {
		pg.get(6);
	}

}
