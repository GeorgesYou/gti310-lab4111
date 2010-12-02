package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class ZigZag {	
	
	public static int[][][] GetAC(List<int[][][]> blocs)
	{
		int[][] ACs = new int[Main.COLOR_SPACE_SIZE][blocs.size()*63];
//		int[][][] ACsToWrite = new int[Main.COLOR_SPACE_SIZE][blocs.size()*63][2];

//		for (int[][][] bloc : blocs)
		for (int b=0;b<blocs.size();b++)
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
						AC[Main.Y][ZigZagOrder[i*8+j]-1]=blocs.get(b)[Main.Y][i][j];
						AC[Main.U][ZigZagOrder[i*8+j]-1]=blocs.get(b)[Main.U][i][j];
						AC[Main.V][ZigZagOrder[i*8+j]-1]=blocs.get(b)[Main.V][i][j];
					}

			for (int v=0;v<63;v++)
			{
				ACs[Main.Y][b*63+v]=AC[Main.Y][v];
				ACs[Main.U][b*63+v]=AC[Main.U][v];
				ACs[Main.V][b*63+v]=AC[Main.V][v];
//				System.out.println("place:"+(b*64+v)+" "+ACs[Main.Y][b*8+v]);
			}
		}
		
//		for (int val:ACs[Main.Y])
//		{
//			System.out.println(ACs[Main.Y][150]);
//		}
		
		List<int[]> Y= new LinkedList<int[]>();
		int zeros=0;
//		int k=0;
		for (int val : ACs[Main.Y])
		{
			if (val==0)
				zeros++;
			else
			{
				int[] tab = {zeros,val};
				Y.add(tab);
//				ACsToWrite[Main.Y][k][0]=zeros;
//				ACsToWrite[Main.Y][k][1]=val;
				zeros=0;
//				k++;
			}
		}
		{int[] tab = {0,0};
		Y.add(tab);}
//		ACsToWrite[Main.Y][k][0]=0;
//		ACsToWrite[Main.Y][k][1]=0;

		List<int[]> U= new LinkedList<int[]>();
		zeros=0;
//		k=0;
		for (int val : ACs[Main.U])
		{
			if (val==0)
				zeros++;
			else
			{
				int[] tab = {zeros,val};
				U.add(tab);
//				ACsToWrite[Main.U][k][0]=zeros;
//				ACsToWrite[Main.U][k][1]=val;
				zeros=0;
//				k++;
			}
		}
		{int[] tab = {0,0};
		U.add(tab);}
//		ACsToWrite[Main.U][k][0]=0;
//		ACsToWrite[Main.U][k][1]=0;

		List<int[]> V= new LinkedList<int[]>();
		zeros=0;
//		k=0;
		for (int val : ACs[Main.V])
		{
			if (val==0)
				zeros++;
			else
			{
				int[] tab = {zeros,val};
				V.add(tab);
//				ACsToWrite[Main.V][k][0]=zeros;
//				ACsToWrite[Main.V][k][1]=val;
				zeros=0;
//				k++;
			}
		}
		{int[] tab = {0,0};
		V.add(tab);}
//		ACsToWrite[Main.V][k][0]=0;
//		ACsToWrite[Main.V][k][1]=0;

		int[][][] ACsToWrite = new int[Main.COLOR_SPACE_SIZE][Y.size()][2];
		for (int i=0;i<Y.size();i++)
		{
			ACsToWrite[0][i]=Y.get(i);
		}
		for (int i=0;i<U.size();i++)
		{
			ACsToWrite[1][i]=U.get(i);
		}
		for (int i=0;i<V.size();i++)
		{
			ACsToWrite[2][i]=V.get(i);
		}
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
				listACs2[0][findZigZagOrder(i+1)-1]=listACs[0][i+blocs.indexOf(bloc)*63];
				listACs2[1][findZigZagOrder(i+1)-1]=listACs[1][i+blocs.indexOf(bloc)*63];
				listACs2[2][findZigZagOrder(i+1)-1]=listACs[2][i+blocs.indexOf(bloc)*63];
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
