package com.msedcl.main;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.msedcl.main.domain.Person;

public class StreamsSortMain {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("9", "A", "X", "1", "B", "4", "a", "c");

		// Sort above list without using lambda/stream
		Collections.sort(list);

		list.forEach(c -> System.out.println(c));

		System.out.println("Option 1 - Sort and Collect into new list");
		// Option 1 - Collect into new list
		List<String> sortedListOne = list.stream().sorted().collect(Collectors.toList());
		sortedListOne.forEach(c -> System.out.println(c));

		System.out.println("Option 2 - Sort and Collect into new list");
		// Option 2 - Collect into new list
		List<String> sortedListTwo = list.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
		sortedListTwo.forEach(c -> System.out.println(c));

		System.out.println("Option 3 - Sort and Collect into new list");
		// Option 3 - Collect into new list
		List<String> sortedListThree = list.stream().sorted((s1, s2) -> s1.compareTo(s2)).collect(Collectors.toList());
		sortedListThree.forEach(c -> System.out.println(c));

		System.out.println("Option 4 - Sort - Reverse order and Collect into new list");
		List<String> sortedListFour = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		sortedListFour.forEach(c -> System.out.println(c));

		// Create List of Person class
		List<Person> people = Arrays.asList(new Person("Vivek", "Gohil", 36), new Person("Trupti", "Acharekar", 38),
				new Person("Samarth", "Patil", 10), new Person("Gurubux", "Gill", 30));

		// Sort by age and store in new list = PeopleSortedByAgeList
		System.out.println("Sort by age and store in new list = PeopleSortedByAgeList");
		List<Person> peopleSortedByAgeList = people.stream()
				.sorted((p1, p2) -> p1.getAge() - p2.getAge())
				.collect(Collectors.toList());
		peopleSortedByAgeList.forEach(p -> System.out.println(p));

		System.out.println("Sort by first and store in new list = PeopleSortedByFirstNameList");
		List<Person> peopleSortedByFistNameList = people.stream()
				.sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()))
				.collect(Collectors.toList());
		peopleSortedByFistNameList.forEach(p -> System.out.println(p));

	}
}
