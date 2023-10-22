package linkedlist;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LL {

	ListNode head;
	int size;

	public LL() {
	}

	public int size() {
		return size;
	}

	public void add(int num) {
		ListNode node = new ListNode(num);
		if (head == null) {
			head = node;
			return;
		}

		ListNode trav = head;
		while (trav.next != null) {
			trav = trav.next;
		}
		trav.next = node;
		size++;
	}

	public static void display(ListNode head) {
		if (head == null) {
			System.out.println("Linked List is empty.");
			return;
		}
		ListNode trav = head;
		System.out.println();
		System.out.print("[ ");
		while (trav != null) {
			System.out.print(trav.val + " ");
			trav = trav.next;
		}
		System.out.print("]");
		System.out.println();
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode prev = dummyHead;
		int carry = 0;

		while (l1 != null && l2 != null) {
			int num = l1.val + l2.val + carry;
			System.out.println("num before carry: " + num);
			if (num > 9) {
				carry = num / 10;
				num = num % 10;
			} else {
				carry = 0;
			}

			System.out.println("num before carry: " + num);
			System.out.println("carry: " + carry);

			ListNode newNode = new ListNode(num);
			prev.next = newNode;
			prev = newNode;
			l1 = l1.next;
			l2 = l2.next;
		}

		while (l1 != null) {
			int num = l1.val + carry;

			if (num > 9) {
				carry = num / 10;
				num = num % 10;
			} else {
				carry = 0;
			}

			ListNode newNode = new ListNode(num);
			prev.next = newNode;
			prev = newNode;
			l1 = l1.next;
		}

		while (l2 != null) {
			int num = l2.val + carry;

			if (num > 9) {
				carry = num / 10;
				num = num % 10;
			} else {
				carry = 0;
			}

			ListNode newNode = new ListNode(num);
			prev.next = newNode;
			prev = newNode;
			l2 = l2.next;
		}

		if (carry != 0) {
			ListNode newNode = new ListNode(carry);
			prev.next = newNode;
			prev = newNode;
		}

		return dummyHead.next;
	}

	// LeetCode medium 61.Rotate LinkedList
	public static ListNode rotateRight(ListNode head, int k) {

		if (head == null || head.next == null)
			return head;

		int len = 1;
		ListNode e = head;
		while (e.next != null) {
			e = e.next;
			len++;
		}

		k = k % len;

		if (k == 0)
			return head;

		ListNode curr = head;

		for (int i = 0; i < (len - k - 1); i++) {
			curr = curr.next;
		}

		ListNode newHead = curr.next;
		curr.next = null;
		e.next = head;
		return newHead;
	}

//	82. Remove Duplicates from Sorted List II
	public static ListNode deleteDuplicates(ListNode head) {
		ListNode curr = head;
		ListNode dummy = new ListNode(0, head);
		ListNode prev = dummy;

		while (curr != null && curr.next != null) {
			if (curr.val == curr.next.val) {
				while (curr.next != null && curr.val == curr.next.val) {
					curr.next = curr.next.next;
				}
				prev.next = curr.next;
				curr = curr.next;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}

		return dummy.next;
	}

//	86. Partition List
	public static ListNode partition(ListNode head, int x) {
		if (head == null)
			return head;

		ListNode leftDummyHead = new ListNode(0);
		ListNode left = leftDummyHead;
		ListNode rightDummyHead = new ListNode(0);
		ListNode right = rightDummyHead;

		ListNode curr = head;
		while (curr != null) {

			if (curr.val < x) {
				ListNode tmp = new ListNode(curr.val);
				left.next = tmp;
				left = left.next;
			} else {
				ListNode tmp = new ListNode(curr.val);
				right.next = tmp;
				right = right.next;
			}
			curr = curr.next;
		}

		left.next = rightDummyHead.next;

		return leftDummyHead.next;
	}

	public static int getIndexOfNode(ListNode head, ListNode node) {

		int i = 0;
		while (head != null) {

			if (head == node) {
				return i;
			}

			i++;
			head = head.next;
		}

		return -1;

	}

	public static ListNode getNodeByIndex(ListNode head, int index) {
		int i = 0;

		while (head != null) {

			if (i == index) {
				return head;
			}

			i++;
			head = head.next;
		}

		return null;

	}

	public static ListNode insertionSortList(ListNode head) {
		if (head.next == null)
			return head;

		ListNode key = head.next;
		ListNode sortedListHead = head;
		sortedListHead.next = null;

		ListNode prev = null;
		ListNode curr = null;

		while (key != null) {
			curr = sortedListHead;
			prev = key;

			while (curr != null && key.val < curr.val) {
				prev = curr;
				curr = curr.next;
			}

			if (curr == sortedListHead)
				sortedListHead = key;

			ListNode temp = key.next;
			prev.next = key;
			key.next = curr;
			key = temp;
		}

		return reverse(sortedListHead);

	}

	private static ListNode reverse(ListNode sortedListHead) {
		// TODO Auto-generated method stub
		return null;
	}

	// 237. Delete Node in a Linked List
	public static void deleteNode(ListNode node) {
		ListNode prev = null;

		while (node.next != null) {
			node.val = node.next.val;
			prev = node;
			node = node.next;
		}

		prev.next = null;
	}

//	328. Odd Even Linked List
	public static ListNode oddEvenList(ListNode head) {
		ListNode e1, e2;
		ListNode s2 = head.next;
		ListNode curr = head;
		ListNode prev = null;

		int len = 1;
		while (curr.next != null) {
			len++;
			prev = curr;
			curr = curr.next;
		}

		if (len % 2 == 0) {
			e2 = curr;
			e1 = prev;
		} else {
			e2 = prev;
			e1 = curr;
		}

		curr = head;
		ListNode next = null;

		while (curr != null && curr.next != null && curr.next.next != null) {
			next = curr.next;
			curr.next = curr.next.next;
			curr = next;
		}

		e2.next = null;
		e1.next = s2;

		return head;

	}

//	382. Linked List Random Node
	public static int getRandom(ListNode head) {
// commented code gives wrong answer on leetcode 
//			ListNode curr = head;
//			int nodesSeenTillNow = 0;
//			ListNode randomNode = null;
//			Random rand = new Random();
//			while (curr != null) {
//				nodesSeenTillNow++;
//				int randomIndex = rand.nextInt() % nodesSeenTillNow;
//				if (randomIndex == nodesSeenTillNow - 1) 
//					randomNode = curr;
//				curr = curr.next;
//			}
//			return randomNode.val;
		Random rand = new Random();

		ListNode curr = head;
		int i = 1;
		ListNode randomNode = head;

		while (curr != null) {

			if (rand.nextInt(i) == i - 1)
				randomNode = curr;

			curr = curr.next;

			i++;
		}

		return randomNode.val;
	}

//	725. Split Linked List in Parts
	public static ListNode[] splitListToParts(ListNode head, int k) {
		ListNode[] res = new ListNode[k];

		int len = 0;
		ListNode curr = head;

		while (curr != null) {
			len++;
			curr = curr.next;
		}
		curr = head;
		// case 1: if length <= k
		if (len <= k) {
			int i = 0;
			while (curr != null) {
				res[i] = curr;
				ListNode next = curr.next;
				curr.next = null;
				curr = next;
				i++;
			}

			if (i != k) {
				while (i != k) {
					res[i] = null;
					i++;
				}
			}

			return res;
		}

		// case 2: if length > k
		int rem = len % k;
		int x = len / k;
		int i = 0;

		while (curr != null) {

			int runTill = rem != 0 ? x + 1 : x;
			if (rem != 0)
				rem--;

			ListNode groupStart = curr;

			for (int j = 0; j < runTill - 1 && curr != null; j++) {
				curr = curr.next;
			}

			if (curr != null) {
				ListNode next = curr.next;
				curr.next = null;
				curr = next;
			}

			res[i] = groupStart;
			i++;
		}

		return res;

	}

//	817. Linked List Components
	public static int numComponents(ListNode head, int[] nums) {

		Set<Integer> set = new HashSet<>();
		for (int i : nums)
			set.add(i);

		int components = set.size();
		ListNode curr = head;

		while (curr.next != null) {
			if (set.contains(curr.val) && set.contains(curr.next.val))
				components--;
			curr = curr.next;
		}

		return components;
	}

	// 1019. Next Greater Node In Linked List O(n^2)
	public static int[] nextLargerNodes(ListNode head) {
		int len = 0;
		ListNode curr = head;

		while (curr != null) {
			len++;
			curr = curr.next;
		}

		int[] res = new int[len];

		curr = head;

		int i = 0;

		while (curr != null) {
			int nextGreater = 0;

			if (curr.next != null) {
				ListNode curr2 = curr.next;

				while (curr2 != null) {
					if (curr2.val > curr.val) {
						nextGreater = curr2.val;
						break;
					}

					curr2 = curr2.next;
				}

			}

			res[i] = nextGreater;
			i++;
			curr = curr.next;
		}

		return res;
	}

	// 148. SortList
	public static ListNode sortList(ListNode head) {

		if (head == null || head.next == null)
			return head;

		ListNode mid = getMid(head);
		ListNode left = head;
		ListNode right = mid.next;
		mid.next = null;

		return merge(sortList(left), sortList(right));

	}

	public static ListNode getMid(ListNode head) {
		ListNode slow = head;
		ListNode fast = head.next;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;

	}

	public static ListNode merge(ListNode list1, ListNode list2) {
		ListNode resHead = new ListNode();
		ListNode tail = resHead;

		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				tail.next = list1;
				list1 = list1.next;
				tail = tail.next;
			} else {
				tail.next = list2;
				list2 = list2.next;
				tail = tail.next;
			}
		}

		tail.next = list1 == null ? list2 : list1;

		return resHead.next;
	}

//	2095. Delete the Middle Node of a Linked List
	public static ListNode deleteMiddle(ListNode head) {
		int len = 0;
		for (ListNode trav = head; trav != null; trav = trav.next)
			len++;

		if (len == 0 || len == 1)
			return null;

		ListNode slow = head;
		ListNode fast = null;
		if (len % 2 == 0)
			fast = head;
		else
			fast = head.next;

		ListNode midPrev = null;

		while (fast != null && fast.next != null) {
			midPrev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		System.out.println("midPrev: " + midPrev.val + " mid: " + slow.val);
		midPrev.next = slow.next;
		slow.next = null;

		return head;
	}

}
