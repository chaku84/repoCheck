package aug;

import java.io.*;
import java.util.*;

public class EdRnd48C {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=true;
    boolean codechef=true;
    
	void solve()
	{	
		int n=ni();
		long [][] a=new long[2][n];
		for(int i=0;i<n;i++)
		{
			a[0][i]=ni();
		}
		for(int i=0;i<n;i++)
		{
			a[1][i]=ni();
		}
		
		
		//System.out.println(res);
		
		long[][] zig=new long[2][n];
		zig[0][0]=0;
		zig[1][0]=1*a[1][0];
		long cnt=2;
		for(int j=1;j<n;j++)
		{
			if(j%2==0)
			{
				zig[0][j]=zig[0][j-1]+(cnt++)*(a[0][j]);
				zig[1][j]=zig[0][j]+(cnt++)*(a[1][j]);
			}
			else
			{
				zig[1][j]=zig[1][j-1]+(cnt++)*(a[1][j]);
				zig[0][j]=zig[1][j]+(cnt++)*(a[0][j]);
			}
		}
		long[][] back=new long[2][n];
		long[][] backr=new long[2][n];
		back[0][n-1]=a[0][n-1];
		back[1][n-1]=a[1][n-1];
		backr[0][n-1]=n*a[0][n-1];
		backr[1][n-1]=n*a[1][n-1];
		for(int i=n-2;i>=0;i--)
		{
			back[0][i]=back[0][i+1]+a[0][i];
			back[1][i]=back[1][i+1]+a[1][i];
			backr[0][i]=backr[0][i+1]+(long)(i+1)*a[0][i];
			backr[1][i]=backr[1][i+1]+(long)(i+1)*a[1][i];
		}
		//assert(cnt==2*n);
		long ans=Math.max(zig[0][n-1], zig[1][n-1]);
		//long valDown1=backr[1][0]+(0-(1))*back[1][0];
		//long valUp1=-backr[0][0]+(2*n-1+(1))*back[0][0];
		//ans=Math.max(ans, valDown1+valUp1);
		
		long valDown2=-backr[1][0]+(2*n-1+(1))*back[1][0];
		long valUp2=backr[0][0]+(0-(1))*back[0][0];
		ans=Math.max(ans, (long)valDown2+(long)valUp2);
		for(int i=0;i<n-1;i++)
		{
			int now=2*(i+1);
			if(i%2==0)
			{
				//if(i>1)ans=Math.max(ans, zig[1][i]+anti[0][i+1]-anti[1][i]);
				long valDown=backr[1][i+1]+(now-(i+2))*back[1][i+1];
				long valUp=-backr[0][i+1]+(2*n-1+(i+2))*back[0][i+1];
				//System.out.println("i:"+i);
				//System.out.println(zig[1][i]+" "+valDown+" "+valUp);
				ans=Math.max(ans, zig[1][i]+(long)valDown+(long)valUp);
			}
			else
			{
				//ans=Math.max(ans, zig[0][i]+clk[1][i+1]-clk[0][i]);
				long valDown=-backr[1][i+1]+(2*n-1+(i+2))*back[1][i+1];
				long valUp=backr[0][i+1]+(now-(i+2))*back[0][i+1];
				//System.out.println("i:"+i);
				//System.out.println(zig[0][i]+" "+valDown+" "+valUp);
				ans=Math.max(ans, zig[0][i]+valDown+valUp);
			}
			//System.out.println(ans);
		}
		System.out.println(ans);
		/*long orig=orig(a, n);
		if(orig!=ans)
		{
			System.out.println(orig+" "+ans);
			//System.out.println(Arrays.toString(a[0]));
			//System.out.println(Arrays.toString(a[1]));
			break;
		}*/
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
    
    public static void main(String[] args) throws Exception {new EdRnd48C().run();}
    
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