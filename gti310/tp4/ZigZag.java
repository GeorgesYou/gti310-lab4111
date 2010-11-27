package gti310.tp4;

import java.util.List;

public class ZigZag {	
	//http://www.developpez.net/forums/d278811/autres-langages/algorithmes/lecture-matrice-zigzag/
	public static int[][][] GetAC(List<int[][][]> blocs)
	{
		int[][] ACs = new int[Main.COLOR_SPACE_SIZE][];
		int[][][] ACsToWrite = new int[Main.COLOR_SPACE_SIZE][][];
		        
		for (int[][][] bloc : blocs)
		{
			int[][] AC = new int[Main.COLOR_SPACE_SIZE][];
			
			boolean up=false;
			int i=0,j=0,k=0;
			int maxI=8,maxJ=8;
			
			while (i <= maxI && j <= maxJ)
			{
				if (i!=0 && j!=0)
				AC[Main.Y][k]=bloc[Main.Y][i][j];
				AC[Main.U][k]=bloc[Main.U][i][j];
				AC[Main.V][k]=bloc[Main.V][i][j];
				k++;
				
				if (i == 0 || i == maxI) 
				{
			        if (j == maxJ)
			        {
			            j--;
			            i++;
			        }
			        j++;
			        
			        AC[Main.Y][k]=bloc[Main.Y][i][j];
					AC[Main.U][k]=bloc[Main.U][i][j];
					AC[Main.V][k]=bloc[Main.V][i][j];
					k++;
				}
			    else
			    {
			        if (j == 0 || j == maxJ)
			        {
			            if (i == maxI)
			            {
			                i--;
			                j++;
			            }
			            i++;
	
			            AC[Main.Y][k]=bloc[Main.Y][i][j];
						AC[Main.U][k]=bloc[Main.U][i][j];
						AC[Main.V][k]=bloc[Main.V][i][j];
						k++;
			        }
			    }
			        
			    if (i == 0 || j == maxJ) 
			    	up = false;
			    if (j == 0 || i == maxI) 
			    	up = true;
			    
			    if (up) 
			    {
			        i--;
			        j++;
			    }
			    else
			    {
			        i++;
			        j--;
			    }
			}
			
			for (int v=0;v<8;v++)
			{
				ACs[Main.Y][blocs.indexOf(bloc)*8+v]=AC[Main.Y][v];
				ACs[Main.U][blocs.indexOf(bloc)*8+v]=AC[Main.U][v];
				ACs[Main.V][blocs.indexOf(bloc)*8+v]=AC[Main.V][v];
			}
		}
		
		int zeros=0;
		int k=0;
		for (int val : ACs[Main.Y])
		{
			if (val==0)
				zeros++;
			else
			{
				ACsToWrite[Main.Y][k][0]=zeros;
				ACsToWrite[Main.Y][k][1]=val;
				zeros=0;
				k++;
			}
		}
		ACsToWrite[Main.Y][k][0]=0;
		ACsToWrite[Main.Y][k][1]=0;
		
		zeros=0;
		k=0;
		for (int val : ACs[Main.U])
		{
			if (val==0)
				zeros++;
			else
			{
				ACsToWrite[Main.U][k][0]=zeros;
				ACsToWrite[Main.U][k][1]=val;
				zeros=0;
				k++;
			}
		}
		ACsToWrite[Main.U][k][0]=0;
		ACsToWrite[Main.U][k][1]=0;
		
		zeros=0;
		k=0;
		for (int val : ACs[Main.V])
		{
			if (val==0)
				zeros++;
			else
			{
				ACsToWrite[Main.V][k][0]=zeros;
				ACsToWrite[Main.V][k][1]=val;
				zeros=0;
				k++;
			}
		}
		ACsToWrite[Main.V][k][0]=0;
		ACsToWrite[Main.V][k][1]=0;
		
		return ACsToWrite;
	}
	
	public static int[][] GetDC(List<int[][][]> blocs)
	{
		int[][] DC = new int[Main.COLOR_SPACE_SIZE][];
		int k=0;
		
		for (int[][][] bloc : blocs)
		{
			DC[Main.Y][k]=bloc[Main.Y][0][0];
			DC[Main.U][k]=bloc[Main.U][0][0];
			DC[Main.V][k]=bloc[Main.V][0][0];
			k++;
		}
		
		for (int i=1;i<DC[Main.Y].length;i++)
		{
			DC[Main.Y][i]=DC[Main.Y][i]-DC[Main.Y][i-1];
			DC[Main.U][i]=DC[Main.U][i]-DC[Main.U][i-1];
			DC[Main.V][i]=DC[Main.V][i]-DC[Main.V][i-1];
		}
		
		return DC;
	}

}
