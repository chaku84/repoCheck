package aug;

import java.io.*;
import java.util.*;

public class AimRnd5
{
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=false;
    boolean codechef=true;
    
	void solve()
	{
		int n=ni();
		int[][] a=new int[n][];
		Comparator<Pair> cmp=new Comparator<Pair>() {

			@Override
			public int compare(Pair p1, Pair p2) {
				// TODO Auto-generated method stub
				if(p1.a==p2.a)return p1.b-p2.b;
				return p1.a-p2.a;
			}};
		TreeSet<Pair> x1=new TreeSet<>(cmp);
		TreeSet<Pair> x2=new TreeSet<>(cmp);
		TreeSet<Pair> y1=new TreeSet<>(cmp);
		TreeSet<Pair> y2=new TreeSet<>(cmp);
		for(int i=0;i<n;i++)
		{
			a[i]=na(4);
			x1.add(new Pair(a[i][0],i));
			y1.add(new Pair(a[i][1],i));
			x2.add(new Pair(a[i][2],i));
			y2.add(new Pair(a[i][3],i));
		}
		for(int i=0;i<n;i++)
		{
			x1.remove(new Pair(a[i][0],i));
			y1.remove(new Pair(a[i][1],i));
		    x2.remove(new Pair(a[i][2],i));
			y2.remove(new Pair(a[i][3],i));
			
			int minx=x1.last().a;
			int miny=y1.last().a;
			int maxx=x2.first().a;
			int maxy=y2.first().a;
			if(minx<=maxx && miny<=maxy)
			{
				out.println(minx+" "+maxy);
				return;
			}
			
			x1.add(new Pair(a[i][0],i));
			y1.add(new Pair(a[i][1],i));
		    x2.add(new Pair(a[i][2],i));
			y2.add(new Pair(a[i][3],i));
		}
		/*
		int[][] pre=new int[n+1][4];
		pre[0]=new int[] {Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
		for(int i=0;i<n;i++)
		{
			pre[i+1][0]=Math.max(pre[i][0],a[i][0]);
			pre[i+1][1]=Math.max(pre[i][1],a[i][1]);
			pre[i+1][2]=Math.min(pre[i][2],a[i][2]);
			pre[i+1][3]=Math.min(pre[i][3],a[i][3]);
		}
		int[][] suf=new int[n+1][4];
		suf[n]=new int[] {Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
		for(int i=n-1;i>=0;i--)
		{
			suf[i][0]=Math.max(suf[i+1][0],a[i][0]);
			suf[i][1]=Math.max(suf[i+1][1],a[i][1]);
			suf[i][2]=Math.min(suf[i+1][2],a[i][2]);
			suf[i][3]=Math.min(suf[i+1][3],a[i][3]);
		}
		for(int i=0;i<n;i++)
		{
			int minx=Math.max(pre[i][0], suf[i+1][0]);
			int miny=Math.max(pre[i][1], suf[i+1][1]);
			int maxx=Math.min(pre[i][2], suf[i+1][2]);
			int maxy=Math.min(pre[i][3], suf[i+1][3]);
			if(minx<=maxx && miny<=maxy)
			{
				out.println(minx+" "+miny);
				return;
			}
		}*/
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
    
    public static void main(String[] args) throws Exception {new AimRnd5().run();}
    
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