package org.arete.java8.newfeatures;

public class DefaultMethodInInterface {

	public static void main(String[] args) {
		
		Formula formula = new Formula() {
			
			@Override
			public double calculate(int input) {
				return this.sqrt(input * 64);
			}
		};
		
		System.out.println(formula.calculate(64));
		System.out.println(formula.sqrt(64));
	}
	
	public interface Formula {
		
		double calculate(int input);
		
		default double sqrt(int input) {
			
			return Math.sqrt(input);
		}
	}
}