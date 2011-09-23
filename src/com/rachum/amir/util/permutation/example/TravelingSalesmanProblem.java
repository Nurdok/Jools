/**
 * 
 */
package com.rachum.amir.util.permutation.example;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rachum.amir.util.permutation.PermutationGenerator;

/**
 * @author Rachum
 *
 */
public class TravelingSalesmanProblem {
	
	private final Map<String, Point> citiesCoord;

	public TravelingSalesmanProblem(final int numOfCities) {
		super();
		citiesCoord = new HashMap<String, Point>();
		citiesCoord.put("A", new Point(0,0));
		citiesCoord.put("B", new Point(2,9));
		citiesCoord.put("C", new Point(8,5));
		citiesCoord.put("D", new Point(2,10));
		citiesCoord.put("E", new Point(2,3));
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		//List<String> route;
		final TravelingSalesmanProblem tsp = new TravelingSalesmanProblem(10);
		final PermutationGenerator<String> routeGenerator =
			//new PermutationGenerator<String>(Arrays.asList("A","B","C","D","E"));
			new PermutationGenerator<String>(new ArrayList<String>(tsp.citiesCoord.keySet()));
		
		int threshold = Integer.MAX_VALUE;
		List<String> bestRoute = routeGenerator.get(0);
		boolean skip = false;
		int routesChecked = 0;
		for (int i=0; i<routeGenerator.size()/tsp.citiesCoord.size(); ++i, ++routesChecked) {
			final List<String> route = routeGenerator.get(i);
			int length = 0;
			skip = false;
			for (int j=0; j<route.size(); ++j) {
				final Point p1 = tsp.citiesCoord.get(route.get(j));
				Point p2 = null;
				if (j == 0) {
					p2 = tsp.citiesCoord.get(route.get(route.size()-1));
				} else {
					p2 = tsp.citiesCoord.get(route.get(j-1));
				}
				length += p1.distance(p2);
				
				if (length > threshold && threshold != Integer.MAX_VALUE) {
					i += PermutationGenerator.factorial(route.size() - j + 1) - 1;
					System.out.println("Route " + route + " is too long, skipping " + PermutationGenerator.factorial(route.size() - j + 1) + " routes.");
					skip = true;
					break;
				}
			}
			if (!skip) {
				threshold = length;
				bestRoute = route;
				System.out.println("Route " + route + " is a new minimum, setting threshold at " + threshold);
			}
		}
		System.out.println("Best route is " + bestRoute + ", length is " + threshold);
		System.out.println("Checked " + routesChecked + " out of " + routeGenerator.size()/tsp.citiesCoord.size() + " possible routes.");
	}

}
