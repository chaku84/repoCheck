package july23to31;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class CN14AUGQ2 {
	//No of triplets in an array having product as x
	public static void main(String[] args)
	{
		Random r=new Random();
		for(int t=1;t<=100;t++)
		{
			int n=1000,x=r.nextInt(n)+1;
			//System.out.println(x);
			int[] arr=new int[n];
			for(int i=0;i<n;i++)
			{
				arr[i]=r.nextInt(x)+1;
			}
			long ans1=brFrc(arr,n,x);
			long ans2=effAlgo(arr,n,x);
			if(ans1!=ans2)
			{
				System.out.println(t);
				break;
			}
		}
	}
	
	private static long effAlgo(int[] arr, int n, int x) {
		// TODO Auto-generated method stub
		//System.out.println(Arrays.toString(arr));
		HashMap<Integer,Integer> cnt=new HashMap<>();
		for(int i=0;i<n;i++)
		{
			if(cnt.containsKey(arr[i]))
			{
				cnt.put(arr[i], cnt.get(arr[i])+1);
			}
			else cnt.put(arr[i], 1);
		}
		ArrayList<Integer> factors=new ArrayList<>();
		for(int i=1;i<=Math.sqrt(x);i++)
		{
			if(x%i==0)
			{
				factors.add(i);
				if(x%(x/i)==0 && i!=x/i)factors.add(x/i);
			}
		}
		Collections.sort(factors);
		HashMap<Integer,ArrayList<Integer>> map=new HashMap<>();
		int sz=factors.size();
		ArrayList<Integer> tmpl=new ArrayList<>();
		tmpl.add(1);
		map.put(1,tmpl);
		for(int i=1;i<sz;i++)
		{
			int curr=factors.get(i);
			//System.out.println(curr);
			ArrayList<Integer> l=new ArrayList<>();
			for(int j=0;j<=i;j++)
			{
				if(curr%factors.get(j)==0)
				{
					l.add(factors.get(j));
				}
			}
			//System.out.println(l.toString());
			map.put(curr, l);
		}
		long ans=0;
		for(int i=0;i<n;i++)
		{
			if(x%arr[i]==0)
			{
				int curr=x/arr[i];
				//System.out.println("arr["+i+"]:"+arr[i]);
				//System.out.println(curr);
				for(int j:map.get(curr))
				{
					if(j*j>curr)break;
					//System.out.print(j+" j: ");
					if(cnt.containsKey(j) && cnt.containsKey(curr/j))
					{
						//System.out.println(cnt.get(j)+" "+cnt.get(curr/j));
						int cnt1=cnt.get(j),cnt2=cnt.get(curr/j);
						if(j==arr[i])cnt1--;
						if(curr/j==arr[i])cnt2--;
						if(j!=curr/j)
						{
							ans+=(long)cnt1*cnt2;
						}
						else
						{
							ans+=((long)cnt1*(cnt1-1))/2;
						}
						//System.out.println("ans:"+ans);
						//ans+=(long)cnt.get(j)*cnt.get(curr/j);
					}
				}
				//System.out.println();
			}
		}
		return ans/3;
	}

	private static int brFrc(int[] arr, int n,int x) {
		// TODO Auto-generated method stub
		int cnt=0;
		for(int i=0;i<n-2;i++)
		{
			for(int j=i+1;j<n-1;j++)
			{
				for(int k=j+1;k<n;k++)
				{
					if(arr[i]*arr[j]*arr[k]==x)cnt++;
				}
			}
		}
		return cnt;
	}

}
