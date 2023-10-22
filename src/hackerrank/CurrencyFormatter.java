package hackerrank;

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class CurrencyFormatter {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
		 * class should be named Solution.
		 */
		Scanner scn = new Scanner(System.in);
		double money = scn.nextDouble();
		scn.close();

		String us = NumberFormat.getCurrencyInstance(Locale.US).format(money);
		String in = NumberFormat.getCurrencyInstance(new Locale("en", "IN")).format(money);
		String cn = NumberFormat.getCurrencyInstance(Locale.CHINA).format(money);
		String fr = NumberFormat.getCurrencyInstance(Locale.FRANCE).format(money);

		System.out.println("US: " + us);
		System.out.println("India: " + in);
		System.out.println("China: " + cn);
		System.out.println("France: " + fr);

	}
}