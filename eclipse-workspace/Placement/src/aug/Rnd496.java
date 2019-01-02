package aug;

import java.io.*;
import java.util.*;


public class Rnd496
{
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=false;
    boolean codechef=true;
    
	void solve()
	{
		int n=ni(),m=ni();
		int[] a=na(n);
		int[] lf=new int[n];
		int[] mf=new int[n];
		
		ArrayList<Integer> indices=new ArrayList<>();
		HashMap<Integer,Integer> map=new HashMap<>();
		for(int i=0;i<n;i++)
		{
			if(a[i]>m)
			{
				lf[i]=i==0?0:lf[i-1];
				mf[i]=i==0?1:mf[i-1]+1;
			}
			else if(a[i]<m)
			{
				lf[i]=i==0?1:lf[i-1]+1;
				mf[i]=i==0?0:mf[i-1];
			}
			else
			{
				indices.add(i);
				lf[i]=i==0?0:lf[i-1];
				mf[i]=i==0?0:mf[i-1];
			}
		}
		map.put(0, 1);
		System.out.println(Arrays.toString(lf));
		System.out.println(Arrays.toString(mf));
		long ans=0;
		for(int i=0;i<n;i++)
		{
			System.out.println(lf[i]-mf[i]);
			
			if(i>=indices.get(0))
			{
				if(map.containsKey(lf[i]-mf[i]))
					ans+=map.get(lf[i]-mf[i]);
				if(map.containsKey(lf[i]-mf[i]+1))
				{
					ans+=map.get(lf[i]-mf[i]+1);
				}
				System.out.println("ans:"+ans);
			}
			if(map.containsKey(lf[i]-mf[i]))
			{
				map.put(lf[i]-mf[i], map.get(lf[i]-mf[i])+1);
			}
			else map.put(lf[i]-mf[i],1);
		}
		System.out.println(ans);
	}
	
	void solveE1()
	{
		int n=ni(),m=ni();
		int[] p=na(n);
		int[] l1=new int[n];
		int[] l2=new int[n];
		int[] m1=new int[n];
		int[] m2=new int[n];
		int ind=0;
		for(int i=0;i<n;i++)
		{
			if(p[i]==m)
			{
				ind=i;break;
			}
		}
		for(int i=ind-1;i>=0;i--)
		{
			if(p[i]<m)
			{
				l1[i]=l1[i+1]+1;
				m1[i]=m1[i+1];
			}
			else if(p[i]>m)
			{
				l1[i]=l1[i+1];
				m1[i]=m1[i+1]+1;
			}
			else
			{
				l1[i]=l1[i+1];
				m1[i]=m1[i+1];
			}
		}
		HashMap<Integer,Integer> map=new HashMap<>();
		for(int i=ind+1;i<n;i++)
		{
			if(p[i]<m)
			{
				l2[i]=l2[i-1]+1;
				m2[i]=m2[i-1];
			}
			else if(p[i]>m)
			{
				l2[i]=l2[i-1];
				m2[i]=m2[i-1]+1;
			}
			else
			{
				l2[i]=l2[i-1];
				m2[i]=m2[i-1];
			}
			if(map.containsKey(m2[i]-l2[i]))
			{
				map.put(m2[i]-l2[i], map.get(m2[i]-l2[i])+1);
			}
			else
			{
				map.put(m2[i]-l2[i], 1);
			}
		}
		long ans=0;
		int cnt0=0;
		for(int i=0;i<ind;i++)
		{
			//System.out.println(l1[i]+" "+m1[i]);
			if(map.containsKey(l1[i]-m1[i]+1))
			    ans+=map.get(l1[i]-m1[i]+1);
			if(map.containsKey(l1[i]-m1[i]))
				ans+=map.get(l1[i]-m1[i]);
			if(l1[i]-m1[i]+1==0)cnt0++;
			if(l1[i]-m1[i]==0)cnt0++;
			//System.out.println(ans);
		}
		//System.out.println(Arrays.toString(l1));
		//System.out.println(Arrays.toString(m1));
		//System.out.println(Arrays.toString(l2));
		//System.out.println(Arrays.toString(m2));
		ans+=cnt0;
		if(map.containsKey(0))ans+=map.get(0);
		if(map.containsKey(1))ans+=map.get(1);
		ans++;
		System.out.println(ans);
	}
	
	static void dfs(int[][] g,int u,int[] vis,int p)
	{
		vis[u]=p;
		for(int v:g[u])
		{
			if(vis[v]==0)dfs(g,v,vis,u);			
		}
	}
	
	static long fnc(long a,long b)
	{
		return a+(1000000007)*b;
	}
	
	static int[][] packD(int n, int[] from, int[] to) {
		int[][] g = new int[n][];
		int[] p = new int[n];
		for (int f : from)
			p[f]++;
		for (int i = 0; i < n; i++)
			g[i] = new int[p[i]];
		for (int i = 0; i < from.length; i++) {
			g[from[i]][--p[from[i]]] = to[i];
		}
		return g;
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
    
    static long lcm(int a,int b)
    {
        long val=a;
        val*=b;
        return (val/gcd(a,b));
    }
    
    static long gcd(long a,long b)
    {
        if(a==0)return b;
        return gcd(b%a,a);
    }
    
    static int pow(int a, int b, int p)
    {
        long ans = 1, base = a;
        while (b!=0)
        {
            if ((b & 1)!=0)
            {
                ans *= base;
                ans%= p;
            }
            base *= base;
            base%= p;
            b >>= 1;
        }
        return (int)ans;
    }

    static int inv(int x, int p)
    {
        return pow(x, p - 2, p);
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
    
    public static void main(String[] args) throws Exception {new Rnd496().run();}
    
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