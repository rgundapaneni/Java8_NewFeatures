package org.arete.java8.newfeatures;

public class MethodAndConstructorReferences {

	public static void main(String[] args) {
	
		Something st = new Something("->");
		
		Converter<String, String> converter = st::startsWith;
		System.out.println(converter.convert("ABC"));
		
		PersonFactory<Person> personFactory = Person::new;
		
		Person person = personFactory.create("Peter", "Parker");
		System.out.println(person);
	}
	
	@FunctionalInterface
	public interface Converter<F, T> {
		
		T convert(F from);
	}
	
	public static class Something {
		
		private String prefix;
		
		public Something(String prefix) {
			this.prefix = prefix;
		}
		
		public String startsWith(String inputStr) {
			
			return this.prefix + inputStr.charAt(0);
		}
	}
	
	public static class Person {
		
		String firstName;
		String lastName;
		
		Person() { this.firstName = "Finding"; this.lastName = "Nemo"; }
		
		Person(String firstName, String lastName) {
			
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		public String toString() {
			
			return this.firstName + " " + this.lastName;
		}
	}
	
	@FunctionalInterface
	public interface PersonFactory<P extends Person> {
		
		P create(String firstName, String lastName);
	}
}