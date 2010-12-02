package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class ZigZag {	
	//http://www.developpez.net/forums/d278811/autres-langages/algorithmes/lecture-matrice-zigzag/
	
	public static int[][][] GetAC(List<int[][][]> blocs)
	{
		int[][] ACs = new int[Main.COLOR_SPACE_SIZE][blocs.size()*blocs.get(0)[0].length*blocs.get(0)[0][0].length];
		int[][][] ACsToWrite = new int[Main.COLOR_SPACE_SIZE][blocs.size()*63+1][2];

		for (int[][][] bloc : blocs)
		{
			int[][] AC = new int[Main.COLOR_SPACE_SIZE][63];
			
			int[] ZigZagOrder = { 
					0,1,5,6,14,15,27,28,
					2,4,7,13,16,26,29,42,
					3,8,12,17,25,30,41,43,
					9,11,18,24,31,40,44,53,
					10,19,23,32,39,45,52,54,
					20,22,33,38,46,51,55,60,
					21,34,37,47,50,56,59,61,
					35,36,48,49,57,58,62,63};
			
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
					if (!(i==0 && j==0))
					{
						AC[Main.Y][ZigZagOrder[i*8+j]-1]=bloc[Main.Y][i][j];
						AC[Main.U][ZigZagOrder[i*8+j]-1]=bloc[Main.U][i][j];
						AC[Main.V][ZigZagOrder[i*8+j]-1]=bloc[Main.V][i][j];
					}
			
//			boolean up=false;
//			int i=0,j=0,k=0;
//			int maxI=7,maxJ=7;
//			
//			while (i <= maxI && j <= maxJ)
//			{
//				if (!(i==0 && j==0))
//				{
////					System.out.println(bloc[Main.Y][i][j]);
//					AC[Main.Y][k]=bloc[Main.Y][i][j];
//					AC[Main.U][k]=bloc[Main.U][i][j];
//					AC[Main.V][k]=bloc[Main.V][i][j];
//					k++;
//				}
//				
//				if (i == 0 || i == maxI) 
//				{
//			        if (j == maxJ)
//			        {
//			            j--;
//			            i++;
//			        }
//			        j++;
////			        System.out.println(bloc[Main.Y][i][j]);
//			        AC[Main.Y][k]=bloc[Main.Y][i][j];
//					AC[Main.U][k]=bloc[Main.U][i][j];
//					AC[Main.V][k]=bloc[Main.V][i][j];
//					k++;
//				}
//			    else
//			    {
//			        if (j == 0 || j == maxJ)
//			        {
//			            if (i == maxI)
//			            {
//			                i--;
//			                j++;
//			            }
//			            i++;
////			            System.out.println(bloc[Main.Y][i][j]);
//			            AC[Main.Y][k]=bloc[Main.Y][i][j];
//						AC[Main.U][k]=bloc[Main.U][i][j];
//						AC[Main.V][k]=bloc[Main.V][i][j];
//						k++;
//			        }
//			    }
//			        
//			    if (i == 0 || j == maxJ) 
//			    	up = false;
//			    if (j == 0 || i == maxI) 
//			    	up = true;
//			    
//			    if (up) 
//			    {
//			        i--;
//			        j++;
//			    }
//			    else
//			    {
//			        i++;
//			        j--;
//			    }
//			}
			
			for (int v=0;v<63;v++)
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
		int[][] DC = new int[Main.COLOR_SPACE_SIZE][blocs.size()];
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
	
	public static List<int[][][]> CreateBlocs(int[][] DCs, int[][][] ACs)
	{
		List<int[][][]> blocs = new LinkedList<int[][][]>();
		
		//DCs
		for (int i=1;i<DCs[Main.Y].length;i++)
		{
			DCs[Main.Y][i]=DCs[Main.Y][i]+DCs[Main.Y][i-1];
			DCs[Main.U][i]=DCs[Main.U][i]+DCs[Main.U][i-1];
			DCs[Main.V][i]=DCs[Main.V][i]+DCs[Main.V][i-1];
		}
		
		for (int i=0;i<DCs[Main.Y].length;i++)
		{
			int[][][] bloc = new int[Main.COLOR_SPACE_SIZE][8][8];
			bloc[Main.Y][0][0]=DCs[Main.Y][i];
			bloc[Main.U][0][0]=DCs[Main.U][i];
			bloc[Main.V][0][0]=DCs[Main.V][i];
			blocs.add(bloc);
		}
		
		//ACs
		List<Integer> Y= new LinkedList<Integer>();
		List<Integer> U= new LinkedList<Integer>();
		List<Integer> V= new LinkedList<Integer>();
		//int[][] listACs = new int[Main.COLOR_SPACE_SIZE][ACs[0].length];
		for (int i=0;i<ACs[0].length;i++)
		{
			for(int j=0;j<ACs[0][i][0];j++)
				Y.add(0);
			
			Y.add(ACs[0][i][1]);
		}
		
		for (int i=0;i<ACs[1].length;i++)
		{
			for(int j=0;j<ACs[1][i][0];j++)
				U.add(0);
			
			U.add(ACs[1][i][1]);
		}
		
		for (int i=0;i<ACs[2].length;i++)
		{
			for(int j=0;j<ACs[2][i][0];j++)
				V.add(0);
			
			V.add(ACs[2][i][1]);
		}
		
		int[][] listACs = new int[Main.COLOR_SPACE_SIZE][Y.size()];
		int[][] listACs2 = new int[Main.COLOR_SPACE_SIZE][63];
		int[] ZigZagOrder = { 
				0,1,5,6,14,15,27,28,
				2,4,7,13,16,26,29,42,
				3,8,12,17,25,30,41,43,
				9,11,18,24,31,40,44,53,
				10,19,23,32,39,45,52,54,
				20,22,33,38,46,51,55,60,
				21,34,37,47,50,56,59,61,
				35,36,48,49,57,58,62,63};
		
		for (int i=0;i<Y.size();i++)
		{
			listACs[0][i]=Y.get(i);
			listACs[1][i]=U.get(i);
			listACs[2][i]=V.get(i);
		}
		
		for (int[][][] bloc : blocs)
		{
			for (int i=0;i<63;i++)
			{
				listACs2[0][findZigZagOrder(i)/*indice du zigzagorder où y'a le i*/]=listACs[0][i+blocs.indexOf(bloc)*63];
				listACs2[1][findZigZagOrder(i)/*indice du zigzagorder où y'a le i*/]=listACs[1][i+blocs.indexOf(bloc)*63];
				listACs2[2][findZigZagOrder(i)/*indice du zigzagorder où y'a le i*/]=listACs[2][i+blocs.indexOf(bloc)*63];
			}
			
			for (int i=1;i<64;i++)
			{
				bloc[0][i/8][i%8] = listACs2[0][i-1];
				bloc[1][i/8][i%8] = listACs2[1][i-1];
				bloc[2][i/8][i%8] = listACs2[2][i-1];
			}
		}
		
		
		return blocs;
	}
	
	private static int findZigZagOrder(int i)
	{
		int[] ZigZagOrder = { 
				0,1,5,6,14,15,27,28,
				2,4,7,13,16,26,29,42,
				3,8,12,17,25,30,41,43,
				9,11,18,24,31,40,44,53,
				10,19,23,32,39,45,52,54,
				20,22,33,38,46,51,55,60,
				21,34,37,47,50,56,59,61,
				35,36,48,49,57,58,62,63};
		
		for (int j=0;j<ZigZagOrder.length;j++)
			if (ZigZagOrder[j]==i)
				return j;
		
		return 0;
	}

}
