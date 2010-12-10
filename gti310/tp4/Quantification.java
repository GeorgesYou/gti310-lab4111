/******************************************************
 Laboratoire #4 : Compression quasi-JPEG
 
 Cours :             GTI310
 Session :           Automne 2010
 Groupe :            01
 Projet :            Laboratoire #4
 Étudiant(e)(s) :    Gabriel Desmarais
 					 Marie-Ève Benoit
 Code(s) perm. :     DESG24078908
 					 BENM22568707
 Chargée de lab. :   Jean-François Franche 
 Nom du fichier :    Data.java
 Date crée :         2010-12-1
 Date dern. modif.   2010-12-1
 *******************************************************/
package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class Quantification {
	private static int[][] Y = new int[8][8];
	private static int[][] UV = new int[8][8];
	
	
	/**n
	 * C1+C2+C3+C4+C5+C6(N-1)+C7N+C8(N-1)N+C9(N-1)N*N+C10N*N*N+C11N*N*N+C12N*N*N
	 * C13N+C14
	 *  
	 *  K+N+N+N^2+N^3+N^3+N^3+N^3+N+C14
	 *  
	 * O(N^3)
	 * 
	 * @param Liste de blocs
	 * @param degrée de qualité
	 */	
	public static List<int[][][]> Do(List<int[][][]> blocs, int fq)
	{		
		if (fq==100)
			return blocs;
		
		List<int[][][]> blocsQuant = new LinkedList<int[][][]>();
		
		InitMatrice();
		double a = (fq<=50 ? (double)(50/fq) : (double)(200-2*fq)/100);
		
		for (int[][][] bloc : blocs)
		{
			int[][][] blocQuant = new int[Main.COLOR_SPACE_SIZE][8][8];
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					blocQuant[Main.Y][i][j]=(int) Math.round(bloc[Main.Y][i][j]/(a*Y[i][j]));
					blocQuant[Main.U][i][j]=(int) Math.round(bloc[Main.U][i][j]/(a*UV[i][j]));
					blocQuant[Main.V][i][j]=(int) Math.round(bloc[Main.V][i][j]/(a*UV[i][j]));
				}
			
			blocsQuant.add(blocQuant);
		}		

		return blocsQuant;
	}
		
	/**n
	 * C1+C2+C3+C4+C5+C6(N-1)+C7N+C8(N-1)N+C9(N-1)N*N+C10N*N*N+C11N*N*N+C12N*N*N
	 * C13N+C14
	 *  
	 *  K+N+N+N^2+N^3+N^3+N^3+N^3+N+C14
	 *  
	 * O(N^3)
	 * 
	 * @param Liste de blocs
	 * @param degrée de qualité
	 */	
	public static List<int[][][]> UnDo(List<int[][][]> blocs, int fq)
	{
		if (fq==100)
			return blocs;
		
		List<int[][][]> blocsUnQuant = new LinkedList<int[][][]>();
		
		InitMatrice();
		double a = (fq<=50 ? (double)(50/fq) : (double)(200-2*fq)/100);
		
		for (int[][][] bloc : blocs)//6
		{
			int[][][] blocUnQuant = new int[Main.COLOR_SPACE_SIZE][8][8];
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					blocUnQuant[Main.Y][i][j]=(int) Math.round(bloc[Main.Y][i][j]*(a*Y[i][j]));
					blocUnQuant[Main.U][i][j]=(int) Math.round(bloc[Main.U][i][j]*(a*UV[i][j]));
					blocUnQuant[Main.V][i][j]=(int) Math.round(bloc[Main.V][i][j]*(a*UV[i][j]));
				}
			
			blocsUnQuant.add(blocUnQuant);
		}

		
		return blocsUnQuant;
	}
	
	/**n  
	 *  C1+...+C128 = K = 1
	 *  
	 * O(1)
	 *	
	 */	
	private static void InitMatrice()
	{
		Y[0][0]=46;
		Y[0][1]=41;
		Y[0][2]=40;
		Y[0][3]=46;
		Y[0][4]=54;
		Y[0][5]=60;
		Y[0][6]=61;
		Y[0][7]=71;

		Y[1][0]=42;
		Y[1][1]=42;
		Y[1][2]=44;
		Y[1][3]=49;
		Y[1][4]=56;
		Y[1][5]=58;
		Y[1][6]=70;
		Y[1][7]=75;

		Y[2][0]=44;
		Y[2][1]=43;
		Y[2][2]=46;
		Y[2][3]=54;
		Y[2][4]=50;
		Y[2][5]=57;
		Y[2][6]=69;
		Y[2][7]=56;

		Y[3][0]=44;
		Y[3][1]=47;
		Y[3][2]=42;
		Y[3][3]=49;
		Y[3][4]=61;
		Y[3][5]=87;
		Y[3][6]=80;
		Y[3][7]=62;

		Y[4][0]=48;
		Y[4][1]=42;
		Y[4][2]=47;
		Y[4][3]=56;
		Y[4][4]=68;
		Y[4][5]=109;
		Y[4][6]=103;
		Y[4][7]=77;

		Y[5][0]=44;
		Y[5][1]=45;
		Y[5][2]=55;
		Y[5][3]=64;
		Y[5][4]=81;
		Y[5][5]=104;
		Y[5][6]=113;
		Y[5][7]=92;

		Y[6][0]=49;
		Y[6][1]=64;
		Y[6][2]=78;
		Y[6][3]=87;
		Y[6][4]=103;
		Y[6][5]=121;
		Y[6][6]=120;
		Y[6][7]=101;

		Y[7][0]=72;
		Y[7][1]=92;
		Y[7][2]=95;
		Y[7][3]=98;
		Y[7][4]=112;
		Y[7][5]=100;
		Y[7][6]=103;
		Y[7][7]=95;
		
		UV[0][0]=47;
		UV[0][1]=48;
		UV[0][2]=44;
		UV[0][3]=57;
		UV[0][4]=95;
		UV[0][5]=95;
		UV[0][6]=95;
		UV[0][7]=95;
		
		UV[1][0]=48;
		UV[1][1]=41;
		UV[1][2]=46;
		UV[1][3]=66;
		UV[1][4]=95;
		UV[1][5]=95;
		UV[1][6]=95;
		UV[1][7]=95;

		UV[2][0]=44;
		UV[2][1]=46;
		UV[2][2]=56;
		UV[2][3]=95;
		UV[2][4]=95;
		UV[2][5]=95;
		UV[2][6]=95;
		UV[2][7]=95;

		UV[3][0]=47;
		UV[3][1]=66;
		UV[3][2]=95;
		UV[3][3]=95;
		UV[3][4]=95;
		UV[3][5]=95;
		UV[3][6]=95;
		UV[3][7]=95;

		UV[4][0]=95;
		UV[4][1]=95;
		UV[4][2]=95;
		UV[4][3]=95;
		UV[4][4]=95;
		UV[4][5]=95;
		UV[4][6]=95;
		UV[4][7]=95;

		UV[5][0]=95;
		UV[5][1]=95;
		UV[5][2]=95;
		UV[5][3]=95;
		UV[5][4]=95;
		UV[5][5]=95;
		UV[5][6]=95;
		UV[5][7]=95;

		UV[6][0]=95;
		UV[6][1]=95;
		UV[6][2]=95;
		UV[6][3]=95;
		UV[6][4]=95;
		UV[6][5]=95;
		UV[6][6]=95;
		UV[6][7]=95;

		UV[7][0]=95;
		UV[7][1]=95;
		UV[7][2]=95;
		UV[7][3]=95;
		UV[7][4]=95;
		UV[7][5]=95;
		UV[7][6]=95;
		UV[7][7]=95;
	}

}
