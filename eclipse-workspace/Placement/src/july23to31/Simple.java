package july23to31;

import java.io.*;
import java.util.*;

public class Simple {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=true;
    boolean codechef=true;
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        Stack<Integer> st=new Stack<>();
        st.push(0);
        int n=A.size();
        int[] ls=new int[n];
        ls[0]=0;
        for(int i=1;i<n;i++)
        {
            while(!st.isEmpty() && A.get(st.peek())<=A.get(i))st.pop();
            ls[i]=st.isEmpty()?0:st.peek()+1;
            st.push(i);
        }
        st=new Stack<>();
        int[] rs=new int[n];
        rs[n-1]=n-1;
        st.push(n-1);
        for(int i=n-2;i>=0;i--)
        {
            while(!st.isEmpty() && A.get(st.peek())<=A.get(i))st.pop();
            rs[i]=st.isEmpty()?n-1:st.peek()-1;
            st.push(i);
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        HashMap<Integer,HashSet<Long>> map2=new HashMap<>();
        HashMap<Integer,Integer> lastInd=new HashMap<>();
        for(int i=0;i<n;i++)
        {
            if(!map.containsKey(A.get(i)))
            {
                map.put(A.get(i),(rs[i]-i+1)*(i-ls[i]+1));
                HashSet<Long> sett=new HashSet<>();
                sett.add(fnc(ls[i],rs[i]));
                map2.put(A.get(i),sett);
                lastInd.put(A.get(i),i);
            }
            else
            {
                if(!map2.get(A.get(i)).contains(fnc(ls[i],rs[i])))
                {
                    map.put(A.get(i),map.get(A.get(i))+(rs[i]-i+1)*(i-ls[i]+1));
                    map2.get(A.get(i)).add(fnc(ls[i],rs[i]));
                    lastInd.put(A.get(i),i);
                }
                else
                {
                    map.put(A.get(i),map.get(A.get(i))+(rs[i]-i+1)*(i-lastInd.get(A.get(i))));
                    lastInd.put(A.get(i),i);
                }
            }
        }
        //HashMap<Integer,Integer> div=new HashMap<>();
        long mod=1000000007;
        Pair[] list=new Pair[map.size()];
        int ind=0;
        for(int i:map.keySet())
        {
            long val=1;
            for(int j=1;j*j<=i;j++)
            {
                if(i%j==0)
                {
                    val*=j;
                    val%=mod;
                    if(i%(i/j)==0 && j*j!=i)
                    {
                        val*=i/j;
                        val%=mod;
                    }
                }
            }
            Pair pr=new Pair(i,(int)val,map.get(i));
            list[ind++]=pr;
        }
        Arrays.sort(list,new Comparator<Pair>(){
            public int compare(Pair p1,Pair p2)
            {
                return p2.div-p1.div;
            }
        });
        long sum=0;
        for(Pair pr:list)
        {
            sum+=pr.cnt;
            pr.cnt=sum;
            //System.out.println(pr.val+" "+pr.div+" "+pr.cnt);
        }
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i:B)
        {
            Pair tmp=new Pair(0,0,i);
            int ind1=Arrays.binarySearch(list,tmp,new Comparator<Pair>(){
                public int compare(Pair p1,Pair p2)
                {
                    long val= p1.cnt-p2.cnt;
                    if(val<0)return -1;
                    else if(val>0)return 1;
                    return 0;
                }
            });
            if(ind1<0)ind1=-ind1-1;
            if(ind1>=list.length)ind1=list.length-1;
            ans.add(list[ind1].div);
            //System.out.println(ind1+" "+list[ind1].cnt+" "+i);
        }
        for(int i:A)
        {
            //System.out.println(i+" "+map.get(i));
        }
        return ans;
    }
    static class Pair
    {
        int val;
        int div;
        long cnt;
        public Pair(int val,int div,long cnt)
        {
            this.val=val;
            this.div=div;
            this.cnt=cnt;
        }
    }
    
    public long fnc(int a,int b)
    {
        long val=a;
        val+=1000000007*(long)b;
        return val;
    }


    
    void solve()
    {
    	int t=100;
    	while(t-->0)
    	{
    		ArrayList<Integer> a=new ArrayList<>();
        	Random r=new Random();
        	int size=100;
        	for(int i=0;i<size;i++)
        	{
        		a.add(r.nextInt(100)+1);
        	}
        	//System.out.println(a.toString());
        	ArrayList<Integer> ans=new ArrayList<>();
        	for(int i=0;i<size;i++)
        	{
        		for(int j=i;j<size;j++)
        		{
        			int max=Integer.MIN_VALUE;
        			for(int k=i;k<=j;k++)
        			{
        				max=Math.max(max, a.get(k));
        			}
        			long mod=1000000007;
        			long val=1;
                    for(int k=1;k*k<=max;k++)
                    {
                        if(max%k==0)
                        {
                            val*=k;
                            val%=mod;
                            if(max%(max/k)==0 && k*k!=max)
                            {
                                val*=max/k;
                                val%=mod;
                            }
                        }
                    }
                    ans.add((int)val);
        		}
        	}
        	ans.sort(new Comparator<Integer>() {
    			@Override
    			public int compare(Integer o1, Integer o2) {
    				// TODO Auto-generated method stub
    				return o2.compareTo(o1);
    			}
    		});
        	int sz=ans.size();
        	ArrayList<Integer> b=new ArrayList<>();
        	for(int i=0;i<sz;i++)
        	{
        		b.add(i+1);
        	}
        	ArrayList<Integer> ans2=solve(a,b);
        	for(int i=0;i<sz;i++)
        	{
        		//System.out.println(i+" "+ans.get(i)+" "+ans2.get(i));
        		if(ans.get(i).intValue()!=ans2.get(i).intValue())
        		{
        			System.out.println(i+" "+ans.get(i)+" "+ans2.get(i));
        			break;
        		}
        	}
    	}
    	//System.out.println(ans.toString());
    	//System.out.println(ans2.toString());
    }
    /*
    static class Pair
    {
        int a,b;
        public Pair(int a,int b)
        {
            this.a=a;
            this.b=b;
        }
    }*/
    
    static int bit[];
    
    static void add(int x,int d,int n)
    {
        for(int i=x;i<=n;i+=i&-i)bit[i]+=d;
    }

    static int query(int x)
    {
        int ret=0;
        for(int i=x;i>0;i-=i&-i)
        ret+=bit[i];
        return ret;
    }
    
    static long lcm(int a,int b)
    {
        long val=a;
        val*=b;
        return (val/gcd(a,b));
    }
    
    static int gcd(int a,int b)
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
    
    public static void main(String[] args) throws Exception {new Simple().run();}
    
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
