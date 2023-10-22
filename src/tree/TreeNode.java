package tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	public int data;
	public List<TreeNode> children;

	public TreeNode(int data) {
		this.data = data;
		this.children = new ArrayList<>();
	}
}
