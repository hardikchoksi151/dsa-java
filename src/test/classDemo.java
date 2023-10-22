package test;

public class classDemo {
	public static void main(String[] args) {
		node node = new node();
		System.out.println(node.val);
		System.out.println(node.next);
	}
}

class node {
	int val;
	node next;
	node(){}
}