package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class BinarySearch {
//	https://leetcode.com/problems/arranging-coins/
//	discussion
	public static int arrangeCoins(int n) {
		long s = 0;
		long e = n;

		while (s <= e) {
			long mid = s + (e - s) / 2;
			long coinsTillMidRow = mid * (mid + 1) / 2;

			if (coinsTillMidRow > n)
				e = mid - 1;
			else
				s = mid + 1;
		}
		return (int) e;
	}

//	https://leetcode.com/problems/kth-missing-positive-number/
//	https://www.youtube.com/watch?v=Nfu-ubvJaZ0
	public static int findKthPositive(int[] arr, int k) {
		int s = 0, e = arr.length - 1;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			int missingOnLeft = arr[mid] - mid - 1;

			if (missingOnLeft < k)
				s = mid + 1;
			else
				e = mid - 1;
		}

		return s + k;

	}

//	1608. Special Array With X Elements Greater Than or Equal X
	public static int specialArray(int[] nums) {
		int s = 0, e = nums.length;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			int count = 0;
			for (int i = 0; i < nums.length; i++)
				if (nums[i] >= mid)
					count++;
//			if balanced return ans
			if (count == mid)
				return mid;
			if (count > mid)
//				 for balancing count and mid, we need to increase mid and decrease count
				s = mid + 1;
			else
//				decrease mid
				e = mid - 1;
		}

		return -1;
	}

//	1351. Count Negative Numbers in a Sorted Matrix
	public static int countNegatives(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		int count = 0;

		int row = 0;
		int col = n - 1;

		while (row < m && col >= 0) {
			if (grid[row][col] < 0) {
				count += m - row;
				col--;
			} else {
				row++;
			}
		}

		return count;
	}

//	74. Search a 2D Matrix
	public static boolean searchMatrix(int[][] mat, int target) {
		int m = mat.length;
		int n = mat[0].length;

		int targetRow = -1;
		// search the which row has the target value
		int l = 0;
		int r = n - 1;

		int sRow = 0;
		int eRow = m - 1;

		while (sRow <= eRow) {
			int midRow = sRow + (eRow - sRow) / 2;

			if (target > mat[midRow][r])
				sRow = midRow + 1;
			else if (target < mat[midRow][l])
				eRow = midRow - 1;
			else {
				targetRow = midRow;
				break;
			}
		}

		System.out.println(targetRow);

		if (targetRow == -1)
			return false;

		// now, search if the target exists in the targetRow
		int s = 0, e = n - 1;

		while (s <= e) {
			int mid = s + (e - s) / 2;

			if (mat[targetRow][mid] == target)
				return true;

			if (target < mat[targetRow][mid])
				e = mid - 1;
			else
				s = mid + 1;
		}

		return false;
	}

//	1346. Check If N and Its Double Exist
	public static boolean checkIfExist(int[] arr) {
		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			int target = 2 * arr[i];
			int s = 0, e = arr.length - 1;

			while (s <= e) {
				int mid = s + (e - s) / 2;

				if (target == arr[mid] && mid != i)
					return true;
				else if (target < arr[mid])
					e = mid - 1;
				else
					s = mid + 1;
			}
		}

		return false;
	}

//	1337. The K Weakest Rows in a Matrix

	private int numsOfSoldiers(int[] row) {
		int s = 0, e = row.length - 1;

		while (s <= e) {
			int m = s + (e - s) / 2;

			if (row[m] == 0)
				e = m - 1;
			else
				s = m + 1;
		}

		return e + 1;
	}

	public int[] kWeakestRows(int[][] mat, int k) {
		PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
		int[] res = new int[k];

		for (int i = 0; i < mat.length; i++) {
			maxHeap.offer(new int[] { numsOfSoldiers(mat[i]), i });
			if (maxHeap.size() > k)
				maxHeap.poll();
		}

		while (k > 0)
			res[--k] = maxHeap.poll()[1];

		return res;
	}

//	350. Intersection of Two Arrays II

//	1. BruteForce TS: O(m + n) SC: O(m + n)
	public static int[] intersect(int[] nums1, int[] nums2) {
		HashMap<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
		ArrayList<Integer> res = new ArrayList<>();

//		for (int num : nums1)
//			map1.put(num, map1.getOrDefault(num, 0) + 1);

//		for (int num : nums2)
//			map2.put(num, map2.getOrDefault(num, 0) + 1);

//		for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
//			int key = entry.getKey();
//			if (map2.containsKey(key))
//				for (int i = 0; i < Math.min(map1.get(key), map2.get(key)); i++)
//					res.add(key);
//		}

		if (nums1.length > nums2.length) {
			int[] temp = nums1;
			nums1 = nums2;
			nums2 = temp;
		}

		for (int num : nums1)
			map1.put(num, map1.getOrDefault(num, 0) + 1);

		for (int num : nums2) {
			if (map1.containsKey(num) && map1.get(num) > 0) {
				res.add(num);
				map1.put(num, map1.get(num) - 1);
			}
		}

		int[] temp = new int[res.size()];

		for (int i = 0; i < temp.length; i++)
			temp[i] = res.get(i);

		return temp;
	}
// 2. Space optimised, one hashmap TS: O(m + n) SC: O(min(m, n))

//	instead of using two hashmaps, we can use one hashmap
//	but for this to work nums1 < nums2
//	so we can just swap references if nums1 > nums2
//	note: why small array hashmap should be made?
//	because we wanna append an element minimum # of times in result array
//	and why do we wanna do that?
//	because it's given in the problem statement
//	"Each element in the result must appear as many times as it shows in both arrays"

//	public static int[] intersect(int[] nums1, int[] nums2) {
//		HashMap<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
//		ArrayList<Integer> res = new ArrayList<>();
//
//		if (nums1.length > nums2.length) {
//			int[] temp = nums1;
//			nums1 = nums2;
//			nums2 = temp;
//		}
//
//		for (int num : nums1)
//			map1.put(num, map1.getOrDefault(num, 0) + 1);
//
//		for (int num : nums2) {
//			if (map1.containsKey(num) && map1.get(num) > 0) {
//				res.add(num);
//				map1.put(num, map1.get(num) - 1);
//			}
//		}
//
//		int[] temp = new int[res.size()];
//
//		for (int i = 0; i < temp.length; i++)
//			temp[i] = res.get(i);
//
//		return temp;
//	}

//	3. more optimises (two pointers) (works only if arrays are sorted)
//	TS: O(m + n) SC: O(1)
//	public static int[] intersect(int[] nums1, int[] nums2) {
//		ArrayList<Integer> res = new ArrayList<>();
//
//		Arrays.sort(nums1);
//		Arrays.sort(nums2);
//
//		int i = 0, j = 0;
//
//		while (i < nums1.length && j < nums2.length) {
//			if (nums1[i] < nums2[j])
//				i++;
//			else if (nums1[i] > nums2[j])
//				j++;
//			else {
//				res.add(nums1[i]);
//				i++;
//				j++;
//			}
//		}
//
//		int[] temp = new int[res.size()];
//
//		for (int i = 0; i < temp.length; i++)
//			temp[i] = res.get(i);
//		return temp;
//	}

//	1855. Maximum Distance Between a Pair of Values
//	sol: https://leetcode.com/problems/maximum-distance-between-a-pair-of-values/discuss/2705351/Python-O(N2)-O(NLogN)-O(N)-or-EXPLAINED
	public static int maxDistance(int[] nums1, int[] nums2) {
		int max = 0;

		for (int i = 0; i < nums1.length; i++) {

			int s = 0, e = nums2.length - 1;

			while (s <= e) {
				int m = s + (e - s) / 2;

				if (nums1[i] <= nums2[m]) {
					max = Math.max(max, m - i);
					s = m + 1;
				} else {
					e = m - 1;
				}
			}
		}

		return max;
	}

//	888. Fair Candy Swap
	public int[] fairCandySwap(int[] A, int[] B) {
		int sum1 = 0, sum2 = 0;

		for (int i : A)
			sum1 += i;

		for (int i : B)
			sum2 += i;

		int diff = (sum1 - sum2) / 2;

		Arrays.sort(A);

		for (int b : B) {
			int s = 0, e = A.length - 1;

			int targetA = b + diff;

			while (s <= e) {
				int m = s + (e - s) / 2;

				if (targetA == A[m])
					return new int[] { targetA, b };

				if (targetA < A[m])
					e = m - 1;
				else
					s = m + 1;
			}
		}

		return null;
	}

//	540. Single element in a sorted array
	public static int singleNonDuplicate(int[] nums) {
		int s = 0, e = nums.length - 1;

		while (s <= e) {
			int m = s + (e - s) / 2;

			if (nums[m] == nums[m - 1]) {
				if (m % 2 != 0)
					s = m + 1;
				else
					e = m - 1;
			} else if (nums[m] == nums[m + 1]) {
				if (m % 2 != 0)
					e = m - 1;
				else
					s = m + 1;
			} else
				return nums[m];
		}
		return -1;
	}

	public int findPeakIndex(int[] arr) {
		int s = 0, e = arr.length - 1;

		while (s <= e) {
			int m = s + (e - s) / 2;
			int N = e - s + 1;
			int mid = arr[m];
			int prev = arr[(m - 1 + N) % N];
			int next = arr[(m + 1) % N];

			// if (mid > prev && mid > next)
			// return m;

			if (m < e && arr[m] > arr[m + 1])
				return m;

			if (m > s && arr[m] < arr[m - 1])
				return m - 1;

			if (arr[s] == mid && arr[e] == mid) {
				if (s < e && arr[s] > arr[s + 1])
					return s;
				s++;

				if (s < e && arr[e - 1] > arr[e])
					return e - 1;
				e--;
			} else if (arr[s] < mid || arr[s] == mid && mid > arr[e])
				s = m + 1;
			else
				e = m - 1;
		}

		return -1;
	}

	public boolean binarySearch(int[] arr, int s, int e, int x) {
		while (s <= e) {
			int m = s + (e - s) / 2;

			if (x == arr[m])
				return true;

			if (x < arr[m])
				e = m - 1;
			else
				s = m + 1;
		}

		return false;
	}

	public boolean search(int[] nums, int target) {
		int peakIndex = findPeakIndex(nums);

		if (peakIndex == -1)
			return binarySearch(nums, 0, nums.length - 1, target);

		if (nums[peakIndex] == target)
			return true;

		boolean res = binarySearch(nums, 0, peakIndex - 1, target);

		if (!res)
			return binarySearch(nums, peakIndex + 1, nums.length - 1, target);

		return res;
	}

	public int[] findRightInterval(int[][] intervals) {
		int[] res = new int[intervals.length];

		TreeMap<Integer, Integer> map = new TreeMap<>();

		for (int i = 0; i < intervals.length; i++) {
			map.put(intervals[i][0], i);
		}

		for (int i = 0; i < intervals.length; i++) {
			Map.Entry<Integer, Integer> entry = map.ceilingEntry(intervals[i][1]);
			if (entry != null)
				res[i] = entry.getValue();
			else
				res[i] = -1;
		}

		return res;
	}

	public static void main(String[] args) {
		int[] nums1 = { 55, 30, 5, 4, 2 };
		int[] nums2 = { 100, 20, 10, 10, 5 };
		System.out.println(maxDistance(nums1, nums2));
	}
}