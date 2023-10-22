package arrayAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Array {

	public static void main(String[] args) {
		int[] nums = { 1, 7, 3, 6, 5, 6 };
		System.out.println(pivotIndex(nums));
	}

	public static int pivotIndex(int[] nums) {
		// approach by neetcode
		int total = 0;

		for (int i = 0; i < nums.length; i++)
			total += nums[i];

		int leftSum = 0;

		for (int i = 0; i < nums.length; i++)
			if (total - leftSum - nums[i] == leftSum)
				return i;
			else {
				leftSum += nums[i];
			}

		return -1;
	}

	// find all array dissappeared in array
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		ArrayList<Integer> res = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			int x = nums[Math.abs(nums[i]) - 1];
			nums[Math.abs(nums[i]) - 1] = x < 0 ? x : -x;
		}

		printArray(nums, nums.length);

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				res.add(i + 1);
			}
		}

		return res;
	}

	public static void printArray(int[] arr, int len) {

		System.out.print("[ ");
		for (int i = 0; i < len; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.print("]\n");
	}

	// duplicates zero to next position, if zero detected at a position.
	public static void duplicateZeros(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == 0) {
				for (int j = arr.length - 2; j > i; j--) {
					arr[j + 1] = arr[j];
				}
				arr[i + 1] = 0;
				i++;
			}
		}
	}

	public static int square(int x) {
		return x * x;
	}

	public static int[] sortedSquares(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		int[] res = new int[nums.length];
		int i = res.length - 1;
		while (left <= right) {
			if (Math.abs(nums[left]) < Math.abs(nums[right])) {
				res[i] = square(nums[right]);
				right--;
			} else {
				res[i] = square(nums[left]);
				left++;
			}
			i--;
		}
		return res;
	}

	// find maximum consecutive ones in a binary array
	public static int findMaxConsecutiveOnes(int[] nums) {
		int maxConsecutiveOnes = 0;
		int oneCounts = 0;

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] == 1) {
				oneCounts++;
			} else {
				if (oneCounts > maxConsecutiveOnes)
					maxConsecutiveOnes = oneCounts;
				oneCounts = 0;
			}
		}

		if (oneCounts > maxConsecutiveOnes)
			maxConsecutiveOnes = oneCounts;
		return maxConsecutiveOnes;
	}

//	remove duplicates
	public static int removeDuplicates(int[] nums) {
		int len = nums.length;
		for (int i = 0; i < len; i++) {
			while (i < len - 1 && nums[i] == nums[i + 1]) {
				for (int k = i + 2; k < len; k++) {
					nums[k - 1] = nums[k];
				}
				len--;
			}
		}
		return len;
	}

	// remove duplicates in O(n) time
	public static int removeDuplicates2(int[] nums) {
		int uniquePointer = 0;

		for (int i = 1; i < nums.length; i++)
			if (nums[i - 1] != nums[i])
				nums[++uniquePointer] = nums[i];

		return uniquePointer + 1;
	}

//	Remove Element
	public static int removeElement(int[] nums, int val) {
		int length = nums.length;

		for (int i = 0; i < length; i++) {
			if (nums[i] == val) {
				for (int j = i + 1; j < length; j++) {
					nums[j - 1] = nums[j];
				}
				length--;
				i--;
			}
		}

		return length;
	}

	// Check If N and Its Double Exist
	public static boolean checkIfExist(int[] arr) {

		HashSet<Integer> set = new HashSet<>();

		for (int i : arr) {
			set.add(i * 2);
		}

		int zeroCount = 0;

		for (int i : arr) {
			if (zeroCount == 0 && i == 0) {
				zeroCount++;
				continue;
			}
			if (set.contains(i))
				return true;
		}

		return false;
	}

	// valid mountain array
	public static boolean validMountainArray(int[] arr) {
		int l1 = 0;
		int i = 1;
		while (i < arr.length && arr[i - 1] < arr[i]) {
			l1++;
			i++;
		}

		int l2 = 0;

		while (i < arr.length) {
			l2++;
			if (arr[i - 1] > arr[i]) {
				i++;
			} else {
				return false;
			}
		}

		return (l2 != 0 && l1 != 0);
	}

	public static void moveZeroes(int[] nums) {
		int front = 0;

		for (int i = 0; i < nums.length; i++)
			if (nums[i] != 0) {
				int temp = nums[front];
				nums[front] = nums[i];
				nums[i] = temp;
				front++;
			}
	}

	public static int[] sortArrayByParity(int[] nums) {
		int front = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] % 2 == 0) {
				int temp = nums[front];
				nums[front] = nums[i];
				nums[i] = temp;
				front++;
			}
		}

		return nums;
	}

	// O(n) - two pointers.
	public static int removeElement2(int[] nums, int val) {
		if (nums == null || nums.length == 0)
			return 0;

		int front = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val)
				nums[front++] = nums[i];
		}

		return front;
	}

	// returns third maximum number
	public static int thirdMax(int[] nums) {

		if (nums.length < 3) {
			int max = nums[0];
			for (int i : nums)
				if (max < i)
					max = i;
			return max;
		}

		TreeSet<Integer> set = new TreeSet<>();

		for (int i : nums)
			set.add(i);

		Object[] arr = set.toArray();

		if (arr.length < 3)
			return (int) arr[arr.length - 1];

		return (int) arr[arr.length - 3];
	}

}
