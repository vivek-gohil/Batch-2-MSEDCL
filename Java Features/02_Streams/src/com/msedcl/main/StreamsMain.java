package com.msedcl.main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.msedcl.main.domain.Person;

public class StreamsMain {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("Vivek", "Bahubali", "Trupti", "Samarth");

		// Print all except Bahubali
		for (String name : names) {
			if (!name.equals("Bahubali"))
				System.out.println(name);
		}

		System.out.println();

		names.stream().filter((name) -> {
			if (!name.equals("Bahubali")) {
				return true;
			} else {
				return false;
			}
		}).forEach((name) -> System.out.println(name));

		System.out.println();

		names.stream().filter((name) -> !name.equals("Bahubali")).forEach(n -> System.out.println(n));

		System.out.println();

		List<String> filterd = names.stream().filter((name) -> !name.equals("Bahubali")).collect(Collectors.toList());

		filterd.forEach(n -> System.out.println(n));
	}
}
