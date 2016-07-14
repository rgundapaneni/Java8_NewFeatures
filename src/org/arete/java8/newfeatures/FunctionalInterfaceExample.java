package org.arete.java8.newfeatures;

public class FunctionalInterfaceExample {

	public static void main(String[] args) {
		
		Converter<String, Integer> converter = (String from) -> Integer.valueOf(from);
		System.out.println(converter.convert("3232"));
		
		Converter<String, Double> converter2 = (String from) -> sumDigits(from);
		System.out.println(converter2.convert("123456789"));
		
		Converter<String, Double> converter3 = FunctionalInterfaceExample::sumDigits;
		System.out.println(converter3.convert("1234"));
	}

	@FunctionalInterface
	public interface Converter<F, T> {
		
		T convert(F from);
	}
	
	private static Double sumDigits(String numberStr) {
		
		int sumOfDigits = 0;
		Integer inputInteger = Integer.valueOf(numberStr);
		
		while(inputInteger > 0) {
			
			int currentDigit = inputInteger % 10;
			inputInteger = (inputInteger / 10);
			
			sumOfDigits += currentDigit;
		}
		
		return sumOfDigits + .59;
	}
}