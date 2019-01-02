package july23to31;

import java.io.*;
import java.util.*;

public class RivigoQ1 {
    InputStream is;
    PrintWriter out;
    String INPUT = "";
    //boolean codechef=true;
    boolean codechef=true;
    
    void solve()
    {
        map=new HashMap<>();
        int t=ni(),s=ni();
        ow=ni();
        ids=new HashSet<>();
        //int a2b=0,b2a=0;
        for(int i=0;i<t;i++)
        {
            char ch1=nc();
            char ch2=nc();
            if(ch1=='A' && ch2=='B')a2b=ni();
            else b2a=ni();
            //System.out.println(ch1 +" "+ch2);
        }
        Comparator<Pair> cmp=new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                // TODO Auto-generated method stub
                if(o1.b==o2.b)return o1.a-o2.a;
                return o1.b-o2.b;
            }
        };
        TreeSet<Pair> a2bSet=new TreeSet<>(cmp);
        TreeSet<Pair> b2aSet=new TreeSet<>(cmp);
        HashMap<Integer,Integer> a2bMap=new HashMap<>();
        HashMap<Integer,Integer> b2aMap=new HashMap<>();
        for(int i=0;i<s;i++)
        {
            int id=ni();
            ids.add(id);
            char ch1=nc();
            int stTm=ni();
            char ch2=nc();
            if(ch1=='A' && ch2=='B')
            {
                Pair pr=new Pair(id,stTm);
                a2bSet.add(pr);
                a2bMap.put(id, stTm);
            }
            else 
            {
                Pair pr=new Pair(id,stTm);
                b2aSet.add(pr);
                b2aMap.put(id,stTm);
            }
        }
        RetType cost=minCost(a2bSet, b2aSet);
        System.out.println(cost.ll.size());
        for(Pair pr:cost.ll)
        {
            //System.out.println(pr.a+" "+pr.b);
            if(pr.a==-1 || pr.b==-1)
            {
                int curId=pr.a;
                if(curId==-1)curId=pr.b;
                if(a2bMap.containsKey(curId))
                {
                    System.out.println("A B "+curId+" -1 -1");
                }
                if(b2aMap.containsKey(curId))
                {
                    System.out.println("B A "+curId+" -1 -1");
                }
            }
            else if(a2bMap.containsKey(pr.a))
            {
                int st1=a2bMap.get(pr.a);
                int end2=st1+a2b;
                int st2=b2aMap.get(pr.b);
                if(st2>=end2)
                {
                    System.out.println("A B "+pr.a+" "+pr.b+" B");
                }
                else
                {
                    System.out.println("A B "+pr.a+" "+pr.b+" A");
                }
            }
            else
            {
                int st1=a2bMap.get(pr.b);
                int end2=st1+a2b;
                int st2=b2aMap.get(pr.a);
                if(st2>=end2)
                {
                    System.out.println("A B "+pr.b+" "+pr.a+" B");
                }
                else
                {
                    System.out.println("A B "+pr.b+" "+pr.a+" A");
                }
            }
        }
        System.out.println(cost.val);
        //System.out.println(cnt);
  
    }
    
    public int compCost(int a)
    {
        return a<=10?a:10+2*(a-10);
    }
    
    static int a2b,b2a,ow;
    static int cnt=0;
    static HashMap<HashSet<Integer>,RetType> map;
    static HashSet<Integer> ids;
    public RetType minCost(TreeSet<Pair> a2bSet,TreeSet<Pair> b2aSet)
    {
        //if(map.containsKey(ids))
        //{
            //return map.get(ids);
        //}
        cnt++;
        Pair a2bCurr=null;
        if(a2bSet.size()!=0)a2bCurr=a2bSet.first();
        
        Pair b2aCurr=null;
        if(b2aSet.size()!=0)b2aCurr=b2aSet.first();
        
        if(a2bCurr==null && b2aCurr==null)
        {
            RetType rt=new RetType();
            rt.ll=new LinkedList<>();
            rt.val=0;
            return rt;
        }
        
        Pair a2bNext=null;
        
        if(a2bCurr!=null)
        {
            a2bNext=b2aSet.ceiling(new Pair(-1,a2bCurr.b+a2b));
        }
        
        Pair b2aNext=null;
        
        if(b2aCurr!=null)
        {
            b2aNext=a2bSet.ceiling(new Pair(-1,b2aCurr.b+b2a));
        }
        
        RetType val1=comp(a2bSet,b2aSet,a2bCurr,a2bNext,0);
        RetType val2=comp(b2aSet,a2bSet,b2aCurr,b2aNext,1);
        if(val1.val<val2.val)
        {
            //map.put(ids, val1);
            return val1;
        }
        else
        {
            //map.put(ids, val2);
            return val2;
        }
    }

    public RetType comp(TreeSet<Pair> a2bSet,TreeSet<Pair> b2aSet,Pair a2bCurr,Pair a2bNext,int k)
    {
        long min=Long.MAX_VALUE;
        int distInBw=a2b,distInBw2=b2a;
        RetType finVal=new RetType();
        finVal.ll=new LinkedList<>();
        finVal.val=min;
        if(k==1)
        {
            distInBw=b2a;
            distInBw2=a2b;
        }
        if(a2bCurr!=null)
        {
            //System.out.println(a2bCurr.a+" curr "+a2bCurr.b);
            if(a2bNext!=null)
            {
                //case1
                {
                    //System.out.println(a2bNext.a+" next "+a2bNext.b);
                    
                    a2bSet.remove(a2bCurr);
                    b2aSet.remove(a2bNext);
                    ids.remove(a2bCurr.a);
                    ids.remove(a2bNext.a);
                    
                    int diff=a2bNext.b-a2bCurr.b-distInBw;
                    
                    RetType val;
                    if(k==0)val=minCost(a2bSet,b2aSet);
                    else val=minCost(b2aSet,a2bSet);
                    
                    diff=(diff<=10)?diff:(10+2*(diff-10));
                    if(val.val==Long.MAX_VALUE)val.val-=diff;
                    if(min>val.val+diff)
                    {
                        min=diff+val.val;
                        finVal=new RetType();
                        finVal.ll=new LinkedList<>();
                        finVal.ll.addAll(val.ll);
                        finVal.ll.add(new Pair(a2bCurr.a,a2bNext.a));
                        finVal.val=min;
                    }
                    //min=Math.min(min,diff+val );
                    
                    a2bSet.add(a2bCurr);
                    b2aSet.add(a2bNext);
                    ids.add(a2bCurr.a);
                    ids.add(a2bNext.a);
                }
            }
            else
            {
                int end2=a2bCurr.b+distInBw;
                if(end2>ow)
                {
                    a2bSet.remove(a2bCurr);
                    ids.remove(a2bCurr.a);
                    RetType val;
                    if(k==0)val=minCost(a2bSet,b2aSet);
                    else val=minCost(b2aSet,a2bSet);
                    //min=Math.min(min,val );
                    if(min>val.val)
                    {
                        min=val.val;
                        finVal=new RetType();
                        finVal.ll=new LinkedList<>();
                        finVal.ll.addAll(val.ll);
                        finVal.ll.add(new Pair(a2bCurr.a,-1));
                        finVal.val=min;
                    }
                    a2bSet.add(a2bCurr);
                    ids.add(a2bCurr.a);
                }
                else
                {
                    int diff=ow-end2;
                    diff=(diff<=10)?diff:(10+2*(diff-10));
                    
                    a2bSet.remove(a2bCurr);
                    ids.remove(a2bCurr.a);
                    
                    RetType val;
                    if(k==0)val=minCost(a2bSet,b2aSet);
                    else val=minCost(b2aSet,a2bSet);
                    
                    if(val.val==Long.MAX_VALUE)val.val-=diff;
                    if(min>val.val+diff)
                    {
                        min=diff+val.val;
                        finVal=new RetType();
                        finVal.ll=new LinkedList<>();
                        finVal.ll.addAll(val.ll);
                        finVal.ll.add(new Pair(a2bCurr.a,-1));
                        finVal.val=min;
                    }
                    //min=Math.min(min,diff+val );
                    a2bSet.add(a2bCurr);
                    ids.add(a2bCurr.a);
                }
            }
        }
        return finVal;
    }
    
    static class RetType
    {
        LinkedList<Pair> ll;
        long val;
    }
    
    static class Pair
    {
    	int a;
    	int b;
    	public Pair(int a,int b)
    	{
    		this.a=a;
    		this.b=b;
    	}
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
    
    public static void main(String[] args) throws Exception {new RivigoQ1().run();}
    
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
