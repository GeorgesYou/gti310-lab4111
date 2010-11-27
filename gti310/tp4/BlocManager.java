package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class BlocManager {

	public static List<int[][][]> split(int[][][] tab)
	{
		List<int[][][]> blocs = new LinkedList<int[][][]>();
		int[][][] bloc = new int[Main.COLOR_SPACE_SIZE][8][8];
		for (int i=0;i<=(tab[0].length/8);i++)
		{
			bloc = new int[Main.COLOR_SPACE_SIZE][8][8];
			for (int j=0;j<8;j++)
				for (int k=0;k<8;k++)
				{
					bloc[Main.Y][j][k]=tab[Main.Y][j+i*8][k+i*8];
					bloc[Main.U][j][k]=tab[Main.U][j+i*8][k+i*8];
					bloc[Main.V][j][k]=tab[Main.V][j+i*8][k+i*8];
				}
			blocs.add(bloc);
		}
		return blocs;
	}
	
	
	public static int[][][] merge(List<int[][][]> blocs)
	{
		int[][][] tab = new int[Main.COLOR_SPACE_SIZE][][];
		
		for (int[][][] bloc : blocs)
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					tab[Main.Y][i+8*blocs.indexOf(bloc)][j+8*blocs.indexOf(bloc)]=bloc[Main.Y][i][j];
					tab[Main.U][i+8*blocs.indexOf(bloc)][j+8*blocs.indexOf(bloc)]=bloc[Main.U][i][j];
					tab[Main.V][i+8*blocs.indexOf(bloc)][j+8*blocs.indexOf(bloc)]=bloc[Main.V][i][j];
				}
		
		return tab;
	}
}
