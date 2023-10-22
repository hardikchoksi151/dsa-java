package linkedlist;

import java.util.HashMap;

class LRUCache {

	public class Node {
		int key, val;
		Node prev, next;

		public Node() {
		}

		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}

	}

	private Node left;
	private Node right;
	private HashMap<Integer, Node> map;
	private int capacity;
	private int size;

	private void makeMRU(Node node) {
		if (node == right)
			return;

		if (node == left) {
			Node temp = left;
			left = left.next;
			left.prev = null;

			right.next = temp;
			temp.prev = right;
			temp.next = null;
			right = temp;

			return;
		}
		node.prev.next = node.next;
		node.next.prev = node.prev;

		node.next = null;
		node.prev = right;
		right.next = node;
		right = node;

	}

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<>();
		size = 0;
	}

	public int get(int key) {
		Node node = map.get(key);

		if (node == null)
			return -1;

		makeMRU(node);
		return node.val;
	}

	public void put(int key, int value) {
		// check if already exists
		// if does than update the value and makeMRU
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.val = value;
			makeMRU(node);
			return;
		}

		Node newNode = new Node(key, value);

		if (size == 0) {
			left = right = newNode;
			map.put(key, newNode);
			size++;
			return;
		}

		newNode.prev = right;
		right.next = newNode;
		right = newNode;
		map.put(key, newNode);

		if (size == capacity) {
			Node temp = left.next;
			left.next = null;
			temp.prev = null;
			map.remove(left.key, left);
			left = temp;
			return;
		}

		size++;
	}
}

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */