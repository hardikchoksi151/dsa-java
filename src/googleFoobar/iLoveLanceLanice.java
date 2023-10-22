package googleFoobar;

import java.util.Scanner;

public class iLoveLanceLanice {
	public static String solution(String x) {

		char[] arr = new char[x.length()];
		
		for (int i=0;i<x.length();i++) {
			if (x.charAt(i) < 'a' || x.charAt(i) > 'z') {
				arr[i] = x.charAt(i);
			} else {
				int count = x.charAt(i) - 97;
				arr[i] = (char)(122 - count);
			}
		}
		
		return String.valueOf(arr);
	}
	
	public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		String str = scn.nextLine();
		String ans = solution(str);
		System.out.println(ans);
		scn.close();
		
	}
}
