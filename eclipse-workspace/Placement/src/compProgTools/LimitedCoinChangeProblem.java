package compProgTools;

import java.util.Arrays;
import java.util.Random;

public class LimitedCoinChangeProblem {
	
	public static void main(String[] args)
	{
		Random rnd=new Random();
		for(int t=1;t<=100;t++)
		{
			int price=rnd.nextInt(200)+400;
			int p=rnd.nextInt(price/4)+1;
			int q=rnd.nextInt(price/4)+1;
			int r=rnd.nextInt(price/4)+1;
			int s=rnd.nextInt(price/4)+1;
			
			/*
			int price=100000;
			int p=200;
			int q=200;
			int r=3000;
			int s=4000;
			*/
			int[] ans=brute(price,p,q,r,s);
			
			int[] ans2=eff(price,p,q,r,s);
			int f=0;
			
			for(int i=0;i<4;i++)
			{
				if(ans[i]!=ans2[i])
				{
					f=1;break;
				}
			}
			
			//System.out.println(Arrays.toString(ans2));
			if(f==1)
			{
				System.out.println(t);
				System.out.println(price+" "+p+" "+q+" "+r+" "+s);
				System.out.println(Arrays.toString(ans));
				System.out.println(Arrays.toString(ans2));
				break;
			}
		}
	}
	
	private static int[] eff(int price, int p, int q, int r, int s) 
    {
    	int[][] dp=new int[price+1][4];
    	int[] tmp=new int[] {1,5,10,25};
    	int[][] prev=new int[price+1][4];
    	int[][] curr=new int[price+1][4];
    	int[] cnt=new int[] {p,q,r,s};
    	int min=Integer.MIN_VALUE;
    	for(int i=1;i<=price;i++)
    	{
    		for(int j=0;j<4;j++)
    		{
    			dp[i][j]=min;
    		}
    	}
    	for(int i=1;i<=p;i++)
    	{
    		dp[i][0]=i;
    		prev[i][0]=i;
    	}
    	dp[0][0]=0;
    	for(int j=1;j<4;j++)
		{
    		for(int i=1;i<=price;i++)
        	{
    			if(i>=tmp[j])
    			{
    				int a=dp[i-tmp[j]][j-1]+1,b=dp[i][j-1],c=dp[i-tmp[j]][j]+1;
    				
    				if(a>=b && a>=c && prev[i-tmp[j]][j]+1<=cnt[j])
    				{
    					curr[i]=Arrays.copyOf(prev[i-tmp[j]], 4);
    					curr[i][j]++;	
    					dp[i][j]=a;
    				}
    				else if(c>=a && c>=b && curr[i-tmp[j]][j]+1<=cnt[j])
    				{
    					curr[i]=Arrays.copyOf(curr[i-tmp[j]], 4);
    					curr[i][j]++;
    					dp[i][j]=c;
    				}
    				else
    				{
    					dp[i][j]=b;
    					curr[i]=Arrays.copyOf(prev[i], 4);
    				}
    			}
    			else 
    			{
    				curr[i]=Arrays.copyOf(prev[i], 4);
    				dp[i][j]=dp[i][j-1];
    			}
        	}
    		for(int i=1;i<=price;i++)
    		{
    			for(int k=0;k<=j;k++)
    			{
    				prev[i][k]=curr[i][k];
    			}
    		}
		}
    	if(prev[price][0]+prev[price][1]*5+prev[price][2]*10+prev[price][3]*25!=price)return new int[4];
		return prev[price];
	}


	private static int[] brute(int price, int p, int q, int r, int s) 
	{
		int max=0;
		int[] ans=new int[4];
		for(int i=p;i>=0;i--)
		{
			for(int j=q;j>=0;j--)
			{
				for(int k=r;k>=0;k--)
				{
					for(int l=s;l>=0;l--)
					{
						if(i+j*5+k*10+l*25==price)
						{
							if(max<(i+j+k+l))
							{
								ans=new int[] {i,j,k,l};
								max=(i+j+k+l);
							}
						}
					}
				}
			}
		}
		return ans;
	}

}
