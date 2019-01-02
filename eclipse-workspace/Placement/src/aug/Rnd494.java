package aug;

import java.io.*;
import java.util.*;


public class Rnd494
{
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=false;
    boolean codechef=true;
    
	void solve()
	{
		int n=ni(),d=ni(),k=ni();
		if(k==2 && n!=d+1)
		{
			out.println("NO");
			return;
		}
		d++;
		if(d>n)
		{
			out.println("NO");
			return;
		}
		depth=new int[d];
		long sum=0;
		
		for(int i=1;i<d-1;i++)
		{
			
			depth[i]=Math.min(i, d-1-i);
			long val=1;
			long tmp=0;
			for(int j=0;j<depth[i];j++)
			{
				if(k==2)
				{
					sum=n-d;break;
				}
				tmp+=val;
				if(tmp>=n-d)break;
				val*=(k-1);
			}
			
			sum+=(long)(k-2)*(tmp);
			if(sum>=n-d)break;
		}
		//System.out.println(Arrays.toString(depth));
		//System.out.println(sum);
		if(sum<n-d)
		{
			out.println("NO");
			return;
		}
		out.println("YES");
		for(int i=1;i<d;i++)
		{
			out.println(i+" "+(i+1));
		}
		int rem=n-d;
		int curr=d+1;
		for(int i=2;i<=d-1;i++)
		{
			if(rem==0)break;
			Queue<Integer> q=new LinkedList<>();
			for(int j=0;j<k-2;j++)
			{
				q.offer(curr++);
				out.println(i+" "+(curr-1));
				rem--;
				if(rem==0)break;
			}
			if(rem==0)break;
			q.offer(null);
			int lv=1;
			while(!q.isEmpty() && depth[i-1]>lv)
			{
				Integer c=q.poll();
				if(c!=null)
				{
					for(int j=0;j<k-1;j++)
					{
						q.offer(curr++);
						out.println(c.intValue()+" "+(curr-1));
						rem--;
						if(rem==0)break;
					}
					if(rem==0)break;
				}
				else if(!q.isEmpty())
				{
					if(lv==depth[i-1])break;
					lv++;
					q.offer(null);
				}
			}
			if(rem==0)break;
		}
	}
	
	static int[] depth;
	static int dfs(int[][] g,int u,int[] vis,int p)
	{
		vis[u]=p;
		if(g[u].length==0)
		{
			depth[u]=0;
			return 0;
		}
		int min=Integer.MAX_VALUE;
		for(int v:g[u])
		{
			if(vis[v]==0)min=Math.min(min, dfs(g,v,vis,u));			
		}
		depth[u]=min+1;
		return min+1;
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
    
    public static void main(String[] args) throws Exception {new Rnd494().run();}
    
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