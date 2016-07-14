package org.arete.java8.newfeatures;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExprIntroInComparator {

	public static void main(String[] args) {
		
		List<String> planets = Arrays.asList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune");
		sortOne(planets);
		sortTwo(planets);
		sortThree(planets);
	}

	private static void sortOne(List<String> planets) {
		
		Collections.sort(planets, new Comparator<String>() {

			@Override
			public int compare(String planet1, String planet2) {
					return planet1.compareTo(planet2);
			}
		});
	
		System.out.println("Sort One");
		System.out.println(planets);
	}
	
	private static void sortTwo(List<String> planets) {
		
		Collections.sort(planets, (String planet1, String planet2) -> { return planet1.compareTo(planet2); });
		
		System.out.println("Sort Two");
		System.out.println(planets);
	}
	
	private static void sortThree(List<String> planets) {
		
		Collections.sort(planets, (planet1, planet2) -> planet1.compareTo(planet2) );
		
		System.out.println("Sort Three");
		System.out.println(planets);
	}
}