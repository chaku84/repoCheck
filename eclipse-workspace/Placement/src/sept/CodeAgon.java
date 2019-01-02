package sept;

import java.io.*;
import java.util.*;

public class CodeAgon {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean debug=true;
    boolean debug=false;
    
    static long mod=998244353;
    
	void solve()
	{
		solveD();
		/*
		int n=ni(),m=ni();
		int[][] ar=new int[n][m];
		ArrayList<Temp> list=new ArrayList<>();
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				ar[i][j]=ni();
				list.add(new Temp(ar[i][j],i+1,j+1));
			}
		}
		list.sort(new Comparator<Temp>() {
			@Override
			public int compare(Temp t1, Temp t2) {
				// TODO Auto-generated method stub
				if(t1.a==t2.a && t1.b==t2.b)return t1.c-t2.c;
				if(t1.a==t2.a)return t1.b-t2.b;
				return t1.a-t2.a;
			}
		});
		int row=ni(),col=ni();
		long a=0,b=0,c=0,d=0;
		long[][] store=new long[4][n*m];
		long[] ans=new long[n*m];
		long[] ansum=new long[n*m];
		for(int i=0;i<n*m;i++)
		{
			Temp curr=list.get(i);
			a+=mult(mult(curr.b,curr.b),1);
			a%=mod;
			b+=mult(mult(curr.c,curr.c),1);
			b%=mod;
			c+=mult(curr.b,2);
			c%=mod;
			d+=mult(curr.c,2);
			d%=mod;
			store[0][i]=a;
			store[1][i]=b;
			store[2][i]=c;
			store[3][i]=d;
			int ind=Collections.binarySearch(list,new Temp(curr.a-1,1000,1000), new Comparator<Temp>() {
				@Override
				public int compare(Temp t1, Temp t2) {
					// TODO Auto-generated method stub
					if(t1.a==t2.a && t1.b==t2.b)return t1.c-t2.c;
					if(t1.a==t2.a)return t1.b-t2.b;
					return t1.a-t2.a;
					}
				});
			if(ind<0)ind=-ind-2;
			//System.out.println("ind:"+ind);
			if(ind<0)
			{
				ans[i]=0;
			}
			else
			{
				ans[i]=ansum[ind]+store[0][ind];
				ans[i]%=mod;
				ans[i]+=(store[1][ind]-mult(store[2][ind],curr.b)+mod)%mod;
				ans[i]%=mod;
				ans[i]+=(mod-mult(store[3][ind],curr.c))%mod;
				ans[i]%=mod;
				ans[i]+=mult((ind+1),mult(curr.b,curr.b));
				ans[i]%=mod;
				ans[i]+=mult((ind+1),mult(curr.c,curr.c));
				ans[i]%=mod;
				ans[i]=mult(ans[i],invl(ind+1,mod));
			}
			if(i>0)
			ansum[i]=(ansum[i-1]+ans[i])%mod;
			//out.println(ans[i]);
			if(curr.b==row && curr.c==col)
			{
				if(ind<0)
				{
					out.println(0);break;
				}
				out.println(ans[i]);
				//return;
				break;
			}
		}*/
		//System.out.println(Arrays.toString(store[0]));
		//System.out.println(Arrays.toString(store[1]));
		//System.out.println(Arrays.toString(store[2]));
		//System.out.println(Arrays.toString(store[3]));
		//System.out.println(Arrays.toString(ans));
	}
	
	public static long invl(long a, long mod) {
		long b = mod;
		long p = 1, q = 0;
		while (b > 0) {
			long c = a / b;
			long d;
			d = a;
			a = b;
			b = d % b;
			d = p;
			p = q;
			q = d - c * q;
		}
		return p < 0 ? p + mod : p;
	}
	
	static long mult(long a,long b)
	{
		a=a%mod;
		b=b%mod;
		long val=(a*b)%mod;
		return val;
	}
	
	static class Temp
	{
		int a,b,c;
		public Temp(int a,int b,int c)
		{
			this.a=a;
			this.b=b;
			this.c=c;
		}
	}
	
	void solveD()
	{
		int n=ni();
		long t=nl();
		int[] a=na(n);
		ArrayList<Long> list=new ArrayList<>();
		list.add((long)0);
		long curr=0;
		for(int i=0;i<n;i++)
		{
			curr+=a[i];
			list.add(curr);
		}
		Collections.sort(list);
		int ind=1;
		
		for(int i=1;i<list.size();i++)
		{
			if(list.get(i)!=list.get(i-1))list.set(ind++,list.get(i));
		}
		while(list.size()!=ind)list.remove(list.size()-1);
		//System.out.println(list.toString());
		long ans=0;
		int sz=list.size();
		curr=0;
		int N=(600 * 1000 + 13);
		//bit=new long[N];
		segt=new long[N];
		ind=Collections.binarySearch(list, (long)0);
		if(ind<0)ind=-ind-2;
		//add(ind+1,1,N);
		update(ind, 0, sz-1, 0);
		for(int i=0;i<n;i++)
		{
			curr+=a[i];
			
			ind=Collections.binarySearch(list,curr-t+1);
			if(ind<0)ind=-ind-1;
			long q=query(0,sz-1, 0, ind-1, 0);
			//System.out.println("npos:"+ind);
			//long q=query(ind);
			//System.out.println("q:"+q);
			ans+=(i+1-q);
			
			int ind2=Collections.binarySearch(list, curr);
			if(ind2<0)ind2=-ind2-2;
			//System.out.println("k:"+ind2);
			//add(ind2+1,1,N);
			update(ind2, 0,sz-1, 0);
		}
		
		out.println(ans);
	}
	
    static long segt[];
	
	static long constSt(long[] a,int st,int end,int si)
	{
		if(st==end)
		{
			segt[si]=a[end];
			return segt[si];
		}
		int mid=(st+end)/2;
		
		long val1=constSt(a,st,mid,2*si+1);
		long val2=constSt(a,mid+1,end,2*si+2);
		
		segt[si]=val1+val2;
		
		return segt[si];
	}
	
	static void update(int ind,int st,int end,int si)
	{
		if(st>ind || end<ind)return;
		segt[si]++;
		if(st!=end)
		{
			int mid=(st+end)/2;
			update(ind,st, mid, 2*si+1);
			update(ind,mid+1, end, 2*si+2);
		}
		//System.out.println("af");
	}
	
	static long query(int st,int end,int qs,int qe,int ind)
	{
		if(qs<=st && end<=qe)
		{
			return segt[ind];
		}
		if(end<qs || qe<st)
		{
			return 0;
		}
		int mid=(st+end)/2;
		return query(st, mid, qs, qe, 2*ind+1)+query(mid+1, end, qs, qe, 2*ind+2);
	}
	
	static long bit[];
    //1 based indexing
    static void add(int x,int d,int n)
    {
        for(int i=x;i<=n;i+=i&-i)bit[i]+=d;
    }

    static long query(int x)
    {
        long ret=0;
        for(int i=x;i>0;i-=i&-i)
        ret+=bit[i];
        return ret;
    }
	
	void solveE()
	{
		//tree construction for given max values of indices of two components
		int n=ni();
		int[] a=new int[n-1];
		for(int i=0;i<n-1;i++)
		{
			int u=ni(),v=ni();
			if(v!=n)
			{
				out.println("NO");
				return;
			}
			a[i]=u;
		}
		Arrays.sort(a);
		int l=0;
		int[] vis=new int[n+1];
		int curr=1;
		ArrayList<Pair> ans=new ArrayList<>();
		while(l<n-1)
		{
			int r=l+1;
			while(r<n-1 && a[l]==a[r])r++;
			int next=n;
			while(l<r-1)
			{
				while(vis[curr]==1)curr++;
				ans.add(new Pair(curr,next));
				next=curr;
				vis[curr]=1;
				l++;
			}
			if(vis[a[l]]==1)
			{
				out.println("NO");
				return;
			}
			ans.add(new Pair(next,a[l]));
			vis[a[l]]=1;
			l++;
		}
		out.println("YES");
		for(Pair p:ans)
		{
			out.println(p.a+" "+p.b);
		}
		out.println();
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
        if(!debug)oj=true;
        is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);
        
        long s = System.currentTimeMillis();
        solve();
        out.flush();
        tr(System.currentTimeMillis()-s+"ms");
    }
    
    public static void main(String[] args) throws Exception {new CodeAgon().run();}
    
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