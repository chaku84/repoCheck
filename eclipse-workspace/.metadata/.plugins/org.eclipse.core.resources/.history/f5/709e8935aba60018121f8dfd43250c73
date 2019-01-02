package aug;

import java.io.*;
import java.util.*;

public class Round498
{
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=true;
    boolean codechef=true;
    
	void solve()
	{
		n=ni();m=ni();
		k=nl();
		a=new long[n][m];
		cntMap=new HashMap[n][m];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				a[i][j]=nl();
				cntMap[i][j]=new HashMap<>();
			}
		}
		half=(n+m-2)/2;
		ans=0;
		forward(0,0,0,0);
		backward(n-1, m-1, 0, 0);
		out.println(ans);
	}
	long[][] a;
	int half,n,m;
	long k;
	HashMap<Long,Integer>[][] cntMap;
	long ans;
	
	public void forward(int x,int y,long val,int cnt)
	{
		val^=a[x][y];
		if(cnt==half)
		{
			if(cntMap[x][y].containsKey(val))cntMap[x][y].put(val, cntMap[x][y].get(val)+1);
			else cntMap[x][y].put(val, 1);
			return;
		}
		
		if(x<n-1)forward(x+1,y,val,cnt+1);
		if(y<m-1)forward(x,y+1,val,cnt+1);
	}
	
	public void backward(int x,int y,long val,int cnt)
	{
		if(cnt==n+m-2-half)
		{
			if(cntMap[x][y].containsKey(k^val))
				ans+=cntMap[x][y].get(k^val);
			return;
		}
		if(x>0)backward(x-1, y, val^a[x][y], cnt+1);
		if(y>0)backward(x, y-1, val^a[x][y], cnt+1);
	}
	
	void probE()
	{
		int n=ni(),q=ni();
		int[] from=new int[n];
		int[] to=new int[n];
		int ind=0;
		for(int i=1;i<n;i++)
		{
			int u=ni()-1;
			from[ind++]=u;
			to[ind-1]=i;
		}
		int[][] g=packD(n, from, to);
		for(int[] f:g)
		{
			Arrays.sort(f);
		}
		size=new int[n];
		tra=new ArrayList<>();
		int[] vis=new int[n];
		size(g,0,vis);
		vis=new int[n];
		dfs(g, 0, vis, -1);
		HashMap<Integer,Integer> indMap=new HashMap<>();
		for(int i=0;i<n;i++)
		{
			indMap.put(tra.get(i), i);
		}
		while(q-->0)
		{
			int u=ni()-1,k=ni();
			if(k>size[u])
			{
				out.println(-1);
			}
			else
			{
				int v=tra.get(indMap.get(u)+k-1);
				out.println(v+1);
			}
		}
	}
	
	static int[] size;
	static ArrayList<Integer> tra;
	
	static int size(int[][] g,int u,int[] vis)
	{
		vis[u]=1;
		int sz=0;
		for(int v:g[u])
		{
			if(vis[v]==0)sz+=size(g, v, vis);			
		}
		size[u]=sz+1;
		return sz+1;
	}
	
	static void dfs(int[][] g,int u,int[] vis,int p)
	{
		vis[u]=p;
		tra.add(u);
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
    
    public static void main(String[] args) throws Exception {new Round498().run();}
    
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