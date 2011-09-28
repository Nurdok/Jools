/**
 * 
 */
package com.rachum.amir.util.permutation.example;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.rachum.amir.util.permutation.PermutationGenerator;

/**
 * @author Rachum
 *
 */
public class TravelingSalesmanProblem {
	
	private final Map<String, Point> citiesCoord;

	public TravelingSalesmanProblem() {
		super();
		citiesCoord = new HashMap<String, Point>();
		
		final Random rand = new Random();
		final int numOfCities = Math.max(4, rand.nextInt(20));
		for (int i=0; i<numOfCities; ++i) {
			citiesCoord.put("A" + i, new Point(rand.nextInt(100),rand.nextInt(100)));
			System.out.println("A" + i + " (" + citiesCoord.get("A" + i).getX() + ", " + citiesCoord.get("A" + i).getY() + ")");
		}
	}
	
	private double accumulateDistance(final List<String> route, final int cityIndex) {
		final Point p1 = citiesCoord.get(route.get(cityIndex));
		Point p2 = null;
		if (cityIndex == 0) {
			p2 = citiesCoord.get(route.get(route.size()-1));
		} else {
			p2 = citiesCoord.get(route.get(cityIndex-1));
		}
		return p1.distance(p2);
	}
	
	private int calculateRouteLength(final List<String> route) {
		int length = 0;
		for (int i=0; i<route.size(); ++i) {
			length += accumulateDistance(route, i);
		}
		return length;
	}
	
	public List<String> naiveSolve() {
		int minRouteLength = Integer.MAX_VALUE;
		List<String> minRoute = null;
		final PermutationGenerator<String> routeGenerator =
			new PermutationGenerator<String>(new ArrayList<String>(citiesCoord.keySet()));
		
		for (final List<String> route : routeGenerator) {
			final int routeLength = calculateRouteLength(route);
			if (routeLength < minRouteLength) {
				minRouteLength = routeLength;
				minRoute = route;
			}
		}
		System.out.println("Best route is " + minRoute + ", length is " + minRouteLength);
		return minRoute;
	}
	
	
	public List<String> thresholdSolve() {
		final PermutationGenerator<String> routeGenerator =
			new PermutationGenerator<String>(new ArrayList<String>(citiesCoord.keySet()));
		
		int threshold = Integer.MAX_VALUE;
		List<String> bestRoute = routeGenerator.get(0);
		boolean skip = false;
		int routesChecked = 0;
		int i = 0;
		while (i<routeGenerator.size()/citiesCoord.size()) {
			routesChecked++;
			final List<String> route = routeGenerator.get(i);
			int length = 0;
			skip = false;
			for (int j=0; j<route.size(); ++j) {
				length += accumulateDistance(route, j);
				
				if (length > threshold && threshold != Integer.MAX_VALUE) {
					final int routesToSkip = PermutationGenerator.factorial(route.size() - j);
					System.out.println("Route " + i + ": " + route + " is too long (length is " + length + " at " + route.get(j) +"), advancing " + routesToSkip + " routes.");
					i += routesToSkip;
					skip = true;
					break;
				}
			}
			if (!skip) {
				if (length < threshold) {
					threshold = length;
					bestRoute = route;
					System.out.println("Route " + i + ": " + route + " is a new minimum, setting threshold at " + threshold);
				}
				++i;
			}
		}
		System.out.println("Best route is " + bestRoute + ", length is " + threshold);
		System.out.println("Checked " + routesChecked + " out of " + routeGenerator.size()/citiesCoord.size() + " possible routes.");
		return bestRoute;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final TravelingSalesmanProblem tsp = new TravelingSalesmanProblem();
		final List<String> naiveRoute = tsp.naiveSolve();
		final List<String> thresholdRoute = tsp.thresholdSolve();
		System.out.println("\n\nNaive solution: " + naiveRoute);
		System.out.println("Threshold solution: " + thresholdRoute);
		assert(tsp.calculateRouteLength(naiveRoute) == tsp.calculateRouteLength(thresholdRoute));
	}

}
