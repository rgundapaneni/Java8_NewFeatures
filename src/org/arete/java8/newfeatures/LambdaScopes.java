package org.arete.java8.newfeatures;

public class LambdaScopes {

	public static void main(String[] args) {
		
		int value = 5;
		Converter<Integer, String> stringConverter = (from) -> String.valueOf(from * value);
		System.out.println(stringConverter.convert(15));
		
		Lambda4 lambda4 = new Lambda4();
		lambda4.testScope();
		
		System.out.println(lambda4);
	}
	
	@FunctionalInterface
	public interface Converter<F, T> {
		
		T convert(F from);
	}
	
	public static class Lambda4 {
		
		static int outerStaticNum;
		int outerNum;
		
		public void testScope() {
			
			Converter<Integer, String> stringConverter1 = (from) -> {
				outerStaticNum = 45;
				return String.valueOf(from);
			};
			
			Converter<Integer, String> stringConverter2 = (from) -> {
				outerNum = 65;
				return String.valueOf(from);
			};
			
			stringConverter1.convert(100);
			stringConverter2.convert(1000);
		}
		
		public String toString() {
			
			return String.format("First: %d, second: %d", outerStaticNum, outerNum);
		}
	}
}