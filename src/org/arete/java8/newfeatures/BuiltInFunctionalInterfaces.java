package org.arete.java8.newfeatures;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BuiltInFunctionalInterfaces {

	public static void main(String[] args) {

		objectNotNullTest();

		predicateLongStringTest();

		methodComposeAfter();

		methodComposeBefore();

		supplierConsumerExample();

		comparatorExample();

		optionalExample();
	}

	private static void objectNotNullTest() {

		Predicate<Person> isNonNull = Objects::nonNull;
		System.out.print("objectNotNullTest: ");
		System.out.println("isNonNull: " + isNonNull.test(new Person()));
	}

	private static void predicateLongStringTest() {

		Predicate<String> isStringLong = (p) -> p.length() > 10;

		System.out.println("predicateLongStringTest: ");
		System.out.println(isStringLong.test("This is a supposed to be a long string"));
		System.out.println(isStringLong.negate().test("Short"));
	}

	private static void methodComposeAfter() {

		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);

		System.out.print("methodComposeAfter: ");
		System.out.println(backToString.apply("450"));
	}

	private static void methodComposeBefore() {

		Function<List<Integer>, Integer> sumFunction = BuiltInFunctionalInterfaces::sumIntegersInList;

		Function<String, Integer> stringSumFunction =
									sumFunction.compose(BuiltInFunctionalInterfaces::extractInteger);

		int sumOfList = stringSumFunction.apply("45, 60, 15");
		System.out.println("methodComposeBefore: " + sumOfList);
	}

	private static List<Integer> extractInteger(String intValues) {

		List<Integer> values = new ArrayList<>();
		String[] intVals = intValues.split(",");
		for (String intVal : intVals) {

			values.add(Integer.valueOf(intVal.trim()));
		}

		return values;
	}

	private static Integer sumIntegersInList(List<Integer> list) {

		return list.stream().mapToInt(i -> i.intValue()).sum();
	}

	private static void supplierConsumerExample() {
		Supplier<Person> personSupplier = Person::new;
		System.out.println(personSupplier.get());

		Consumer<Person> greetPerson = (p) -> System.out.println("Hello " + p.firstName);
		greetPerson.accept(new Person("Luke", "Skywalker"));
	}

	private static void comparatorExample() {
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

		Person person1 = new Person("Grant", "Walker");
		Person person2 = new Person("Peter", "Rusk");

		System.out.println("First comparison: " + comparator.compare(person1, person2));
		System.out.println("First comparison: " + comparator.reversed().compare(person1, person2));
	}

	private static void optionalExample() {
		Optional<Long> optional = Optional.of(Long.valueOf(245));
		System.out.println("Is optional present: " + optional.isPresent());
		System.out.println("Optional value: " + optional.get());
		System.out.println("Optional alternative: " + optional.orElse(Long.valueOf(450)));

		optional.ifPresent((l) -> System.out.println("First digit: " + l.longValue() % 10) );
	}

	public static class Person {
		
		private String firstName;
		private String lastName;
		
		public Person() {
			
			this.firstName = "John";
			this.lastName = "Doe";
		}
		
		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}
		
		public String toString() {
			return String.format("%s %s", firstName, lastName);
		}
	}
}