/**
 * 
 */
package com.rachum.amir.util.permutation.example;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rachum.amir.util.permutation.PermutationGenerator;

/**
 * @author Rachum
 *
 */
public class TravelingSalesmanProblem {
	
	private Map<String, Point> citiesCoord;

	public TravelingSalesmanProblem(final int numOfCities) {
		super();
		//TODO generate cities randomly
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		//List<String> route;
		final TravelingSalesmanProblem tsp = new TravelingSalesmanProblem(10);
		final PermutationGenerator<String> routeGenerator =
			new PermutationGenerator<String>(new ArrayList<String>(tsp.citiesCoord.keySet()));
		
		final int threshold = Integer.MAX_VALUE;
		List<String> bestRoute = routeGenerator.get(0);
		boolean skip = false;
		for (int i=0; i<routeGenerator.size(); ++i) {
			final List<String> route = routeGenerator.get(i);
			int length = 0;
			for (int j=1; i<route.size(); ++j) {
				final Point p1 = tsp.citiesCoord.get(route.get(j));
				final Point p2 = tsp.citiesCoord.get(route.get(j-1));
				length += p1.distance(p2);
				
				if (length > threshold) {
					i += factorial(route.size() - j) - 1;
					skip = true;
					break;
				}
			}
			if (!skip) {
				threshold = length;
				bestRoute = route;
				skip = false;
			}
		}
		System.out.println();
	}

}
