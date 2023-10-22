package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GenericTree {
	public static TreeNode takeInput(Scanner scn) {
		System.out.println("Enter node data:");
		int data = scn.nextInt();
		TreeNode root = new TreeNode(data);

		System.out.println("Enter number of children: ");
		int childCount = scn.nextInt();
		for (int i = 0; i < childCount; i++) {
			TreeNode child = takeInput(scn);
			root.children.add(child);
		}

		return root;
	}

	public static TreeNode takeInputLevelOrder() {
		Queue<TreeNode> pendingNodes = new LinkedList<TreeNode>();
		Scanner scn = new Scanner(System.in);

		System.out.println("Enter data for root: ");
		TreeNode root = new TreeNode(scn.nextInt());

		pendingNodes.add(root);

		while (!pendingNodes.isEmpty()) {
			TreeNode node = pendingNodes.poll();

			System.out.println("Enter number of children for " + node.data + ": ");

			int childCount = scn.nextInt();

			for (int i = 0; i < childCount; i++) {
				System.out.println("Enter data for +" + (i + 1) + "th child of " + node.data + ": ");
				int data = scn.nextInt();

				TreeNode childNode = new TreeNode(data);

				node.children.add(childNode);

				pendingNodes.add(childNode);
			}
		}
		scn.close();
		return root;
	}

	public static void printTree(TreeNode root) {
		if (root == null) {
			return;
		}
		
		StringBuilder sb = new StringBuilder(root.data + ": ");
		
		for (int i = 0; i < root.children.size(); i++) {
			sb.append(root.children.get(i).data + ", ");
		}

		System.out.println(sb);

		for (int i = 0; i < root.children.size(); i++) {
			printTree(root.children.get(i));
		}
			
	}

	public static void main(String[] args) {
//		Scanner scn = new Scanner(System.in);
		TreeNode root = takeInputLevelOrder();
//		scn.close();
		printTree(root);
	}
}
