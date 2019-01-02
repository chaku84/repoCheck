package sept;

import java.io.*;
import java.util.*;


public class Rnd486
{
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=false;
    boolean codechef=true;
    
	void solve()
	{
		char[] a=ns().toCharArray();
		HashMap<Character,Integer> map=new HashMap<>();
		int n=a.length;
		ArrayList<Integer> ind=new ArrayList<>();
		for(int i=0;i<n;i++)
		{
			if(a[i]=='0')ind.add(i);
			if(map.containsKey(a[i]))
			{
				map.put(a[i], Math.max(i, map.get(a[i])));
			}
			else map.put(a[i],i);
		}
		int ans=Integer.MAX_VALUE;
		Collections.sort(ind);
		String[] tmp=new String[] {"25","50","75"};
		for(int i=0;i<3;i++)
		{
			char c1=tmp[i].charAt(0);
			char c2=tmp[i].charAt(1);
			int v1=-1;
			if(map.containsKey(c1))v1=map.get(c1);
			int v2=-1;
			if(map.containsKey(c2))v2=map.get(c2);
			if(v1!=-1 && v2!=-1)
			{
				if(v1<v2)ans=Math.min(ans, n-1-v2+n-2-v1);
				else ans=Math.min(ans, n-1-v2+n-2-(v1-1));
			}
			System.out.println(tmp[i]);
			System.out.println(ans);
		}
		int sz=ind.size();
		if(sz>=2)
		{
			int f1=ind.get(sz-1);
			int f2=ind.get(sz-2);
			ans=Math.min(ans, n-1-f1+n-2-f2);
		}
		if(ans==Integer.MAX_VALUE)
		{
			out.println(-1);
		}
		else out.println(ans);
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
	
	void solveC()
	{
		int k=ni();
		int[][] a=new int[k][];
		//Pair[][] arr=new Pair[k][];
		long[] sum=new long[k];
		int tot=0;
		for(int i=0;i<k;i++)
		{
			int len=ni();
			tot+=len;
			a[i]=new int[len];
			//arr[i]=new Pair[len];
			for(int j=0;j<len;j++)
			{
				a[i][j]=ni();
				sum[i]+=a[i][j];
			}
			
			
			//Arrays.sort(a[i]);
		}
		Pair[] arr=new Pair[tot];
		int ind=0;
		for(int i=0;i<k;i++)
		{
			int len=a[i].length;
			//arr[i]=new Pair[len];
			for(int j=0;j<len;j++)
			{
				arr[ind++]=new Pair(sum[i]-a[i][j],i,j);
			}
			
			
			//Arrays.sort(a[i]);
		}
		Arrays.sort(arr,new Comparator<Pair>() {

			@Override
			public int compare(Pair o1, Pair o2) {
				// TODO Auto-generated method stub
				long diff=o1.a-o2.a;
				if(diff<0)return -1;
				else if(diff==0)
				{
					return o1.b-o2.b;
				}
				return 1;
			}
		});
		for(int i=1;i<tot;i++)
		{
			if(arr[i].a==arr[i-1].a && arr[i].b!=arr[i-1].b)
			{
				out.println("YES");
				out.println((arr[i-1].b+1)+" "+(arr[i-1].c+1));
				out.println((arr[i].b+1)+" "+(arr[i].c+1));
				return;
			}
		}
		out.println("NO");
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
    	long a;
        int b,c;
        public Pair(long a,int b,int c)
        {
            this.a=a;
            this.b=b;
            this.c=c;
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
    
    public static void main(String[] args) throws Exception {new Rnd486().run();}
    
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