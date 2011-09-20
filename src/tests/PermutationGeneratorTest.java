/**
 * 
 */
package tests;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import permutations.PermutationGenerator;

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
		pg = new PermutationGenerator<String>(Arrays.asList("a", "b", "c"));
	}

	/**
	 * Test method for {@link permutations.PermutationGenerator#next()}.
	 */
	@Test
	public void testNext() {
		for (final List<String> permutation : pg) {
			System.out.println(permutation);
		}
		System.out.println(pg.get(3));
		fail("Not yet implemented"); // TODO
	}

}
