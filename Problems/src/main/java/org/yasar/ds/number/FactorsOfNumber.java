package org.yasar.ds.number;

public class FactorsOfNumber {

	public static void main(String[] args) {
		primeFactors(56);
	}
	// A function to print all prime factors of a given number n
	static void primeFactors(int n) {
		// Print the number of 2s that divide n
		while (n % 2 == 0) {
			System.out.print("2 * ");
			n = n / 2;
		}

		// n must be odd at this point. So we can skip one element (Note i = i
		// +2)
		for (int i = 3; i <= Math.sqrt(n); i = i + 2) {
			// While i divides n, print i and divide n
			while (n % i == 0) {
				System.out.print(i + " * ");
				n = n / i;
			}
		}

		// This condition is to handle the case whien n is a prime number
		// greater than 2
		if (n > 2)
			System.out.print(n);
		
	}
}

