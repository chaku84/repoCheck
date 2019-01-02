package samsung;

import java.util.*;
import java.io.*;

public class RockClimbing {
	static int[][] comb=new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
	static int row,col;
	static int[][] mat;
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		String[] st=new String[] {"11110000","00030111","11100100","00000010","21111111"};
		mat=new int[5][8];
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<8;j++)
			{
				mat[i][j]=st[i].charAt(j)-'0';
			}
		}
		vis=new int[5][8];
		row=5;col=8;
		System.out.println(checkForLevel(1, 3));
	}
	static int[][] vis;
	static int checkForLevel(int destx,int desty)
	{
		int level=1;
		while(true)
		{
			for(int i=0;i<row;i++)Arrays.fill(vis[i], 0);
			vis[destx][desty]=1;
			if(checkForRow(destx,desty,level)==1)
			{
				return level;
			}
			level++;
		}
	}
	
	private static int checkForRow(int destx, int desty, int level) 
	{
		// TODO Auto-generated method stub
		if(destx==row-1)return 1;
		for(int k=0;k<(2+level*2);k++)
		{
			int tempx,tempy;
			if(k<4)
			{
				tempx=destx+comb[k][0];
				tempy=desty+comb[k][1];
			}
			else
			{
				tempy=desty;
				tempx=destx+(k/2)*comb[k%2][0];
			}
			if(tempx>=0 && tempy>=0 && tempx<row && tempy<col)
			{
				if(vis[tempx][tempy]==0 && mat[tempx][tempy]==1)
				{
					vis[tempx][tempy]=1;
					if(checkForRow(tempx,tempy,level)==1)return 1;
				}
			}
		}
		return 0;
	}
}
