package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class BlocManager {

	public static List<int[][][]> split(int[][][] tab)
	{
		List<int[][][]> blocs = new LinkedList<int[][][]>();
		int[][][] bloc = new int[Main.COLOR_SPACE_SIZE][8][8];
		for (int i=0;i<(tab[0].length/8);i++)
		{
			for (int l=0;l<(tab[0][i].length/8);l++)
			{
				bloc = new int[Main.COLOR_SPACE_SIZE][8][8];
				for (int j=0;j<8;j++)
					for (int k=0;k<8;k++)
					{
						bloc[Main.Y][j][k]=tab[Main.Y][j+i*8][k+l*8];
						bloc[Main.U][j][k]=tab[Main.U][j+i*8][k+l*8];
						bloc[Main.V][j][k]=tab[Main.V][j+i*8][k+l*8];
					}
				blocs.add(bloc);
			}
		}
		return blocs;
	}
	
	
	public static int[][][] merge(List<int[][][]> blocs, int w, int h)
	{
		int[][][] tab = new int[Main.COLOR_SPACE_SIZE][w/**blocs.size()*/][h/**blocs.size()*/];
		
//		for (int[][][] bloc : blocs)
		for (int b=0;b<blocs.size();b++)
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					tab[Main.Y][i+((b*8)%w)][j+((b*8)%h)]=blocs.get(b)[Main.Y][i][j];
					tab[Main.U][i+((b*8)%w)][j+((b*8)%h)]=blocs.get(b)[Main.U][i][j];
					tab[Main.V][i+((b*8)%w)][j+((b*8)%h)]=blocs.get(b)[Main.V][i][j];
				}
		
		return tab;
	}
}
