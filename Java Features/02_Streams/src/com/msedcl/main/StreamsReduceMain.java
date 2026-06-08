package com.msedcl.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.IntBinaryOperator;

public class StreamsReduceMain {
	public static void main(String[] args) {

		int[] numbers = { 1, 2, 3, 4, 5, 6 };

		// print sum of all numbers of array
		int result = Arrays.stream(numbers).reduce(new IntBinaryOperator() {

			@Override
			public int applyAsInt(int left, int right) {
				System.out.println("left = " + left + " :: right = " + right );
				return left + right;
			}
		}).getAsInt();
		
		System.out.println("Sum  = " + result);
		
		System.out.println("-".repeat(80));
		
		result = Arrays.stream(numbers).reduce((l,r) -> l+r).getAsInt();
		System.out.println("Sum = " + result);
		
	}
}
