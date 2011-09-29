/**
 * 
 */
package com.rachum.amir.util.range.example;

import com.rachum.amir.util.range.Range;

/**
 * @author Rachum
 *
 */
public class Example {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		for (final int i : new Range(0,10,2)) {
			System.out.println(i);
		}
		
		System.out.println((new Range(1,11)).toList());
	}

}
