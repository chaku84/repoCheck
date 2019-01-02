package aug;

import java.io.*;
import java.util.*;

public class Round505D {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=true;
    boolean codechef=true;
    
    void solve()
	{
		int n=ni();
		int[] a=na(n);
		boolean[][] g=new boolean[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=i;j<n;j++)
			{
				g[i][j]=gcd(a[i],a[j])>=2;
			}
		}
		//1-left,0-right
		boolean[][][] dp=new boolean[2][n][n];
		for(int len=1;len<=n;len++)
		{
			for(int i=0;i<n-len+1;i++)
			{
				int j=i+len-1;
				if(len==1)
				{
					dp[0][i][j]=(i-1>=0) && g[i-1][i];
					dp[1][i][j]=(j+1<n) && g[i][j+1];
				}
				else
				{
					dp[0][i][j]|=(i-1>=0)&& dp[0][i+1][j] && g[i-1][i];
					dp[0][i][j]|=(i-1>=0)&& dp[1][i][j-1] && g[i-1][j];
					dp[1][i][j]|=(j+1<n)&& dp[0][i+1][j] && g[i][j+1];
					dp[1][i][j]|=(j+1<n)&& dp[1][i][j-1] && g[j][j+1];
					
					for(int k=i+1;k<=j-1;k++)
					{
						if(dp[1][i][k-1] && dp[0][k+1][j])
						{
							dp[0][i][j]|=(i-1>=0) && g[i-1][k];
							dp[1][i][j]|=(j+1<n) && g[k][j+1];
						}
					}
				}
			}
		}
		boolean ans=false;
		ans|=dp[0][1][n-1];
		ans|=dp[1][0][n-2];
		for(int k=1;k<n-1;k++)
		{
			ans|=dp[1][0][k-1] && dp[0][k+1][n-1];
		}
		out.println(ans?"Yes":"No");
	}
    
    void solveB()
	{
		int n=ni();
		int[] a=new int[n];
		int[] b=new int[n];
		long ans=0;
		for(int i=0;i<n;i++)
		{
			a[i]=ni();b[i]=ni();
			ans=gcd(ans,lcm(a[i],b[i]));
		}
		for(int i=0;i<n;i++)
		{
			if(gcd(ans,a[i])!=1)
			{
				ans=gcd(ans,a[i]);
			}
			else ans=gcd(ans,b[i]);
		}
		
		out.println(ans==1?-1:ans);
	}
	
	static long orig(long[][] a,int n)
	{
		long[][] sum123=new long[2][n+1];
		long[][] sum321=new long[2][n+1];
		long[][] sum111=new long[2][n+1];
		for(int i = 0; i < 2; ++i)
			for(int j = n - 1; j >= 0; --j){
				sum123[i][j] = sum123[i][j + 1] + (long)(j + 1)  * a[i][j];
				sum321[i][j] = sum321[i][j + 1] + (long)(n - j)  * a[i][j];
				sum111[i][j] = sum111[i][j + 1] + a[i][j];
			}
		
		long res = 0, sum = 0;
		for(int i = 0, j = 0; j < n; ++j, i ^= 1)
		{
			long nres = sum;
			nres += sum123[i][j] + (long)j  * sum111[i][j];
			nres += sum321[i ^ 1][j] + (long)(j + n) * sum111[i ^ 1][j];
			res = Math.max(res, nres);
			
			sum += (long)a[i][j] * (j + j + 1);
			sum += (long)a[i ^ 1][j]  * (j + j + 2);
		}
		
		for(int j = 0; j < n; ++j) res -= (long)a[0][j] + a[1][j];
		return res;
	}
	
	public static long[] radixSort(long[] f){ return radixSort(f, f.length); }
	public static long[] radixSort(long[] f, int n)
	{
		long[] to = new long[n];
		{
			int[] b = new int[65537];
			for(int i = 0;i < n;i++)b[1+(int)(f[i]&0xffff)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[(int)(f[i]&0xffff)]++] = f[i];
			long[] d = f; f = to;to = d;
		}
		{
			int[] b = new int[65537];
			for(int i = 0;i < n;i++)b[1+(int)(f[i]>>>16&0xffff)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[(int)(f[i]>>>16&0xffff)]++] = f[i];
			long[] d = f; f = to;to = d;
		}
		{
			int[] b = new int[65537];
			for(int i = 0;i < n;i++)b[1+(int)(f[i]>>>32&0xffff)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[(int)(f[i]>>>32&0xffff)]++] = f[i];
			long[] d = f; f = to;to = d;
		}
		{
			int[] b = new int[65537];
			for(int i = 0;i < n;i++)b[1+(int)(f[i]>>>48&0xffff)]++;
			for(int i = 1;i <= 65536;i++)b[i]+=b[i-1];
			for(int i = 0;i < n;i++)to[b[(int)(f[i]>>>48&0xffff)]++] = f[i];
			long[] d = f; f = to;to = d;
		}
		return f;
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
    
    public static void main(String[] args) throws Exception {new Round505D().run();}
    
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