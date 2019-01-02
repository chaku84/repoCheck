package compProgTools;

import java.util.Arrays;

public class Permut {
	void permute(int[] arr, int l, int r)
    {
       int i;
       if (l == r)
       {
    	   //System.out.println(Arrays.toString(arr));
    	   //int lis=lis(arr,arr.length),lds=lds(arr,arr.length);
    	   int lis=0,lds=0;
    	   //System.out.println(lis+" "+lds);
    	   if(ans>(lis==0?1:lis)+(lds==0?1:lds))
    	   {
    		   ans=(lis==0?1:lis)+(lds==0?1:lds);
    		   finArr=Arrays.copyOf(arr, arr.length);
    	   }
       }
       else
       {
           for (i = l; i <= r; i++)
           {
              swap(arr,l,i);
              permute(arr, l+1, r);
              swap(arr,l,i);
           }
       }
    }
	
	static void comb(int[] data,int ind,int n,int r,int[] a,int i)
	{
		if(ind==r)
		{
			System.out.println(Arrays.toString(data));
			return;
		}
		if(i>=n)return;
		
		//included
		data[ind]=a[i];
		comb(data, ind+1, n, r, a, i+1);
		while(i<n-1 && a[i]==a[i+1])i++;
		//excluded
		comb(data, ind, n, r, a, i+1);
	}

	 void swap(int[] arr,int i1,int i2)
	 {
		 int tmp=arr[i1];
	   	arr[i1]=arr[i2];
	   	arr[i2]=tmp;
	 }
	 static int ans;
	    
	 static int[] finArr;
	 
	 public static void main(String[] args)
	 {
		 int[] a= {1,1,3,4,5};
		 int[] data=new int[3];
		 comb(data,0,5,3,a,0);
	 }
}
