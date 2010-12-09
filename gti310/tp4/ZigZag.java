package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class ZigZag {	
	
	public static int[][][] GetAC(List<int[][][]> blocs)
	{
//		int[][] ACs = new int[Main.COLOR_SPACE_SIZE][blocs.size()*63];
		List<int[]> Y= new LinkedList<int[]>();
		List<int[]> U= new LinkedList<int[]>();
		List<int[]> V= new LinkedList<int[]>();
		
		for (int b=0;b<blocs.size();b++)
		{
			int Y0=0;
			int U0=0;
			int V0=0;
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

			for (int val : AC[Main.Y])
			{
				if (val==0)
					Y0++;
				else
				{
					int[] tab = {Y0,val};
					Y.add(tab);
					Y0=0;
				}
			}
			{int[] tab = {0,0};
			Y.add(tab);}
			
			for (int val : AC[Main.U])
			{
				if (val==0)
					U0++;
				else
				{
					int[] tab = {U0,val};
					U.add(tab);
					U0=0;
				}
			}
			{int[] tab = {0,0};
			U.add(tab);}

			for (int val : AC[Main.V])
			{
				if (val==0)
					V0++;
				else
				{
					int[] tab = {V0,val};
					V.add(tab);
					V0=0;
				}
			}
			{int[] tab = {0,0};
			V.add(tab);}
				
//			for (int v=0;v<63;v++)
//			{
//				ACs[Main.Y][b*63+v]=AC[Main.Y][v];
//				ACs[Main.U][b*63+v]=AC[Main.U][v];
//				ACs[Main.V][b*63+v]=AC[Main.V][v];
//			}
		}

		int[][][] ACsToWrite = new int[Main.COLOR_SPACE_SIZE][Y.size()][2];
		for (int i=0;i<Y.size();i++)
			ACsToWrite[0][i]=Y.get(i);
		
		for (int i=0;i<U.size();i++)
			ACsToWrite[1][i]=U.get(i);
		
		for (int i=0;i<V.size();i++)
			ACsToWrite[2][i]=V.get(i);
		
//		for (int[] tab:V)
//		{
//			if (!(tab[0]==0 && tab[1]==0))
//			System.out.println("zeroes :"+tab[0]+" value :"+tab[1]);
//		}
//		System.out.println("Y"+Y.size()+" U"+U.size()+" V"+V.size());
//		for (int i=0;i<U.size();i++)
//			System.out.println(U.get(i)[0]+" "+U.get(i)[1]);
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
		
		for (int i=(DC[Main.Y].length-1);i>=1;i--)
		{
			DC[Main.Y][i]=DC[Main.Y][i]-DC[Main.Y][i-1];
			DC[Main.U][i]=DC[Main.U][i]-DC[Main.U][i-1];
			DC[Main.V][i]=DC[Main.V][i]-DC[Main.V][i-1];
		}
		
//		for (int val:DC[2])
//		{
//			System.out.println("DC:"+val);
//		}
		
		return DC;
	}
	
	public static List<int[][][]> CreateBlocs(int[][] DCs, int[][][] ACs, int width, int height)
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

		for (int i=0;i<ACs[0].length;i++)
		{
			if (ACs[0][i][1]==0)
				break;
			
			for(int j=0;j<ACs[0][i][0];j++)
				Y.add(0);
			
			Y.add(ACs[0][i][1]);	
		}
		
		for (int i=0;i<ACs[1].length;i++)
		{
			if (ACs[1][i][1]==0)
				break;
			
			for(int j=0;j<ACs[1][i][0];j++)
				U.add(0);
			
			U.add(ACs[1][i][1]);
		}
		
		for (int i=0;i<ACs[2].length;i++)
		{
			if (ACs[2][i][1]==0)
				break;
			
			for(int j=0;j<ACs[2][i][0];j++)
				V.add(0);
			
			V.add(ACs[2][i][1]);
		}
		
		int[][] listACs = new int[Main.COLOR_SPACE_SIZE][(width*height*63/64)];
		int[][] listACs2 = new int[Main.COLOR_SPACE_SIZE][63];
		
//		int size=0;
//		if (Y.size()>size) size=Y.size();
//		if (U.size()>size) size=U.size();
//		if (V.size()>size) size=V.size();
		
		for (int i=0;i<(width*height*63/64);i++)
		{
			if (i>=Y.size())
				listACs[0][i]=0;
			else
				listACs[0][i]=Y.get(i);
			
			if (i>=U.size())
				listACs[1][i]=0;
			else
				listACs[1][i]=U.get(i);
			
			if (i>=V.size())
				listACs[2][i]=0;
			else
				listACs[2][i]=V.get(i);
			
//			System.out.println(listACs[0][i]+"  "+listACs[1][i]+"  "+listACs[2][i]);
		}

		for (int b=0;b<blocs.size();b++)
		{
			for (int i=0;i<63;i++)
			{
				listACs2[0][findZigZagOrder(i+1)-1]=listACs[0][i+b*63];
				listACs2[1][findZigZagOrder(i+1)-1]=listACs[1][i+b*63];
				listACs2[2][findZigZagOrder(i+1)-1]=listACs[2][i+b*63];
			}
			
			for (int i=1;i<64;i++)
			{
				blocs.get(b)[0][i/8][i%8] = listACs2[0][i-1];
				blocs.get(b)[1][i/8][i%8] = listACs2[1][i-1];
				blocs.get(b)[2][i/8][i%8] = listACs2[2][i-1];
			}
			
//			System.out.println(blocs.get(b)[0][0][0]+" "+blocs.get(b)[1][0][0]+" "+blocs.get(b)[2][0][0]);
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
