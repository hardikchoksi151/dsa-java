package leetCode;

import java.util.Arrays;

public class twoSum {

	public static int[] solution(int[] nums, int target) {
		int[] res = new int[2];
		
//		int left = 0, right = nums.length - 1;
//		Arrays.sort(nums);
//		while(left < right) {
//			if ( nums[left]+nums[right] < target ) {
//				left++;
//			} else if ( nums[left] + nums[right] > target ) {
//				right--;
//			} else {
//				res[0] = left;
//				res[1] = right;
//			}
//		}
		
		for (int i=0; i < nums.length; i++) {
			for (int j=i+1; j < nums.length; j++) {
					if (nums[i] + nums[j] == target) {
						res[0] = i;
						res[1] = j;
					}
			}
		}
		
		
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {2,7,11,15};
		int target = 9;
		int[] res = solution(nums, target);
		for(int i=0; i<res.length; i++) {
			System.out.println(res[i]);
		}
	}
}