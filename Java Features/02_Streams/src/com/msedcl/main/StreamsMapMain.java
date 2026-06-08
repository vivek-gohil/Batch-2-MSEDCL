package com.msedcl.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.msedcl.main.domain.Person;

public class StreamsMapMain {
	public static void main(String[] args) {
		List<String> alphabets = Arrays.asList("a", "b", "c", "d", "e");

		// Without Streams/Lambda
		// Convert this alphabets into upper case and add it into new list
		// Print new list;
		List<String> upperAlphabets = new ArrayList<>();
		for (String s : alphabets) {
			upperAlphabets.add(s.toUpperCase());
		}
		for (String s : upperAlphabets) {
			System.out.println(s);
		}

		System.out.println("-".repeat(80));
		upperAlphabets = alphabets.stream().map(new Function<String, String>() {
			@Override
			public String apply(String t) {
				// TODO Auto-generated method stub
				return t.toUpperCase();
			}
		}).collect(Collectors.toList());
		upperAlphabets.forEach(s -> System.out.println(s));

		System.out.println("-".repeat(80));

		upperAlphabets = alphabets.stream().map(
				s -> s.toUpperCase()).
				collect(Collectors.toList());
		
		upperAlphabets.forEach(s -> System.out.println(s));

		// Create List of Person class
		List<Person> people = Arrays.asList(new Person("Vivek", "Gohil", 36), new Person("Trupti", "Acharekar", 38),
				new Person("Samarth", "Patil", 10), new Person("Gurubux", "Gill", 30));

		
		//Create new list of person with Uppercase firstName and lastName 
		//Print new list
		people.stream().map(p -> {
			p.setFirstName(p.getFirstName().toUpperCase());
			p.setLastName(p.getLastName().toUpperCase());
			return p;
		}).collect(Collectors.toList());
	}
}
