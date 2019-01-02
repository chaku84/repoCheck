package samsung;

import java.io.*;
import java.util.*;

public class Fishery {
	
	static int max=3;
	static int[] gate;
	static int[] fisherman;
	static int[] fishspot;
	static int[] vis;
	static int answer;
	static int N;
	
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		int t=in.nextInt();
		while(t-->0)
		{
			int n=in.nextInt();
			N=n;
			fisherman=new int[max];
			gate=new int[max];
			for(int i=0;i<max;i++)
			{
				gate[i]=in.nextInt();
				fisherman[i]=in.nextInt();
			}
			fishspot=new int[61];
			vis=new int[max];
			answer=999999;
			for(int i=0;i<max;i++)
			{
				solve(i,0,1);
				vis[i]=0;
				resetSpot(i);
			}
			System.out.println(answer);
		}
	}

	private static void solve(int index, int sum, int count)
	{
		// TODO Auto-generated method stub
		int[] pos=new int[3];
		vis[index]=1;
		
		if(answer<sum)return;
		
		int npos=calc(index,pos);
		
		sum+=pos[2];
		
		if(count==max)
		{
			answer=Math.min(answer, sum);
			return;
		}
		
		if(npos==1)
		{
			for(int i=0;i<max;i++)
			{
				if(vis[i]==0)
				{
					solve(i,sum,count+1);
					vis[i]=0;
					resetSpot(i);
				}
			}
		}
		else if(npos==2)
		{
			fishspot[pos[0]]=index+1;
			for(int i=0;i<max;i++)
			{
				if(vis[i]==0)
				{
					solve(i,sum,count+1);
					vis[i]=0;
					resetSpot(i);
				}
			}
			
			fishspot[pos[0]]=0;
			fishspot[pos[1]]=index+1;
			for(int i=0;i<max;i++)
			{
				if(vis[i]==0)
				{
					solve(i,sum,count+1);
					vis[i]=0;
					resetSpot(i);
				}
			}
			fishspot[pos[1]]=0;
		}
	}

	private static int calc(int index, int[] pos) {
		// TODO Auto-generated method stub
		int leftMin=999999,rightMin=999999,left,right,npos=0,sum=0;
		left=right=gate[index];
		Arrays.fill(pos, 0);
		for(int i=1;i<fisherman[index];i++)
		{
			if(fishspot[gate[index]]==0)
			{
				sum++;
				fishspot[gate[index]]=index+1;
			}
			else
			{
				leftMin=999999;rightMin=999999;
				
				while(left>0 && fishspot[left]>0)left--;
				while(right<=N && fishspot[right]>0)right++;
				
				if(left>=0 && fishspot[left]==0)leftMin=gate[index]-left+1;
				if(right<=N && fishspot[right]==0)rightMin=right-gate[index]+1;
				
				if(leftMin==rightMin)
				{
					if((fisherman[index]-i-1)>1)
					{
						fishspot[left]=fishspot[right]=index+1;
						sum+=2*leftMin;
						i++;
						if(i==fisherman[index])
						{
							npos=1;
							pos[2]=sum;
							return npos;
						}
					}
					else
					{
						sum+=leftMin;
						fishspot[left]=index+1;
					}
				}
				else if(leftMin<rightMin)
				{
					sum+=leftMin;
					fishspot[left]=index+1;
				}
				else
				{
					sum+=rightMin;
					fishspot[right]=index+1;
				}
			}
		}
		
		leftMin=999999;rightMin=999999;
		
		while(left>0 && fishspot[left]>0)left--;
		while(right<=N && fishspot[right]>0)right++;
		
		if(left>=0 && fishspot[left]==0)leftMin=gate[index]-left+1;
		if(right<=N && fishspot[right]==0)rightMin=right-gate[index]+1;
		
		if(leftMin<rightMin)
		{
			npos=1;
			pos[2]=sum+leftMin;
			fishspot[left]=index+1;
		}
		else if(leftMin>rightMin)
		{
			npos=1;
			pos[2]=sum+rightMin;
			fishspot[right]=index+1;
		}
		else
		{
			npos=2;
			pos[2]=sum+leftMin;
			pos[0]=left;
			pos[1]=right;
		}
		
		return npos;
	}
	
	static void resetSpot(int index)
	{
		for(int i=0;i<=N;i++)
		{
			if(fishspot[i]==index+1)fishspot[i]=0;
		}
	}

}
