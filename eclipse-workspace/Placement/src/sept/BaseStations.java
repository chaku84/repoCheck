package sept;

import java.io.*;
import java.util.*;

public class BaseStations {
	
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		int t=in.nextInt();
		while(t-->0)
		{
			int n=in.nextInt(),m=in.nextInt();
			int[][] a=new int[n][m];
			for(int i=0;i<n;i++)
			{
				for(int j=0;j<m;j++)
				{
					a[i][j]=in.nextInt();
				}
			}
		}
	}
	
	

}
