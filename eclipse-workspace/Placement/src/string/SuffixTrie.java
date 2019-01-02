package string;

import java.io.*;
import java.util.*;

public class SuffixTrie {
	
	static class SuffixTrieNode
	{
		final static int MAX_CHAR = 256;
		
		SuffixTrieNode[] children=new SuffixTrieNode[MAX_CHAR];
		List<Integer> indices;
		
		public SuffixTrieNode()
		{
			for(int i=0;i<MAX_CHAR;i++)
			{
				children[i]=null;
			}
			indices=new LinkedList<>();
		}
		
		public void insert(char[] a,int ind)
		{
			indices.add(ind);
			
			if(ind<a.length)
			{
				char curr=a[ind];
				
				if(children[curr]==null)
					children[curr] = new SuffixTrieNode();
				
				children[curr].insert(a, ind+1);
			}
		}
		
		public List<Integer> search(char[] a,int ind)
		{
			if(ind>=a.length)return indices;
			
			if(children[a[ind]]!=null)
				return children[a[ind]].search(a,ind+1);
			
			else return null;
		}
	}
	
	SuffixTrieNode root=new SuffixTrieNode();
	
	public SuffixTrie(char[] a)
	{
		for(int i=0;i<a.length;i++)
		{
			root.insert(a, i);
		}
	}
	
	void search_trie(char[] pat)
	{
		List<Integer> ans=root.search(pat,0);
		
		if (ans == null)
            System.out.println("Pattern not found");
        else
        {
 
            int patLen = pat.length;
 
            for (Integer i : ans)
                System.out.println("Pattern found at position " +
                                                (i - patLen));
        }
	}
	
	public static void main(String args[]) 
	{   
        String txt = "geeksforgeeks.org";
        SuffixTrie S = new SuffixTrie(txt.toCharArray());
 
        System.out.println("Search for 'ee'");
        S.search_trie("ee".toCharArray());
 
        System.out.println("\nSearch for 'geek'");
        S.search_trie("geek".toCharArray());
 
        System.out.println("\nSearch for 'quiz'");
        S.search_trie("quiz".toCharArray());
 
        System.out.println("\nSearch for 'forgeeks'");
        S.search_trie("forgeeks".toCharArray());
    }
}
