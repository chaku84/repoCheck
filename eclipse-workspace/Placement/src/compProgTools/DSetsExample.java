package compProgTools;

import java.io.*;
import java.util.*;

public class DSetsExample {
    InputStream is;
    PrintWriter out;
    String INPUT = "3\r\n" + 
    		"3\r\n" + 
    		"2 3 1\r\n" + 
    		"3\r\n" + 
    		"2 3 6\r\n" + 
    		"4\r\n" + 
    		"2 3 6 1";
    
    String ExpOut="6\r\n" + 
    		"0\r\n" + 
    		"2";
    //boolean codechef=true;
    boolean codechef=true;
    
    void solve()
    {
        ArrayList<Integer>[] pList=new ArrayList[1000006];
        for(int i=0;i<1000006;i++)
        {
            pList[i]=new ArrayList<Integer>();
        }
        int[] lpf=enumLowestPrimeFactors(1000005);
        for(int i=2;i<=1000005;i++)
        {
            int[] tmp=roughFactorFast(i,lpf);
            for(int j:tmp)
            {
                pList[i].add(j);
            }
        }
        int t=ni();
        while(t-->0)
        {
            int n=ni();
        int[] a=na(n);
        int cnt1=0;
        ArrayList<Integer> compList=new ArrayList<>();
        for(int i:a)
        {
            if(i==1)cnt1++;
            for(int j:pList[i])
            {
                compList.add(j);
            }
        }
        int[] cntList=new int[1000006];
        int cnt=0;
        for(int i:compList)
        {
            if(cntList[i]==0)
            {
                cntList[i]=++cnt;
            }
        }
        DSets ds=new DSets(cnt);
        for(int i:a)
        {
            //System.out.println(i+",size:"+pList[i].size());
            for(int j=1;j<=pList[i].size()-1;j++)
            {
                cnt-=ds.unionBySize(cntList[pList[i].get(j-1)],cntList[pList[i].get(j)])?1:0;
            }
        }
        cnt+=cnt1;
        int mod=1000000007;
        long ans=pow(2, cnt, mod);
        ans=(ans-2+mod)%mod;
        out.println(ans);
        }
    }
    
    public static int[] roughFactorFast(int n, int[] lpf)
    {
        int[] rf = new int[9];
        int q = 0;
        int pre = -1;
        while(lpf[n] > 0){
            int p = lpf[n];
            if(q == 0 || p != pre){
                rf[q++] = p;
                pre = p;
            }else{
//                rf[q-1] *= p;
            }
            n /= p;
        }
        return Arrays.copyOf(rf, q);
    }
    
    public static int[] enumLowestPrimeFactors(int n) {
        int tot = 0;
        int[] lpf = new int[n + 1];
        int u = n + 32;
        double lu = Math.log(u);
        int[] primes = new int[(int) (u / lu + u / lu / lu * 1.5)];
        for (int i = 2; i <= n; i++)
            lpf[i] = i;
        for (int p = 2; p <= n; p++) {
            if (lpf[p] == p)
                primes[tot++] = p;
            int tmp;
            for (int i = 0; i < tot && primes[i] <= lpf[p] && (tmp = primes[i] * p) <= n; i++) {
                lpf[tmp] = primes[i];
            }
        }
        return lpf;
    }
    
    static class DSets
    {
        int[] p;
        int[] r;
        int[] s;
        int size;
        int max_component;
        
        public DSets(int n)
        {
            s=new int[n+1];
            r=new int[n+1];
            p=new int[n+1];
            for(int i=1;i<=n;i++)
            {
                r[i]=0;
                p[i]=i;
                s[i]=1;
            }
            this.size=n+1;
        }
        
        public int find(int x)
        {
            if(x != p[x]){
                p[x] = find(p[x]) ;
            }
            return p[x] ;
        }
        
        public boolean unionBySize(int x,int y)
        {
            int rx , ry ;
            rx = find(x) ;
            ry = find(y) ;
            if(rx != ry)
            {
                if(r[rx] < r[ry])
                {
                    p[rx] = ry ;
                    s[ry] += s[rx] ;
                    max_component = Math.max(max_component,s[ry]) ;
                }
                else
                {
                    p[ry] = rx ;
                    s[rx] += s[ry] ;
                    max_component = Math.max(max_component,s[rx]) ;
                }
                if(r[rx] == r[ry])
                {
                    r[rx] ++;
                }
                return true ;
            }
            return false ;
        }
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
    
    public static void main(String[] args) throws Exception {new DSetsExample().run();}
    
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
