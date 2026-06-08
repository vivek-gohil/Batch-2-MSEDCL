package com.msedcl.main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.msedcl.main.domain.Student;

public class StudentStreamMain {
	public static void main(String[] args) {
		List<Student> studentList = Stream.of(
				new Student(1, "Rohit", 30, "Male", "Mechanical Engineering", "Mumbai", 122,
						Arrays.asList("+912632632782", "+1673434729929")),
				new Student(2, "Pulkit", 56, "Male", "Computer Engineering", "Delhi", 67,
						Arrays.asList("+912632632762", "+1673434723929")),
				new Student(3, "Ankit", 25, "Female", "Mechanical Engineering", "Kerala", 164,
						Arrays.asList("+912632633882", "+1673434709929")),
				new Student(4, "Satish Ray", 30, "Male", "Mechanical Engineering", "Kerala", 26,
						Arrays.asList("+9126325832782", "+1671434729929")),
				new Student(5, "Roshan", 23, "Male", "Biotech Engineering", "Mumbai", 12,
						Arrays.asList("+012632632782")),
				new Student(6, "Chetan", 24, "Male", "Mechanical Engineering", "Karnataka", 90,
						Arrays.asList("+9126254632782", "+16736784729929")),
				new Student(7, "Arun", 26, "Male", "Electronics Engineering", "Karnataka", 324,
						Arrays.asList("+912632632782", "+1671234729929")),
				new Student(8, "Nam", 31, "Male", "Computer Engineering", "Karnataka", 433,
						Arrays.asList("+9126326355782", "+1673434729929")),
				new Student(9, "Sonu", 27, "Female", "Computer Engineering", "Karnataka", 7,
						Arrays.asList("+9126398932782", "+16563434729929", "+5673434729929")),
				new Student(10, "Shubham", 26, "Male", "Instrumentation Engineering", "Mumbai", 98,
						Arrays.asList("+912632646482", "+16734323229929")))
				.collect(Collectors.toList());

		studentList.forEach(s -> System.out.println(s));
		System.out.println("-".repeat(80));
		// 1. Find the List of student whose rank is between 50 to 100
		System.out.println("1. Find the List of student whose rank is between 50 to 100");
		List<Student> filteredByRank = studentList.stream()
				.filter(s -> s.getRank() > 50 && s.getRank() < 100)
				.collect(Collectors.toList());
		filteredByRank.forEach(s -> System.out.println(s));
		
		System.out.println();
		// 2. Find the students whose stays in Karnataka and sort them by their names
		System.out.println("2. Find the students whose stays in Karnataka and sort them by their names");
		List<Student> filteredSortByKarnataka = studentList.stream()
				.filter(s -> s.getCity().equals("Karnataka"))
				.sorted((s1,s2) -> s1.getFirstName().compareTo(s2.getFirstName()))
				.collect(Collectors.toList());
		filteredSortByKarnataka.forEach(s-> System.out.println(s));
		
		System.out.println();
		System.out.println(" 3. Find all the departments names");
		// 3. Find all the departments names
		List<String> allDistinctDepartments = studentList.stream()
				.map( s -> s.getDept()).distinct().toList();
		allDistinctDepartments.forEach(s->System.out.println(s));
		
		System.out.println();
		// 4. Find all the contact number
		System.out.println("4. Find all the contact number");
		List<List<String>> contactNumberList = studentList.stream()
				.map(s -> s.getContacts())
				.collect(Collectors.toList());
		contactNumberList.forEach(s -> System.out.println(s));
		
		System.out.println();
		// 5. Group the student by the department names
		System.out.println(" 5. Group the student by the department names");
		Map<String, List<Student>> studentsByDepartment = studentList.stream()
				.collect(Collectors.groupingBy(s -> s.getDept()));
		studentsByDepartment.forEach((d,sList) -> {
			System.out.println(d);
			sList.forEach(s->System.out.println(s));
		});
		
		System.out.println();
		// 6. Find the average age of male and female student
		System.out.println("6. Find the average age of male and female student");
		Map<String, Double> groupByGenderWithAvgAge = studentList.stream()
				.collect(
						Collectors.groupingBy(s -> s.getGender() ,
						Collectors.averagingDouble(s-> s.getAge())));
		groupByGenderWithAvgAge.forEach(
				(g,avg) -> 
				System.out.println("Gender :: " + g + "Avg Age :: " + avg));
		
	
		System.out.println();
		// 7. Find the highest rank in each department
		System.out.println("7. Find the highest rank in each department");
		Map<String, Optional<Student>> listOfHighestRankByDepartment = studentList.stream().collect(
				Collectors.groupingBy(s -> s.getDept() ,
				Collectors.minBy((s1,s2) -> s1.getRank() - s2.getRank())
		));
		listOfHighestRankByDepartment.forEach((department,optionalStudent) -> {
			System.out.println("Department Name :: " + department );
			if(optionalStudent.isPresent())
				System.out.println(optionalStudent.get());
			
		});
		
		System.out.println();
		// 8. Find the student who has the second rank
		System.out.println("8. Find the student who has the second rank");
		var student =  studentList.stream()
				.sorted((s1,s2) -> s1.getRank() - s2.getRank())
				.skip(1)
				.findFirst()
				.get();
		System.out.println(student);
		
	}
}
