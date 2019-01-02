package string;

import java.util.*;
import java.io.*;

public class KMPalgo {
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		String txt="ABABDABACDABABCABAB";
		int n=txt.length();
		String pat="ABABCABAB";
		int m=pat.length();
		int i=0,j=0;
		int[] lps=new int[m];
		lps(lps,pat,m);
		while(i<n)
		{
			if(txt.charAt(i)==pat.charAt(j))
			{
				i++;j++;
			}
			if(j==m)
			{
				System.out.println(i-j);
				j=lps[j-1];
			}
			else if(i<n && txt.charAt(i)!=pat.charAt(j))
			{
				if(j!=0)
				{
					j=lps[j-1];
				}
				else i++;
			}
		}
	}
	
	static void lps(int[] lps,String s,int n)
	{
		int i=1,len=0;
		while(i<n)
		{
			if(s.charAt(i)==s.charAt(len))
			{
				len++;
				lps[i]=len;
				i++;
			}
			else if(len!=0)
			{
				len=lps[len-1];
			}
			else
			{
				lps[i]=0;
				i++;
			}
		}
	}

}
