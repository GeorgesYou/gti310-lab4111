package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class ZigZag {	
	//http://www.developpez.net/forums/d278811/autres-langages/algorithmes/lecture-matrice-zigzag/
	public static List<int[][]> GetAC(List<int[][][]> blocs)
	{
		List<int[][]> ACs = new LinkedList<int[][]>();
		
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
			
			ACs.add(AC);
		}
		//Beuuuuh, tout transformer en (runlenght,value)... =(
		return ACs;
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
