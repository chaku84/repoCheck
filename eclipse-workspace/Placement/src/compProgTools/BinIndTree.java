package compProgTools;

public class BinIndTree {
    static int bit[];
    //1 based indexing
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
    
}
