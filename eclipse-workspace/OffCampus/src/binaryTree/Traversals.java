package binaryTree;

import java.io.*;
import java.util.*;

public class Traversals {
	
	public static void main(String[] args)
	{
		Node rt=new Node(0);
		rt.left=new Node(1);
		rt.right=new Node(2);
		rt.left.left=new Node(3);
		rt.left.right=new Node(4);
		rt.right.left=new Node(5);
		rt.right.right=new Node(6);
		//q1a(rt);
		//System.out.println();
		//q1b(rt);
		//System.out.println();
		//q1c(rt);
		//System.out.println();
		//q2inorderIter(rt);
		//q3(rt);
		//q4(new int[] {3, 1, 4, 0, 5 ,2 ,6},new int[] {0, 1, 3, 4, 2, 5, 6 });
		//q6(new int[] {1,2,3,4,5,6,7});
		
		//q1a(rt);
		//q1a(q17(rt));
		//q1b(rt);
		//q21(rt);
		
		//q1c(rt);
		//q25(rt);
		
		//q26(rt);
		//q27(rt);
	}
	
	//inorder recursive
	//uses-sorted value for bst
	static void q1a(Node rt)
	{
		if(rt==null)return;
		q1a(rt.left);
		System.out.print(rt.data+" ");
		q1a(rt.right);
	}
	
	//preorder recursive
	//uses-copy of the tree,
	//to get prefix expression of an expression tree
	static void q1b(Node rt)
	{
		if(rt==null)return;
		System.out.print(rt.data+" ");
		q1b(rt.left);
		q1b(rt.right);
	}
	
	//postorder recursive
	//uses-to delete the tree.
	//to get postfix expression of an expression tree
	static void q1c(Node rt)
	{
		if(rt==null)return;
		
		q1c(rt.left);
		q1c(rt.right);
		System.out.print(rt.data+" ");
	}
	//case1-skewed tree->T(n)=n(c+d)
	//case2-equal left & right subtree nodes->T(n)=aT(n/b)+(-)(n)
	
	static void q2inorderIter(Node rt)
	{
		Stack<Node> st=new Stack<>();
		Node curr=rt;
		while(curr!=null || !st.isEmpty())
		{
			while(curr!=null)
			{
				st.push(curr);
				curr=curr.left;
			}
			
			curr=st.pop();
			
			System.out.print(curr.data+" ");
			
			curr=curr.right;
		}
		System.out.println();
	}
	
	//inorder without recursion and without stack
	//morris traversal
	static void q3(Node rt)
	{
		Node curr=rt,pre=null;
		while(curr!=null)
		{
			//no left child
			if(curr.left==null)
			{
				System.out.print(curr.data+" ");
				curr=curr.right;
			}
			else
			{
				//find inorder predecessor
				pre=curr.left;
				while(pre.right!=null && pre.right!=curr)
				{
					pre=pre.right;
				}
				
				//curr as right child of pre
				if(pre.right==null)
				{
					//System.out.print(curr.data+" ");
					pre.right=curr;
					curr=curr.left;
				}
				else //restore the changes in if part
				{
					pre.right=null;
					System.out.print(curr.data+" ");
					curr=curr.right;
				}
			}
		}
		System.out.println();
	}
	
	//postorder from pre and inorder
	static void q4(int[] in,int[] pre)
	{
		preInd=0;
		HashMap<Integer,Integer> map=new HashMap<>();
		for(int i=0;i<in.length;i++)map.put(in[i],i);
		q4Util(in, pre, 0, in.length-1, map);
		System.out.println();
	}
	
	static int preInd;
	
	static void q4Util(int[] in,int[] pre,int inSt,int inEnd,HashMap<Integer,Integer> map)
	{
		if(inSt>inEnd)return;
		int ind=map.get(pre[preInd++]);
		q4Util(in,pre,inSt,ind-1,map);
		q4Util(in, pre, ind+1, inEnd, map);
		
		System.out.print(in[ind]+" ");
	}
	
	//postorder from preorder of bst
	static void q5(int[] pre)
	{
		q5Util(pre, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	static void q5Util(int[] pre,int preInd,int minVal,int maxVal)
	{
		if(preInd==pre.length)return;
		
		if(pre[preInd]<minVal || pre[preInd]>maxVal)return;
		
		q5Util(pre,preInd+1,minVal,pre[preInd]);
		q5Util(pre, preInd+1, pre[preInd], maxVal);
		
		System.out.print(pre[preInd]+" ");
	}
	
	//all possible btrees from inorder
	static void q6(int[] in)
	{
		ArrayList<Node> list=q6Util(in,0,6);
		for(Node rt:list)
		{
			q1a(rt);
			System.out.println();
			q1b(rt);
			System.out.println("//");
		}
	}
	
	static ArrayList<Node> q6Util(int[] in,int st,int end)
	{
		ArrayList<Node> ans=new ArrayList<>();
		
		if(st>end)
		{
			ans.add(null);
			return ans;
		}
		
		for(int i=st;i<=end;i++)
		{
			ArrayList<Node> left=q6Util(in, st, i-1);
			ArrayList<Node> right=q6Util(in, i+1, end);
			for(int j=0;j<left.size();j++)
			{
				for(int k=0;k<right.size();k++)
				{
					Node rt=new Node(in[i]);
					rt.left=left.get(j);
					rt.right=right.get(k);
					ans.add(rt);
				}
			}
		}
		
		return ans;
	}
	//q7-Replace each node in binary tree with the sum of its inorder predecessor and successor
	//store inorder traversal in arr and add 0 at start and at end and do inorder again
	
	//q8-populate next inorder successor for each Node
	//REVERSE INORDER traversal and a global next
	
	//q9-Inorder Successor of a node in Binary Tree
	//method1-If the right child of the node is not NULL 
	//then the inorder successor of this node will be the leftmost node in it�s right subtree.
	//If the right child of node is NULL. Then we keep finding the parent of the given node x, say p
	//such that p->left = x.
	
	//method2-reverse inorder traversal
	
	//q10-Find n-th node of inorder traversal
	
	//q11-Find n-th node in Postorder traversal of a Binary Tree
	
	//q12-Level Order Tree Traversal
	//method1-recursive using level as a function argument
	//method2-using queues
	
	//q13-Level order traversal in spiral form
	//method 1-using recursion and flag to change direction
	//method2-using two stacks/queues
	
	static void q13b(Node rt)
	{
		Stack<Node> s1=new Stack<>();
		Stack<Node> s2=new Stack<>();
		s1.push(rt);
		while(!s1.isEmpty() || !s2.isEmpty())
		{
			while(!s1.isEmpty())
			{
				Node curr=s1.pop();
				if(curr.right!=null)s2.push(curr.right);
				if(curr.left!=null)s2.push(curr.left);
			}
			while(!s2.isEmpty())
			{
				Node curr=s2.pop();
				if(curr.left!=null)s1.push(curr.left);
				if(curr.right!=null)s1.push(curr.right);
			}
		}
	}
	
	//q14-Level order traversal line by line
	//adding null value to queue at each level
	
	//q15-Level order traversal with direction change after every two levels 
	//method1-using simple queue and its size
	static void q15a(Node rt)
	{
		Queue<Node> q=new LinkedList<>();
		Stack<Node> st=new Stack<>();
		int ct=0;
		boolean dir=false;
		while(!q.isEmpty())
		{
			ct++;
			int sz=q.size();
			for(int i=0;i<sz;i++)
			{
				Node curr=q.poll();
				if(!dir)
				{
					System.out.print(curr.data+" ");
				}
				else st.push(curr);
				
				if(curr.left!=null)q.offer(curr.left);
				if(curr.right!=null)q.offer(curr.right);
			}
			if(dir)
			{
				while(!st.isEmpty())System.out.print(st.pop().data+" ");
			}
			System.out.println();
			if(ct==2)
			{
				dir=!dir;
				ct=0;
			}
		}
	}
	
	//q15 method2-using two stacks/queue
	
	//q16-reverse level order traversal
	//method1-using recursion having level as an argument
	//method2-simple queue
	
	//q17-reverse tree path
	//Given a tree and a node data, your task to reverse the path till that particular Node from root.
	static Node q17(Node rt)
	{
		INT nextpos=new INT();
		nextpos.data=0;
		q17Util(rt, 0, nextpos, new HashMap<Integer,Integer>(), 3);
		return rt;
	}
	
	static class INT
	{
		int data;
	}
	
	static Node q17Util(Node rt,int level,INT nextpos,HashMap<Integer,Integer> map,int data)
	{
		if(rt==null)return null;
		if(rt.data==data)
		{
			map.put(level, rt.data);
			
			rt.data=map.get(nextpos.data);
			
			nextpos.data++;
			return rt;
		}
		
		map.put(level, rt.data);
		//recursively reverse tree path taking left child as root
		Node left=q17Util(rt.left, level+1, nextpos, map, data);
		Node right=null;
		if(left==null)
		{
			//recursively reverse tree path taking right child as root
			right=q17Util(rt.right, level+1, nextpos, map, data);
		}
		//if either left or right child tree path has been reversed,
		//replace data in root
		if(left!=null || right!=null)
		{
			rt.data=map.get(nextpos.data);
			nextpos.data++;
			return left!=null?left:right;
		}
		
		return null;
	}
	
	//q18-perfect binary tree specific level order traversal
	//print nodes in level order but nodes should be from left and right side alternatively
	//mehtod1-take nodes at a level from alternate ends
	//method2-dequeue two nodes at a time
	static void q18(Node rt)
	{
		Queue<Node> q=new LinkedList<>();
		System.out.print(rt.data+" ");
		System.out.print(rt.left.data+" "+rt.right.data);
		
		q.offer(rt.left);
		q.offer(rt.right);
		
		while(!q.isEmpty())
		{
			Node l=q.poll();
			Node r=q.poll();
			
			System.out.print(" "+l.left.data+" "+r.right.data);
			System.out.print(" "+l.right.data+" "+r.left.data);
			//if l and r have grandchildren
			if(l.left.left!=null)
			{
				q.offer(l.left);
				q.offer(r.right);
				q.offer(l.right);
				q.offer(r.left);
			}
		}
	}
	
	//q19-perfect binary tree specific level order traversal set2
	//print nodes in level order but nodes should be from left and right side alternatively
	//and from bottom � up manner using stack
	
	//q20-Reverse alternate levels of a perfect binary tree
	//method1-simple level order travel and store odd levels and reverse it
	//method2-using two inorder,store odd level nodes using first inorder
	//reverse stored values and update using second inorder
	//method3-using preorder traversal b 
	
	static void q20(Node rt1,Node rt2,int level)
	{
		if(rt1==null || rt2==null)return;
		if(level%2==0)
		{
			int temp=rt1.data;
			rt1.data=rt2.data;
			rt2.data=temp;
		}
		
		q20(rt1.left, rt2.right, level+1);
		q20(rt1.right, rt2.left, level+1);
	}
	
	//morris preorder traversal
	static void q21(Node rt)
	{
		Node curr=rt,pre=null;
		while(curr!=null)
		{
			if(curr.left==null)
			{
				System.out.print(curr.data+" ");
				curr=curr.right;
			}
			else
			{
				pre=curr.left;
				while(pre.right!=null && pre.right!=curr)
				{
					pre=pre.right;
				}
				
				if(pre.right==null)
				{
					System.out.print(curr.data+" ");
					pre.right=curr;
					curr=curr.left;
				}
				else
				{
					pre.right=null;
					curr=curr.right;
				}
			}
		}
		System.out.println();
	}
	
	//q22-iterative preorder using stack
	//first add right child and then left child
	
	//q23-iterative postorder using two stacks
	//first add left child and then right child
	//and add popped values in stack
	
	//q24-iterative postorder using one stack
	
	static void q24(Node rt)
	{
		Stack<Node> st=new Stack<>();
		Node curr=null,prev=null;
		st.push(rt);
		while(!st.isEmpty())
		{
			curr=st.peek();
			//downward movement
			if(prev==null || (prev.left==curr) || (prev.right==curr))
			{
				if(curr.left!=null)
				{
					st.push(curr.left);
				}
				else if(curr.right!=null)
				{
					st.push(curr.right);
				}
				else
				{
					st.pop();
					System.out.print(curr.data+" ");
				}
			}
			//upward movement through left child
			else if(curr.left==prev)
			{
				if(curr.right!=null)st.push(curr.right);
				else
				{
					st.pop();
					System.out.print(curr.data+" ");
				}
			}
			//upward movement through right child
			else if(curr.right==prev)
			{
				st.pop();
				System.out.print(curr.data+" ");
			}
			prev=curr;
		}
	}
	
	//q25-postorder traversal using parent map
	
	static void q25(Node rt)
	{
		HashMap<Node,Node> par=new HashMap<>();
		par.put(rt,null);
		Node curr=rt;
		while(curr!=null)
		{
			if(curr.left!=null & !par.containsKey(curr.left))
			{
				par.put(curr.left, curr);
				curr=curr.left;
			}
			else if(curr.right!=null && !par.containsKey(curr.right))
			{
				par.put(curr.right, curr);
				curr=curr.right;
			}
			else
			{
				System.out.print(curr.data+" ");
				curr=par.get(curr);
			}
		}
		System.out.println();
	}
	
	//q26-Diagonal Traversal of Binary Tree
	static void q26(Node rt)
	{
		HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
		q26Util(rt,0,map);
		for(int key:map.keySet())
		{
			for(int j:map.get(key))
			{
				System.out.print(j+" ");
			} 
			System.out.println();
		}
	}

	private static void q26Util(Node rt, int d, HashMap<Integer,ArrayList<Integer>> map)
	{
		if(rt==null)return;
		
		if(!map.containsKey(d))map.put(d, new ArrayList<>());
		
		map.get(d).add(rt.data);
		
		q26Util(rt.left, d+1, map);
		q26Util(rt.right, d, map);
	}
	
	//q27-iterative diagonal traversal
	
	static void q27(Node rt)
	{
		Queue<Node> q=new LinkedList<>();
		q.offer(rt);
		q.offer(null);
		while(!q.isEmpty())
		{
			Node curr=q.poll();
			if(curr==null)
			{
				if(q.isEmpty())break;
				System.out.println();
				q.offer(null);
			}
			else
			{
				while(curr!=null)
				{
					System.out.print(curr.data+" ");
					if(curr.left!=null)q.offer(curr.left);
					curr=curr.right;
				}
			}
		}
	}
	
	//q28-boundary traversal of tree
	//left boundary-if left_child!=null,print curr data and call rec for left child
	//ekse if right_child!=null,print curr data and call rec for right child
	//print leaves using inorder
	//right boundary in bottom-up manner i.e, recursive call is before printing.
	
	//q29-Density of Binary Tree in One Traversal
	//Density of Binary Tree = Size / Height 
	
	//q30-Calculate depth of a full Binary tree from Preorder
	
	//q31-Number of Binary Trees for given Preorder Sequence length
	//catalan numbers-sum over j=0:i-1 of nC(j)*nC(i-1-j)
	
	//q32-Modify a binary tree to get Preorder traversal using right pointers only
	//method1-recursive
	
	static Node q32a(Node rt)
	{
		Node right=rt.right;
		Node rightMost=rt;
		
		if(rt.left!=null)
		{
			rightMost=q32a(rt.left);
			
			rt.right=rt.left;
			
			rt.left=null;
		}
		
		if(right==null)return rightMost;
		
		rightMost.right=right;
		
		rightMost=q32a(right);
		
		return rightMost;
		
	}
	
	//method2-iterative,using preorder traversal and prev.right=curr
}
