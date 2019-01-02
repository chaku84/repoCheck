package uber;

import java.io.*;
import java.util.*;

public class Kcores 
{
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int m=in.nextInt();
		int k=in.nextInt();
		LinkedList<Integer>[] g=new LinkedList[n+1];
		for(int i=0;i<=n;i++)
		{
			g[i]=new LinkedList<>();
		}
		for(int i=0;i<m;i++)
		{
			int u=in.nextInt();
			int v=in.nextInt();
			g[u].add(v);
			g[v].add(u);
		}
		printKcores(g,n,k);
	}

	private static void printKcores(LinkedList<Integer>[] g, int n,int k)
	{
		// TODO Auto-generated method stub
		int[] vis=new int[n+1];
		int[] cntCore=new int[n+1];
		int minCore=g[1].size(),mini=1;
		for(int i=0;i<=n;i++)
		{
			cntCore[i]=g[i].size();
			if(minCore>cntCore[i])
			{
				minCore=cntCore[i];
				mini=i;
			}
		}
		dfs(mini,g,vis,cntCore,k);
		for(int i=0;i<=n;i++)
		{
			if(vis[i]==0)dfs(i,g,vis,cntCore,k);
		}
		for(int i=0;i<=n;i++)
		{
			if(cntCore[i]>=k)
			{
				System.out.print(i+": ");
				for(int j:g[i])
				{
					if(cntCore[j]>=k)System.out.print(j+" ");
				}
				System.out.println();
			}
		}
	}
	
	static boolean dfs(int u,LinkedList<Integer>[] g,int[] vis,int[] cntCore,int k)
	{
		vis[u]=1;
		for(int v:g[u])
		{
			if(cntCore[u]<k)cntCore[v]--;
			
			if(vis[v]==0)
			{
				if(dfs(v,g,vis,cntCore,k))cntCore[u]--;
			}
		}
		return cntCore[u]<k;
	}

}
