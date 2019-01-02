
package sept;

import java.io.*;
import java.util.*;

public class Solution implements Runnable {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=false;
    boolean codechef=true;
    static long mod=1000000007;
	void solve() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("C:/Users/DEEPAK/Desktop/B-large-practice.txt"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("C:/Users/DEEPAK/Desktop/B-large-practice-out.txt"));
		int t=Integer.parseInt(br.readLine());
		for(int test=1;test<=t;test++)
		{
			String[] str=br.readLine().split("[\\s]+");
			int n=Integer.parseInt(str[0]);
			int q=Integer.parseInt(str[1]);
			
			int[] x=new int[n+1];
			int[] y=new int[n+1];
			int[] z=new int[q+1];
			int[] a=new int[3];
			int[] b=new int[3];
			int[] c=new int[3];
			int[] m=new int[3];
			
			for(int i=0;i<3;i++)
			{
				str=br.readLine().split("[\\s]+");
				a[i]=Integer.parseInt(str[2]);
				b[i]=Integer.parseInt(str[3]);
				c[i]=Integer.parseInt(str[4]);
				m[i]=Integer.parseInt(str[5]);
				if(i==0)
				{
					x[0]=Integer.parseInt(str[0]);
					x[1]=Integer.parseInt(str[1]);
				}
				else if(i==1)
				{
					y[0]=Integer.parseInt(str[0]);
					y[1]=Integer.parseInt(str[1]);
				}
				else
				{
					z[0]=Integer.parseInt(str[0]);
					z[1]=Integer.parseInt(str[1]);
				}
			}
			//System.out.println(Arrays.toString(a));
			//System.out.println(Arrays.toString(b));
			//System.out.println(Arrays.toString(c));
			//System.out.println(Arrays.toString(m));
			HashMap<Integer,Long> map=new HashMap<>();
			int[] l=new int[n+1];
			int[] r=new int[n+1];
			int[] k=new int[q+1];
			l[0]=Math.min(x[0], y[0])+1;
			r[0]=Math.max(x[0], y[0])+1;
			//k[0]=z[0]+1;
			
			l[1]=Math.min(x[1], y[1])+1;
			r[1]=Math.max(x[1], y[1])+1;
			//k[1]=z[1]+1;
			
			for(int i=2;i<n;i++)
			{
				x[i]=(int) (((long)a[0]*x[i-1]+(long)b[0]*(x[i-2])+c[0])%m[0]);
				y[i]=(int) (((long)a[1]*y[i-1]+(long)b[1]*(y[i-2])+c[1])%m[1]);
				//z[i]=(int) ((long)a[2]*z[i-1]+(long)b[2]*(z[i-2]+c[2])%m[2]);
				l[i]=Math.min(x[i], y[i])+1;
				r[i]=Math.max(x[i], y[i])+1;
			}
			Pair[] query=new Pair[q];
			for(int i=0;i<q;i++)
			{
				if(i>=2)
				{
					z[i]=(int) (((long)a[2]*z[i-1]+(long)b[2]*(z[i-2])+c[2])%m[2]);
				}
				k[i]=z[i]+1;
				query[i]=new Pair(k[i],i);
			}
			//System.out.println(Arrays.toString(l));
			Comparator<Pair> cmp=new Comparator<Pair>() {
				@Override
				public int compare(Pair arg0, Pair arg1) {
					// TODO Auto-generated method stub
					long val=arg0.a-arg1.a;
					if(val<0)return -1;
					else if(val>0)return 1;
					return (int) (arg0.b-arg1.b);
				}};
			Arrays.sort(query, cmp);
			//System.out.println(Arrays.toString(r));
			//System.out.println(Arrays.toString(k));
			ArrayList<Integer> list=new ArrayList<>();
			for(int i=0;i<n;i++)
			{
				if(map.containsKey(l[i]))
				{
					map.put(l[i], map.get(l[i])+1);
				}
				else map.put(l[i],(long)1);
				if(map.containsKey(r[i]+1))
				{
					//list.add(r[i]+1);
					map.put(r[i]+1, map.get(r[i]+1)-1);
				}
				else map.put(r[i]+1,(long)-1);
			}
			list.addAll(map.keySet());
			Collections.sort(list);
			
			long sum=map.get(list.get(0));
			for(int i=1;i<list.size();i++)
			{
				sum+=map.get(list.get(i));
				map.put(list.get(i), sum);
			}
			
			int prev=-1;
			long cnt=0;
			boolean ch=false;
			for(int i=list.size()-1;i>=0;i--)
			{
				if(prev!=-1)
				{
					long next=cnt+(prev-1-list.get(i)+1)*map.get(list.get(i));
					long v=map.get(list.get(i));
					int from=prev-1;
					int to=list.get(i);
					//long val=k[0]-cnt;
					int lind=Arrays.binarySearch(query,new Pair(cnt+1,0),cmp);
					if(lind<0)lind=-lind-1;
					int rind=Arrays.binarySearch(query,new Pair(next+1,-1),cmp);
					if(rind<0)rind=-rind-2;
					else rind--;
					for(int j=lind;j<=rind;j++)
					{
						long val=query[j].a-cnt;
						if(val%v==0)
						{
							query[j].c=prev-val/v;
							//ch=true;
							//bw.write("Case #"+test+": "+(prev-val/v)+"\n");
							//out.println(prev-val/v);
							//break;
						}
						else
						{
							query[j].c=(prev-(val/v+1));
							//ch=true;
							//bw.write("Case #"+test+": "+(prev-(val/v+1))+"\n");
							//out.println(prev-(val/v+1));
							//break;
						}
					}
					cnt=next;
				}
				else
				{
					int rind=Arrays.binarySearch(query,new Pair(map.get(list.get(i))+1,-1),cmp);
					if(rind<0)rind=-rind-2;
					else rind--;
					for(int j=0;j<=rind;j++)
					{
						query[j].c=list.get(i);
					}
					cnt+=map.get(list.get(i));
				}
				prev=list.get(i);	
			}
			long ans=0;
			for(int i=0;i<q;i++)
			{
				//System.out.println(query[i].b+" "+query[i].c+" "+query[i].a);
				
				ans+=query[i].c*(query[i].b+1);
			}
			bw.write("Case #"+test+": "+(ans)+"\n");
			out.println(ans);
		}
		bw.close();
	}
	
	static class Pair
	{
		long a,b,c;
		public Pair(long l,int b)
		{
			this.a=l;
			this.b=b;
			this.c=0;
		}
	}

	static int brute(int[] a,int n)
	{
		int ans=0;
		for(int i=0;i<n;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				for(int k=j+1;k<n;k++)
				{
					if((long)a[i]*a[j]==a[k] || (long)a[k]*a[j]==a[i] || (long)a[i]*a[k]==a[j])
					{
						ans++;
					}
				}
			}
		}
		return ans;
	}


    
    public void run()
    {
        if(codechef)oj=true;
        is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);
        
        long s = System.currentTimeMillis();
        try {
			solve();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.flush();
        tr(System.currentTimeMillis()-s+"ms");
    }
    
    public static void main(String[] args) throws Exception {new Thread(null,new Solution(),"Main",1<<26).start();}
    
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