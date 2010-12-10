package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class BlocManager {

	/**
	 * C1+C2+C3(N-1)+C4(N-1)(N-1)+C5(N-1)N+C6(N-1)(N-1)(N-1)+C7(N-1)(N-1)(N-1)(N-1)+(C8+C9+C10)(N-1)(N-1)(N-1)N+C11(N-1)(N-1)N+C12
	 * C1+C2+C12+N+C4*N^2+C5*N^2+C6*N^3+C7*N^4+K1*N^4+C11*N^3
	 * K2+N+N^2+N^2+N^3+N^4+N^4+N^3
	 * N^4
	 *  
	 * O(N^4)
	 * 
	 * @param tableau en trois dimension d'une image en YUV
	 */		
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
	
	/**
	 * C1+C2+C3(N-1)+C4(N-1)(N-1)+C5(N-1)(N-1)(N-1)+(C6+C7+C8)(N-1)(N-1)N+C7
	 * K1+N+N^2+N^3+K2*N^3+C7
	 * N+N^2+N^3
	 * N^3
	 *  
	 * O(N^3)
	 * 
	 * @param tableau en trois dimension d'une image en YUV
	 * @param la largeur
	 * @param la hauteur 
	 * 
	 */	
	public static int[][][] merge(List<int[][][]> blocs, int w, int h)
	{
		int[][][] tab = new int[Main.COLOR_SPACE_SIZE][w/**blocs.size()*/][h/**blocs.size()*/];

		System.out.println(blocs.size());
		for (int b=0;b<blocs.size();b++)
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{

					tab[Main.Y][i+(((b*8)/w)*8)][j+((b*8)%w)]=blocs.get(b)[Main.Y][i][j];
					tab[Main.U][i+(((b*8)/w)*8)][j+((b*8)%w)]=blocs.get(b)[Main.U][i][j];
					tab[Main.V][i+(((b*8)/w)*8)][j+((b*8)%w)]=blocs.get(b)[Main.V][i][j];
				}

		return tab;
	}
}
