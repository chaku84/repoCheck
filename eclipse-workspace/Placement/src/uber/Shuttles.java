package uber;

import java.io.*;
import java.util.*;

public class Shuttles 
{
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int m=in.nextInt();
		LinkedList<Pair>[] g=new LinkedList[n+1];
		for(int i=0;i<=n;i++)
		{
			g[i]=new LinkedList<>();
		}
		for(int i=0;i<m;i++)
		{
			int u=in.nextInt()-1,v=in.nextInt()-1,w=in.nextInt();
			g[u].add(new Pair(v,w));
			g[v].add(new Pair(u,w));
		}
		int[][] dist=new int[n][n];
		for(int i=0;i<n;i++)
		{
			dist[i]=dijkstra(i,g,n);
		}
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				for(int k=i;k<=j;k++)
				{
					dist[i][j]=Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
			}
		}
		HashMap<Integer,Integer> map=new HashMap<>();
		Integer[] a=new Integer[(1<<n)];
		for(int i=0;i<(1<<n);i++)
		{
			int curr=i,cnt=0;
			while(curr!=0)
			{
				if(curr%2==1)cnt++;
				curr/=2;
			}
			a[i]=i;
			map.put(i, cnt);
		}
		Arrays.sort(a, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return map.get(o1.intValue())-map.get(o2.intValue());
			}
		});
		int[][] ans=new int[n][(1<<n)];
		for(int i=0;i<n;i++)
		{
			Arrays.fill(ans[i], 1000000000);
			ans[i][0]=dist[0][i];
		}
		Arrays.fill(ans[0],0);
		int val=1000000000;
		//ans[0][0]=0;
		
		for(int i:a)
		{
			for(int j=0;j<n;j++)
			{
				int ind=n-1;
				int curr=i;
				while(curr!=0)
				{
					if(curr%2==1 && j!=ind)
					{
						ans[j][i]=Math.min(ans[j][i], dist[j][ind]+ans[ind][i^(1<<(n-1-ind))]);
						//System.out.println(ans[j][i]);
					}
					ind--;
					curr/=2;
				}
				//System.out.println(j+" "+i+" "+ans[j][i]);
			}
		}
		System.out.println(ans[n-1][(1<<(n-1))-1]);
		
	}
	
	static int[] dijkstra(int s,LinkedList<Pair>[] g,int n)
	{
		int[] dist=new int[n];
		PriorityQueue<Integer> pq=new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return dist[o1.intValue()]-dist[o2.intValue()];
			}});
		
		Arrays.fill(dist, 1000000000);
		pq.offer(s);
		int[] vis=new int[n+1];
		dist[s]=0;
		while(!pq.isEmpty())
		{
			int curr=pq.poll();
			vis[curr]=1;
			for(Pair j:g[curr])
			{
				if(vis[j.a]==1)
				{
					if(dist[curr]+j.b<dist[j.a])
					{
						dist[j.a]=dist[curr]+j.b;
						pq.remove(j.a);
						pq.add(j.a);
					}
				}
				else
				{
					if(dist[curr]+j.b<dist[j.a])
					{
						dist[j.a]=dist[curr]+j.b;
						//pq.remove(j.a);
						pq.add(j.a);
					}
				}
			}
		}
		return dist;
	}
	
	static class Pair
	{
		int a,b;
		public Pair(int a,int b)
		{
			this.a=a;
			this.b=b;
		}
	}
}
