package graphTheory;

import java.io.*;
import java.util.*;

public class Dijkstra 
{
	static int vert;
	static HashMap<Integer,LinkedList<Edge>> map;
	static int[] distance;
	
	public static void addEdges(int u,int v,int weight)
	{
		Edge ed1=new Edge(u,v,weight);
		Edge ed2=new Edge(v,u,weight);
		//HashMap<Integer,Edge> m1=new HashMap<Integer,Edge>();
		map.get(u).add(ed1);
		map.get(v).add(ed2);
	}
	
	/*
	 * Time Complexity-O(E+V*log(V)) using fibonacci Heap
	 * Time complexity-O(E*log(V))using binary Heap, only valid for positive weights
	 * whereas bellman Ford Algorithm is also valid for negative weights
	 * but it's time complexity increases to O(E*V)
	 */
	public static void dijkstraShort(int s)
	{
		PriorityQueue<Integer> pq=new PriorityQueue<Integer>(new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return distance[o1.intValue()]-distance[o2.intValue()];
			}});
		
		HashMap<Integer,Integer> visited=new HashMap<Integer,Integer>();
		
		pq.add(s);
		
		
		for(int i=0;i<vert+1;i++)
		{
			distance[i]=-1;
		}
		
		distance[s]=0;
		
		while(!pq.isEmpty())
		{
			int curr=pq.poll();
			
			if(!visited.containsKey(curr))
			{
				visited.put(curr,1);
				
				Collection<Edge> neighbours=map.get(curr);
				
				for(Edge e:neighbours)
				{
					int end=e.end;
					int edgeWeight=e.weight;
					int d=distance[curr]+edgeWeight;
					
					if(distance[end]==-1)
					{
						distance[end]=d;
						pq.add(end);
					}
					if(distance[end]>d)
					{
						distance[end]=d;
						pq.remove(end);    //update the priority of end i.e,remove that element first and then add it again 
						pq.add(end);
					}	
				}
			}
		}
	}
	
	//static ArrayList<Integer> checkArr=new ArrayList<Integer>();
	
	public static long fnc(int a,int b)
	{
		long val=b;
		val*=1000000007;
		return val+a;
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
	
	InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=false;
    boolean codechef=true;
    
    void solve()
	{

    	//Scanner in=new Scanner(System.in);
		int q=ni();
		int flag=0;
		HashMap<Long,Integer> minMap;
		for(int ind=0;ind<q;ind++)
		{
			int vert=ni();
	    	Dijkstra.vert=vert;
			map=new HashMap<Integer,LinkedList<Edge>>();
			distance=new int[vert+1];
			for(int i=1;i<=vert;i++)
			{
				map.put(i,new LinkedList<>());
			}
			int edges=ni();
			minMap=new HashMap<>();
			for(int i=0;i<edges;i++)
			{
				int u=ni();
				int v=ni();
				int w=ni();
				long key=fnc(u,v);
				if(minMap.containsKey(key))
				{
					minMap.put(key,Math.min(w,minMap.get(key)));
				}
				else minMap.put(key, w);
				//addEdges(u,v,w);
			}
			for(long key:minMap.keySet())
			{
				int u=(int) (key%1000000007);
				int v=(int) (key/1000000007);
				
				addEdges(u,v,minMap.get(key));
			}
			int s=ni();
			dijkstraShort(s);
			
			for(int i=1;i<=vert;i++)
			{
				
				if(i!=s)
				{
					//checkArr.add(distance[i]);
					System.out.print(distance[i]+" ");
				}
					
					
			}
			System.out.println();
		}
	}

    void run() throws Exception
    {
        if(codechef)oj=true;
        is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);
        
        long s = System.currentTimeMillis();
        solve();
        out.flush();
        tr(System.currentTimeMillis()-s+"ms");
    }
    
    public static void main(String[] args) throws Exception {new Dijkstra().run();}
    
    private byte[] inbuf = new byte[1024];
    public int lenbuf = 0, ptrbuf = 0;
    
    private int readByte()
    {
        if(lenbuf == -1)throw new InputMismatchException();
        if(ptrbuf >= lenbuf){
            ptrbuf = 0;
            try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
            if(lenbuf <= 0)return -1;
        }
        return inbuf[ptrbuf++];
    }
    
    private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
    private int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
    
    private double nd() { return Double.parseDouble(ns()); }
    private char nc() { return (char)skip(); }
    
    private String ns()
    {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    
    private char[] ns(int n)
    {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while(p < n && !(isSpaceChar(b))){
            buf[p++] = (char)b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }
    
    private char[][] nm(int n, int m)
    {
        char[][] map = new char[n][];
        for(int i = 0;i < n;i++)map[i] = ns(m);
        return map;
    }
    
    private int[] na(int n)
    {
        int[] a = new int[n];
        for(int i = 0;i < n;i++)a[i] = ni();
        return a;
    }
    
    private int ni()
    {
        int num = 0, b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }
        
        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    
    private long nl()
    {
        long num = 0;
        int b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){
            minus = true;
            b = readByte();
        }
        
        while(true){
            if(b >= '0' && b <= '9'){
                num = num * 10 + (b - '0');
            }else{
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    
    private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
    private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }


}


