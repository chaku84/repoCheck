package binaryTree;

import java.util.*;
import java.io.*;

public class ConstructionConversion {
	
	public static void main(String[] args)
	{
		Node rt=new Node(0);
		rt.left=new Node(1);
		rt.right=new Node(2);
		rt.left.left=new Node(3);
		rt.left.right=new Node(4);
		rt.right.left=new Node(5);
		rt.right.right=new Node(6);
	}
	
	/*
	 * Construct Tree from given Inorder and Preorder traversals
	 -> recursive using inorder index of preorder whereas preorder index is increased globally 
	 -> and iterative using set and stack
		Construct a tree from Inorder and Level order traversals
	-> recursive using inorder index by finding first inorder index in levelorder and same call as previous
		Construct Complete Binary Tree from its Linked List Representation
	-> Use levelorder traversal to make tree
		Construct a complete binary tree from given array in level order fashion
	-> Use levelorder traversal to make tree
		Construct Full(0 or 2 child) Binary Tree from given preorder and postorder traversals
	-> postorder can be treated as inorder in 1st
		Construct Full Binary Tree using its Preorder traversal and Preorder traversal of its mirror tree
	-> pre tra of mirror tree gives postorder which is same as prev
		Construct a special tree from given preorder traversal and given leaf and non-leaf node
	-> algo remains same as 1st but subtree is constructed for non-leaf node only
		Construct tree from ancestor matrix
	-> 
		Construct Ancestor Matrix from a Given Binary Tree
		Construct Special Binary Tree from given Inorder traversal
	 */
	

}
