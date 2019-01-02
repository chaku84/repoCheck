package uber;

import java.io.*;
import java.util.*;

public class BalancedDiet 
{
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		int t=in.nextInt();
		Random r=new Random();
		int cnt=0;
		while(t-->0)
		{
			int n=20;
			int target=r.nextInt(10)+1;
			int[] protein=new int[n];
			int[] fat=new int[n];
			for(int i=0;i<n;i++)
			{
				protein[i]=r.nextInt(10)+1;
				fat[i]=r.nextInt(10)+1;
			}
			boolean ans=brute(protein,fat,n,target);
			boolean ans2=eff(protein,fat,n,target);
			if(ans)cnt++;
			if(ans!=ans2)
			{
				System.out.println(t);
				System.out.println(cnt);
				break;
			}
		}
		System.out.println(cnt);
	}

	private static boolean eff(int[] protein, int[] fat, int n, int target) {
		// TODO Auto-generated method stub
		dp=new int[501][501][51];
		for(int i=0;i<501;i++)
			for(int j=0;j<501;j++)
				Arrays.fill(dp[i][j], -1);
		
		return comp(0,0,protein,fat,0,target);
	}
	
	static int[][][] dp;
	
	static boolean comp(int p,int f,int[] protein,int[] fat,int i,int r)
	{
		if(f!=0 && p/f==r && p%f==0)return true;
		int n=protein.length;
		if(i==n-1)
		{
			p+=protein[i];
			f+=fat[i];
			if(f!=0 && p/f==r && p%f==0)return true;
			return false;
		}
		if(dp[p][f][i]!=-1)return dp[p][f][i]==1?true:false;
		boolean ans=comp(p+protein[i],f+fat[i],protein,fat,i+1,r) || comp(p,f,protein,fat,i+1,r);
		if(ans)dp[p][f][i]=1;
		else dp[p][f][i]=0;
		
		return ans;

	}

	private static boolean brute(int[] protein, int[] fat, int n, int target) 
	{
		// TODO Auto-generated method stub
		
		for(int i=1;i<Math.pow(2, n);i++)
		{
			int curr=i,ind=n-1;
			int s1=0,s2=0;
			while(curr!=0)
			{
				if(curr%2==1)
				{
					s1+=protein[ind];
					s2+=fat[ind];
				}
				curr/=2;
				ind--;
			}
			if(s1/s2==target && s1%s2==0)
			{
				return true;
			}
		}
		return false;
	}


}
