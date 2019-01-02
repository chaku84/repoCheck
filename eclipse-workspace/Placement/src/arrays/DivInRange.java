package arrays;

import java.io.*;
import java.util.*;

public class DivInRange {
	//Count elements which divide all numbers in range L-R
	
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int[] a=new int[n];
		Random r=new Random();
		for(int i=0;i<n;i++)
		{
			a[i]=r.nextInt(10)+1;
		}
		//System.out.println(Arrays.toString(a));
		int q=in.nextInt();
		segt=new Pair[3*100000];
		constSt(a, 0, n-1, 0);
		while(q-->0)
		{
			int ind=r.nextInt(n);
			a[ind]=((2-1)^1)+1;
		    update(0, n-1, ind, a, 0);
			for(int le=0;le<n;le++)
			{
				for(int ri=le;ri<n;ri++)
				{
					int ans1=brute(a, le, ri);
					//System.out.println(ans1);
					//System.out.println(Arrays.toString(a));
					Pair val=query(0, n-1, le, ri, 0);
					int ans2=val==null?0:val.minCnt;
					//System.out.println("lr"+le+" "+ri);
					//System.out.println(ans1+" "+ans2);
					if(ans1!=ans2)
					{
						System.out.println(q);
						break;
					}
				}
			}
			
		}
	}
	
	static int gcd(int a,int b)
	{
		if(a==0)return b;
		return gcd(b%a,a);
	}
	
	static int brute(int[] a,int l,int r)
	{
		int g=a[l];
		int min=a[l];
		for(int i=l+1;i<=r;i++)
		{
			g=gcd(g,a[i]);
			min=Math.min(min,a[i]);
		}
		int cnt=0;
		for(int i=l;i<=r;i++)
		{
			if(min==a[i])cnt++;
		}
		if(min==g)return cnt;
		return 0;
	}
	
	static Pair[] segt;
	
	static Pair constSt(int[] a,int st,int end,int si)
	{
		if(st==end)
		{
			segt[si]=new Pair(a[st],a[st],1);
			return segt[si];
		}
		int mid=(st+end)>>1;
		Pair lans=constSt(a,st,mid,2*si+1);
		Pair rans=constSt(a,mid+1, end, 2*si+2);
		
		if(lans!=null && rans!=null)
		{
			segt[si]=new Pair(gcd(lans.gcd,rans.gcd),Math.min(lans.min, rans.min),
					(lans.min<rans.min?lans.minCnt:(lans.min>rans.min?rans.minCnt:(lans.minCnt+rans.minCnt))));
		}
		else if(lans!=null)
		{
			segt[si]=new Pair(lans.gcd,lans.min,lans.minCnt);
		}
		else if(rans!=null)
		{
			segt[si]=new Pair(rans.gcd,rans.min,rans.minCnt);
		}
		else 
		{
			//System.out.println("null");
		}
		//System.out.println(segt[si].gcd);
		return segt[si];
	}
	
	static Pair query(int st,int end,int qs,int qe,int si)
	{
		if(qs>end || qe<st)return new Pair(0,0,0);
		if(qs<=st && end<=qe)
		{
			return new Pair(segt[si].gcd,segt[si].min,segt[si].gcd!=segt[si].min?0:segt[si].minCnt);
		}
		int mid=(st+end)>>1;
		Pair lans=query(st, mid, qs, qe, 2*si+1);
		Pair rans=query(mid+1,end, qs, qe, 2*si+2);
		
		
		Pair tmp=null;
		if(lans.gcd!=0 && rans.gcd!=0)
		{
			tmp=new Pair(gcd(lans.gcd,rans.gcd),Math.min(lans.min, rans.min),
					(lans.min<rans.min?lans.minCnt:(lans.min>rans.min?rans.minCnt:(lans.minCnt+rans.minCnt))));
		}
		else if(lans.gcd!=0)
		{
			tmp=lans;
		}
		else if(rans.gcd!=0)
		{
			tmp=rans;
		}
		else return null;
		if(tmp.gcd!=tmp.min)
		{
			tmp.minCnt=0;
		}
		return tmp;
	}
	
    static void update(int st,int end,int ind,int[] a,int si)
	{
		if(st>ind || end<ind)return;
		if(st==end)
		{
			segt[si]=new Pair(a[ind],a[ind],1);
			return;
		}
		int mid=(st+end)/2;
		update(st,mid,ind,a,2*si+1);
		update(mid+1, end,ind,a, 2*si+2);
		Pair lans=segt[2*si+1];
		Pair rans=segt[2*si+2];
		if(lans!=null && rans!=null)
		{
			segt[si]=new Pair(gcd(lans.gcd,rans.gcd),Math.min(lans.min, rans.min),
					(lans.min<rans.min?lans.minCnt:(lans.min>rans.min?rans.minCnt:(lans.minCnt+rans.minCnt))));
		}
		else if(lans!=null)
		{
			segt[si]=new Pair(lans.gcd,lans.min,lans.minCnt);
		}
		else if(rans!=null)
		{
			segt[si]=new Pair(rans.gcd,rans.min,rans.minCnt);
		}
	}
	
	static class Pair
	{
		int gcd;
		int min;
		int minCnt;
		public Pair(int gcd,int min,int minCnt)
		{
			this.gcd=gcd;
			this.min=min;
			this.minCnt=minCnt;
		}
	}

}
