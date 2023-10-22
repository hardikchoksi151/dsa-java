package arrayAndStrings;

import java.util.ArrayList;
import java.util.List;

public class TwoDArray {

	public static void main(String[] args) {
//		int[][] mat = { { 1, 2, 3 } };

//		int[] res = findDiagonalOrder(mat);
//		for (int i = 0; i < res.length; i++)	
//			System.out.println(res[i]);

//		System.out.println(spiralOrder(mat).toString());
//		System.out.println(generate(0).toString());
		List<List<Integer>> res = generate(5);
		for (List<Integer> list : res) {
			for (int element : list) {
				System.out.print(element + " ");
			}
			System.out.println();
		}
	}

	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> pre = null, row = null;

		for (int i = 0; i < numRows; i++) {
			row = new ArrayList<Integer>();
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i)
					row.add(1);
				else
					row.add(pre.get(j - 1) + pre.get(j));
			}
			pre = row;
			res.add(row);
		}
		return res;
	}

	public static List<Integer> spiralOrder(int[][] mat) {
		ArrayList<Integer> res = new ArrayList<>();
		int L = 0, R = mat[0].length, T = 0, B = mat.length;

		while (L < R && T < B) {
			// add every element in the top row
			for (int i = L; i < R; i++)
				res.add(mat[T][i]);
			T++;

			// add every element in the right-most column
			for (int i = T; i < B; i++)
				res.add(mat[i][R - 1]);
			R--;

			if (!(L < R && T < B))
				break;

			// add every element in the bottom row
			for (int i = R - 1; i >= L; i--)
				res.add(mat[B - 1][i]);
			B--;

			// add every element in the left-most column
			for (int i = B - 1; i >= T; i--)
				res.add(mat[i][L]);
			L++;
		}
		return res;
	}

	public static int[] findDiagonalOrder(int[][] mat) {
		if (mat == null || mat.length == 0)
			return new int[0];
		int m = mat.length;
		int n = mat[0].length;

		int[] res = new int[m * n];
		int i = 0;
		int j = 0;

		for (int k = 0; k < res.length; k++) {
			res[k] = mat[i][j];

			if ((i + j) % 2 == 0) {
				if (j == n - 1)
					i++;
				else if (i == 0)
					j++;
				else {
					i--;
					j++;
				}
			} else {
				if (i == m - 1)
					j++;
				else if (j == 0)
					i++;
				else {
					i++;
					j--;
				}
			}
		}
		return res;
	}
}
