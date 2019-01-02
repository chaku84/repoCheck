package compProgTools;

public class SegmentTreeRMQ {
	
	int segt[];
	
	int constSt(int[] a,int st,int end,int si)
	{
		if(st==end)
		{
			segt[si]=a[end];
			return segt[si];
		}
		int mid=(st+end)/2;
		
		int val1=constSt(a,st,mid,2*si+1);
		int val2=constSt(a,mid+1,end,2*si+2);
		
		segt[si]=Math.min(val1,val2);
		
		return segt[si];
	}
	
	int query(int st,int end,int qs,int qe,int ind)
	{
		if(qs<=st && end<=qe)
		{
			return segt[ind];
		}
		if(end<qs || qe<st)
		{
			return Integer.MAX_VALUE;
		}
		int mid=(st+end)/2;
		return Math.min(query(st, mid, qs, qe, 2*ind+1),query(mid+1, end, qs, qe, 2*ind+2));
	}
}
