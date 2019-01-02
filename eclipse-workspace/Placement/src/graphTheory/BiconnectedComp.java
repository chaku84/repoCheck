package graphTheory;

import java.util.*;
import java.io.*;

public class BiconnectedComp implements Runnable
{
	
	public static void main(String[] args)
	{
		new Thread(null,new BiconnectedComp(),"Main",1<<26).start();
	}
	
	public void run()
	{
		Scanner in=new Scanner(System.in);
		
	}
	
	public void bcc(int[][] g,int n)
	{
		int[] parent=new int[n];
		int[] low=new int[n];
		int[] disc=new int[n];
		Arrays.fill(parent,-1);
		Arrays.fill(low,-1);
		Arrays.fill(disc,-1);
		
		time=0;
		Stack<Pair> st=new Stack<>();
		for(int i=0;i<n;i++)
		{
			if(disc[i]==-1)
			{
				bccUtil(i,g,parent,low,disc,st);
			}
			int f=0;
			while(!st.isEmpty())
			{
				f=1;
				Pair tmp=st.pop();
				System.out.print(tmp.a+":"+tmp.b+" ");
			}
			if(f==1)
			{
				System.out.println();
				count++;
			}
		}
		
	}
	
	static int time;
	static int count;

	private void bccUtil(int u, int[][] g, int[] parent, int[] low, int[] disc,Stack<Pair> st) {
		// TODO Auto-generated method stub
		disc[u]=low[u]=++time;
		int child=0;
		for(int v:g[u])
		{
			if(disc[v]==-1)
			{
				parent[v]=u;
				child++;
				st.push(new Pair(u,v));
				
				//check for articulation in subtree
				bccUtil(v, g, parent, low, disc,st);
				
				low[u]=Math.min(low[u], low[v]);
				
				
				if((disc[u]==1 && child>1) || (low[v]>=disc[u] && disc[u]>1))
				{
					while(!st.empty() && !(st.peek().a==u && st.peek().b==v))
					{
						Pair tmp=st.pop();
						System.out.print(tmp.a+":"+tmp.b+" ");
					}
					Pair tmp=st.pop();
					System.out.println(tmp.a+":"+tmp.b);
					count++;
				}
			}
			else if(v!=parent[u])
			{
				low[u]=Math.min(low[u], disc[v]);
				if(disc[v]<disc[u])
				{
					st.push(new Pair(u,v));
				}
			}
		}
	}
	static class Pair
	{
		int a;
		int b;
		public Pair(int a,int b)
		{
			this.a=a;
			this.b=b;
		}
	}
}
