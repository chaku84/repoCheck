package graphTheory;

import java.io.*;
import java.util.*;

public class KruskalsAlgo {
  
  static int vert;
	static HashMap<Integer,LinkedList<Edge>> map;
	static ArrayList<Edge> allEdges;
	static int[] distance;
  static int size;
  public static int[] s;
  
	public KruskalsAlgo(int v)
	{
		vert=v;
		map=new HashMap<Integer,LinkedList<Edge>>();
		distance=new int[vert+1];
		allEdges=new ArrayList<Edge>();
		for(int i=1;i<=v;i++)
		{
			map.put(i,new LinkedList<Edge>());
		}
	}
	
	public static void addEdges(int u,int v,int weight)
	{
		Edge ed1=new Edge(u,v,weight);
		Edge ed2=new Edge(v,u,weight);
		allEdges.add(ed2);
		allEdges.add(ed1);
		map.get(u).add(ed1);
		map.get(v).add(ed2);
	}
  
  public static void makeSet(int size)
	{
		s=new int[size];
		for(int i=0;i<size;i++)
		{
			s[i]=-1;
		}
	}
	
	public static int find(int x)
	{
		//System.out.println("x:"+x);
		//System.out.println("size:"+size);
		if(x<0 || x>=size)return 0;
		if(s[x]<0)return x;
		else return s[x]=find(s[x]);
	}
	
	public static void unionBySize(int root1,int root2)
	{
		root1=find(root1);
		root2=find(root2);
		//System.out.println("root1:"+root1+",root2:"+root2);
		if(root1==root2 && root1!=-1)return;
		//System.out.println(s[root2]+" "+s[root1]);
		if(s[root2]<s[root1])  //size of root2>root1
		{
			s[root2]+=s[root1]; // updating the size of root2
			s[root1]=root2;   //root2 is set to parent of root1
		}
		else
		{
			s[root1]+=s[root2];   // updating the size of root2
			s[root2]=root1;   //root1 is set to parent of root2
			//System.out.println("dg"+s[root1]);
		}
	}

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      Scanner in=new Scanner(System.in);
      int n=in.nextInt();
      new KruskalsAlgo(n);
      int m=in.nextInt();
      for(int i=0;i<m;i++)
      {
        int u=in.nextInt();
        int v=in.nextInt();
        int w=in.nextInt();
        addEdges(u,v,w);
      }
      kruskalsAlgo();
    }
  
  public static void kruskalsAlgo()
	{
		allEdges.sort(
				new Comparator<Edge>()
				{
			       @Override
			       public int compare(Edge o1, Edge o2) 
			       {
				       // TODO Auto-generated method stub
				       return o1.weight>o2.weight?1:-1;
			       }
		        });
		
		ArrayList<Edge> MSTEdges=new ArrayList<Edge>();
		size=vert+1;
		makeSet(vert+1);
		for(Edge e:allEdges)
		{
			int u=e.start;
			int v=e.end;
			if(find(u)!=find(v))
			{
				MSTEdges.add(e);
				unionBySize(u,v);
			}
			//System.out.println(e.weight);
		}
		int sum=0;
		for(Edge e:MSTEdges)
		{
			sum+=e.weight;
		}
    System.out.println(sum);
	}
  
  static class Edge
  {
		int start;
		int end;
		int weight;
		
		public Edge(int start,int end,int weight)
		{
			this.start=start;
			this.end=end;
			this.weight=weight;
		}
	}
}
