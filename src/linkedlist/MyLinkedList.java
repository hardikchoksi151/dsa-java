package linkedlist;

class MyLinkedList {

//	private class ListNode {
//		int val;
//		ListNode next;
//
//		ListNode(int val) {
//			this.val = val;
//		}
//
//		ListNode(int val, ListNode next) {
//			this.val = val;
//			this.next = next;
//		}
//	}

	public ListNode head;
	public int size;

	public MyLinkedList() {
		head = null;
		size = 0;
	}

	public int get(int index) {
		if (index < 0 || index >= size)
			return -1;

		ListNode curr = head;

		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}

		return curr.val;
	}

	public void addAtHead(int val) {
		if (size == 0) {
			head = new ListNode(val);
		} else {
			ListNode temp = head;
			head = new ListNode(val, temp);
		}
		size++;
	}

	public void addAtTail(int val) {
		if (size == 0) {
			addAtHead(val);
		} else {
			ListNode curr = head;

			while (curr.next != null) {
				curr = curr.next;
			}

			curr.next = new ListNode(val);
			size++;
		}
	}

	public void addAtIndex(int index, int val) {

		if (index < 0 || index > size)
			return;

		if (index == size) {
			addAtTail(val);
			return;
		}

		if (index == 0) {
			addAtHead(val);
			return;
		}

		int i = 0;
		ListNode curr = head;
		ListNode prev = null;

		while (curr != null) {

			if (i == index) {
				prev.next = new ListNode(val, curr);
				size++;
				return;
			}

			i++;
			prev = curr;
			curr = curr.next;
		}

	}

	public void deleteAtIndex(int index) {
		if (size == 0)
			return;

		if (index < 0 || index >= size)
			return;

		if (index == 0) {
			ListNode temp = head;
			head = temp.next;
			temp.next = null;
			size--;
			return;
		}

		ListNode curr = head;
		ListNode prev = null;
		int i = 0;

		while (curr != null) {

			if (i == index) {
				prev.next = curr.next;
				curr.next = null;
				size--;
				return;
			}

			i++;
			prev = curr;
			curr = curr.next;
		}
	}
}
