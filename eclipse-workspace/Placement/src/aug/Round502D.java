package aug;

import java.io.*;
import java.util.*;

public class Round502D
{
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=true;
    boolean codechef=true;
    
	void solve()
	{
		int n=ni(),m=ni(),q=ni();
		int[] w=na(n);
		int[] cost=new int[(1<<n)];
		for(int i=0;i<(1<<n);i++)
		{
			for(int j=0;j<n;j++)
			{
				if(((i>>j)&1)==0)cost[i]+=w[j];
			}
		}
		int[] a=new int[(1<<n)];
		for(int i=0;i<m;i++)
		{
			int v=0;
			char[] s=ns(n);
			for(int j=0;j<n;j++)
			{
				if(s[j]=='1')v^=(1<<j);
			}
			a[v]++;
		}
		int[][] b=new int[(1<<n)][101];
		for(int i=0;i<(1<<n);i++)
		{
			for(int j=0;j<(1<<n);j++)
			{
				if(cost[i^j]<101)
				b[i][cost[i^j]]+=a[j];
			}
		}
		while(q-->0)
		{
			int v=0;
			char[] s=ns(n);
			for(int j=0;j<n;j++)
			{
				if(s[j]=='1')v^=(1<<j);
			}
			int k=ni();
			int ans=0;
			for(int i=0;i<=k;i++)
			{
				ans+=b[v][i];
			}
			out.println(ans);
		}
	}
	
	static void dfs(int[][] g,int u,int[] vis,int p)
	{
		vis[u]=p;
		for(int v:g[u])
		{
			if(vis[v]==0)dfs(g,v,vis,u);			
		}
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
    
    public boolean check(int[] cnt,int m)
    {
    	boolean ch=true;
    	for(int i=2;i<=m;i++)
		{
			if(cnt[i]>=cnt[1])
			{
				ch=false;break;
			}
		}
		return ch;
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
    
    public static void main(String[] args) throws Exception {new Round502D().run();}
    
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