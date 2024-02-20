package com.wnj.util.a;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {
	static List<Integer> levellist=new ArrayList<>();
	public static void levelorder(TreeNode root) {
		if(root==null) {//空树
			return;
		}
		Queue<TreeNode> queue=new LinkedList<TreeNode>();
		queue.offer(root);//offer方法表示添加元素到队尾
		while(!queue.isEmpty()) {
			TreeNode temp=queue.poll();//poll方法删除队头元素
			levellist.add(temp.val);
			if(temp.left!=null) {
				queue.offer(temp.left);
			}
			if(temp.right!=null) {
				queue.offer(temp.right);
			}
		}
	}

	static class TreeNode {//二叉树结点定义
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		TreeNode root=new TreeNode(7);//创建二叉树
		TreeNode a=new TreeNode(4);
		TreeNode b=new TreeNode(9);
		TreeNode c=new TreeNode(2);
		TreeNode d=new TreeNode(5);
		TreeNode e=new TreeNode(8);
		TreeNode f=new TreeNode(11);
		TreeNode g=new TreeNode(1);
		TreeNode h=new TreeNode(3);
		TreeNode i=new TreeNode(10);
		TreeNode j=new TreeNode(12);
		root.left=a;
		root.right=b;
		a.left=c;
		a.right=d;
		b.left=e;
		b.right=f;
		c.left=g;
		c.right=h;
		f.left=i;
		f.right=j;
		
		levelorder(root);//层次遍历
		for(int m=0;m<levellist.size();m++) {
			System.out.print(levellist.get(m)+" ");
		}
	}
}
