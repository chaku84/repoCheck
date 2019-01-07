package array;

import java.io.*;
import java.util.*;

/*
 * Array Intro:-
 * Introduction to Arrays
 * Arrays in C/C++
 	*int arr[6]={1,2,3,4,5,6};
 	*no compile error for index out of bound in c
 	*gives compile error in c++
 * Arrays in Java
   *all arrays are dynamically allocated on heap
   *direct super class of array is Object
   *array type implements Cloneable and java.io.Serializable interfaces
   *deep cloning for 1-d array and shallow cloning for multi.
 * Arrays in Python
 	*array(data type,value list) i.e,array('i',[1,2,3]);
 	*append(v) at end
 	*insert(i,v) at ith pos
 	*pop(i) at ith pos
 	*remove(v)
 	*index(v)
 	*reverse()
 */

public class ArrayRotation {
	public static void main(String[] args)
	{
		int[] a=new int[] {1,2,3,4,5,6,7,8};
		//q3(a,0,2,3,7);
		//q3b(a,3,8);
		//System.out.println(Arrays.toString(a));
		a=new int[] {1,2,3,4,5};
		int[][] q=new int[][] {{1,3},{3,0,2},{2,1},{3,1,4}};
		
		q14(a, q);
	}
	
	//moving d elements from back to front of the array
	//n=5,d=3,a=[1,2,3,4,5]=>out=[3,4,5,1,2]
	//method1-using temp arr
	//method2-rotate one by one d times
	//method3-juggling algo
	static void q1(int[] arr,int d,int n)
	{
		for(int i=0;i<gcd(d,n);i++)
		{
			int j=i,temp=arr[j];
			while(true)
			{
				int k=j+d;
				if(k>=n)k-=n;
				if(k==i)break;
				arr[j]=arr[k];
				j=k;
			}
			arr[j]=temp;
		}
	}
	
	static int gcd(int a,int b)
	{
		if(b%a==0)return a;
		return gcd(b%a,a);
	}
	
	//q2-reversal algo for array rotation
	//divide the array in two parts-a,b
	//rev(a),rev(b),rev(arr)
	//e.g-arr=[1,2,3,4,5],d=2,out=[4,5,1,2,3]
	//[3,2,1,5,4]->[4,5,1,2,3]
	
	//q3-block swap algo
	/*
	 * Initialize A = arr[0..d-1] and B = arr[d..n-1]
		1) Do following until size of A is equal to size of B
		
		  a)  If A is shorter, divide B into Bl and Br such that Br is of same 
		       length as A. Swap A and Br to change ABlBr into BrBlA. Now A
		       is at its final place, so recur on pieces of B.  
		
		   b)  If A is longer, divide A into Al and Ar such that Al is of same 
		       length as B Swap Al and B to change AlArB into BArAl. Now B
		       is at its final place, so recur on pieces of A.
		
		2)  Finally when A and B are of equal size, block swap them.
	 */
	//enda=d-1,stb=d
	static void q3(int[] arr,int sta,int enda,int stb,int endb)
	{
		//System.out.println(sta+" "+stb);
		int sza=enda-sta+1;
		int szb=endb-stb+1;
		//if(sza<=0 || szb<=0 || sza>arr.length || szb>arr.length)return;
		//System.out.println(sza+" "+szb);
		if(sza==szb)
		{
			swap(arr,sta,stb,szb);
			return;
		}
		else if(sza>szb)
		{
			int stal=sta,endal=sta+szb-1;
			int star=sta+szb,endar=enda;
			swap(arr,stal,stb,szb);
			stal=stb;
			endal=stal+szb-1;
			q3(arr,star,endar,stal,endal);
		}
		else
		{
			int stbl=stb,endbl=endb-sza;
			int stbr=endb-sza+1,endbr=endb;
			swap(arr,stbr,sta,sza);
			stbr=sta;
			endbr=stbr+sza-1;
			
			q3(arr,stbr,endbr,stbl,endbl);
		}
	}

	private static void swap(int[] arr, int sta, int stb,int sz) {
		// TODO Auto-generated method stub
		for(int i=0;i<sz;i++)
		{
			int tmp=arr[sta+i];
			arr[sta+i]=arr[stb+i];
			arr[stb+i]=tmp;
		}
	}
	//method2-iterative solution
	static void q3b(int[] arr,int d,int n)
	{
		if(d==0 ||d==n)return;
		int i=d;//asize
		int j=n-d;//bsize
		
		while(i!=j)
		{
			if(i<j)//a is smaller
			{
				swap(arr,d-i,d+j-i,i);
				j-=i;
			}
			else
			{
				swap(arr,d-i,d,j);
				i-=j;
			}
		}
		swap(arr,d-i,d,i);
	}
	
	//q4-cyclically rotate an array by one
	
	//q5-search an element in a sorted and rotated array
	//find pivot and then search element in two subarrays
	
	static int q5findPivot(int[] arr,int low,int high)
	{
		if(high<low)return -1;
		//if(high==low)return low;
		int mid=(low+high)/2;
		if(mid>0)
		{
			if(arr[mid-1]>arr[mid])return mid;
		}
		
		if(mid==0)return 0;
	    
		if(arr[low]>=arr[mid])
		{
			return q5findPivot(arr, low, mid-1);
		}
		else
		{
			return q5findPivot(arr, mid+1, high);
		}
	}
	
	//q6-Given a sorted and rotated array,
	//find if there is a pair with a given sum
	//also find count of pairs
	
	//sol-find pivot and using two pointers
	//left at pivot and right at pivot-1
	static void q6(int[] arr,int k)
	{
		int n=arr.length;
		int pivot=q5findPivot(arr, 0, arr.length-1);
		int left=pivot,right=(pivot-1+n)%n;
		int cnt=0;int sum=0;
		while(left!=right)
		{
			sum=arr[left]+arr[right];
			if(sum==k)cnt++;
			if(sum<k)
			{
				left=(left+1)%n;
			}
			else
			{
				right=(right-1+n)%n;
			}
		}
		System.out.println(cnt);
	}
	
	//q7-Find maximum value of Sum(i*arr[i]) with only 
	//rotations on given array allowed
	//R(j)-R(j-1)=arrsum-n*arr[n-j],R(j)-rotate j times
	//find sum for all rotation and max among them
	
	//q13-find a rotation with max hamming distance
	//hamm dis-count of diff elements at same index in two array
	
	//q14-Queries on Left and Right Circular shift on array
	static void q14(int[] a,int[][] q)
	{
		int n=a.length;
		int st=0,end=n-1;
		long[] sum=new long[n];
		sum[0]=a[0];
		for(int i=1;i<n;i++)
		{
			sum[i]=sum[i-1]+a[i];
		}
		for(int i=0;i<q.length;i++)
		{
			if(q[i][0]==1)//left rot
			{
				int x=q[i][1];
				st=(end-x+1+(x/n)*n)%n;
				end=(st-1+n)%n;
			}
			else if(q[i][0]==2)//right rot
			{
				int y=q[i][1];
				end=(st+y-1+n)%n;
				st=(st+1)%n;
			}
			else
			{
				int l=q[i][1],r=q[i][2];
				l=(l+st)%n;
				r=(r+st)%n;
				long curr=0;
				if(l<=r)
				{
					curr=sum[r]-((l>0)?sum[l-1]:0);
				}
				else
				{
					curr=sum[r]+sum[n-1]-((l>0)?sum[l-1]:0);
				}
				System.out.println(curr);
			}
		}
	}
	
	//
}