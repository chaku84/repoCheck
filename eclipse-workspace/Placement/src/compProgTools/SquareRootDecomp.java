package compProgTools;

import java.util.Arrays;
import java.util.HashMap;

public class SquareRootDecomp {
	/*
	    int t=ni();
		while(t-->0)
		{
			int n=ni();
			int q=ni();
			int[] a=na(n);
			int[] b=na(n);
			int root=(int)Math.sqrt(n);
			HashMap<Integer,Integer>[] map=new HashMap[root+4];
			for(int i=0;i<root+4;i++)
			{
				map[i]=new HashMap<Integer,Integer>();
			}
			for(int i=0;i<n;i++)
			{
				if(map[i/root].containsKey(b[i]))
				{
					map[i/root].put(b[i],map[i/root].get(b[i])+1);
				}
				else
				{
					map[i/root].put(b[i], 1);
				}
			}
			int[] cover=new int[root+4];
			Arrays.fill(cover, -1);
			int cnt=0;
			for(int i=0;i<n;i++)
			{
				if(a[i]==b[i])cnt++;
			}
			
			int ans=cnt;
			while(q-->0)
			{
				int x=ni(),y=ni(),z=ni();
				int l=x^ans,r=y^ans,c=z^ans;
				l--;r--;
				
				if(cover[l/root] != -1)
				{
					for(int i = l/root*root;i < l/root*root+root && i < n;i++)
					{
						a[i] = cover[l/root];
					}
					cover[l/root] = -1;
				}
				if(cover[r/root] != -1)
				{
					for(int i = r/root*root;i < r/root*root+root && i < n;i++)
					{
						a[i] = cover[r/root];
					}
					cover[r/root] = -1;
				}
				if(l/root<r/root)
				{
					for(int i=l;i<l/root*root+root && i<n;i++)
					{
						if(a[i]==b[i])cnt--;
					}
					for(int i=r/root*root;i<=r;i++)
					{
						if(a[i]==b[i])cnt--;
					}
					for(int i=l/root+1;i<r/root;i++)
					{
						if(cover[i]!=-1)
						{
							if(map[i].containsKey(cover[i]))cnt-=map[i].get(cover[i]);
						}
						else
						{
							for(int j=i*root;j<i*root+root && j<n;j++)
							{
								if(a[j]==b[j])cnt--;
							}
						}
					}
					
					
					for(int i=l;i<l/root*root+root && i<n;i++)
					{
						a[i]=c;
					}
					for(int i=r/root*root;i<=r;i++)
					{
						a[i]=c;
					}
					for(int i=l/root+1;i<r/root;i++)
					{
						cover[i]=c;
					}
					
					
					for(int i=l;i<l/root*root+root && i<n;i++)
					{
						if(a[i]==b[i])cnt++;
					}
					for(int i=r/root*root;i<=r;i++)
					{
						if(a[i]==b[i])cnt++;
					}
					for(int i=l/root+1;i<r/root;i++)
					{
						if(cover[i]!=-1)
						{
							if(map[i].containsKey(cover[i]))cnt+=map[i].get(cover[i]);
						}
						else
						{
							for(int j=i*root;j<i*root+root && j<n;j++)
							{
								if(a[j]==b[j])cnt++;
							}
						}
					}
				}
				else
				{
					for(int i=l;i<=r;i++)
					{
						if(a[i]==b[i])cnt--;
					}
					for(int i=l;i<=r;i++)
					{
						a[i]=c;
						//cover[i/root]=c;
					}
					
					for(int i=l;i<=r;i++)
					{
						if(a[i]==b[i])cnt++;
					}
				}
				
				ans=cnt;
				out.println(ans);
			}
		}
	 */

}
