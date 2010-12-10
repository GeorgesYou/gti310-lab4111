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

public class DCTManager {


	/**n
	 * C1+C2(N-1)+C2(N-1)N+C3(N-1)N*N+C4+C5+C6+C7+C8+C9+C10+C11+C12+C13+C14+C15(N-1)+C16N+C17N+
	 * C18N+C19(N-1)N+C20N*N+C21N*N+C22N*N+C23N*N*(N-1)+C24N*N*N+C25N*N*N+C26N*N*N+
	 * C27*N*N*N*(N-1)+ C28*N*N*N*N*(N-1)+C29*N*N*N*N*N+C30*N*N*N*N*N+C31*N*N*N*N*N+ 
	 * C32*N*N*N+C33*N*N*N+C34*N*N*N+C35*N*N*N+C36*N*N*N+C37*N*N*N+
	 * C38*N*N+C39*N*N+C40*N*N+C41*N+C42
	 * 
	 * C1+N+N^2+n^3+K+N+N+N+N+N^2+N^2+N^2+N^2+N^3+N^3+N^3+N^3+
	 * N^4+N^5+N^5+N^5+N^5+N^3+N^3+N^3+N^3+N^3+N^3+N^2+N^2+N^2+N+C42
	 *  
	 *  
	 * O(N^5)
	 * 
	 * @param Liste de blocs
	 */		
	public static List<int[][][]> DCT(List<int[][][]> blocs)
	{
		
		double[][] COS = new double[8][8];
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				COS[i][j]=Math.cos(((2*i+1)*j*Math.PI/16));
		
		List<int[][][]> Fs = new LinkedList<int[][][]>();
		double Fy = 0;
		int[] Fy1 = new int[8];
		int[][] Fy2 = new int[8][];
		double Fu = 0;
		int[] Fu1 = new int[8];
		int[][] Fu2 = new int[8][];
		double Fv = 0;
		int[] Fv1 = new int[8];
		int[][] Fv2 = new int[8][];
		
		for (int[][][] bloc : blocs)
		{
			Fy2 = new int[8][];
			Fu2 = new int[8][];
			Fv2 = new int[8][];
			for (int u=0;u<8;u++)
			{
				Fy1=new int[8];
				Fu1=new int[8];
				Fv1=new int[8];
				for (int v=0;v<8;v++)
				{
					Fy = 0;
					Fu = 0;
					Fv = 0;
					for (int i=0;i<8;i++)
						for (int j=0;j<8;j++)
						{
							Fy+=COS[i][u]*COS[j][v]*bloc[Main.Y][i][j];
							Fu+=COS[i][u]*COS[j][v]*bloc[Main.U][i][j];
							Fv+=COS[i][u]*COS[j][v]*bloc[Main.V][i][j];
						}
					Fy*=(C(u)*C(v)/4);
					Fu*=(C(u)*C(v)/4);
					Fv*=(C(u)*C(v)/4);
					Fy1[v]=(int)Math.round(Fy);
					Fu1[v]=(int)Math.round(Fu);
					Fv1[v]=(int)Math.round(Fv);
				}
				Fy2[u]=Fy1;
				Fu2[u]=Fu1;
				Fv2[u]=Fv1;
			}
			Fs.add(new int[][][]{Fy2,Fu2,Fv2});
		}
		
		return Fs;
	}

	/**n
	 *  C1+C2(N-1)+C3(N-1)N+C4(N*N)+C5+C6+C7+C8+C9+C10+C11+C12+C13+C14
	 *  C15(N-1)+C16N+C17N+C18N+C19N(N-1)+ C20N*N+C21N*N+C22N*N+C23N*N*(N-1)+
	 *  C24N*N*N+C25N*N*N+C26N*N*N+C27N*N*N*(N-1)+C28N*N*N*N*(N-1)
	 *  C29N*N*N*N*N+C30N*N*N*N*N+C31N*N*N*N*N+C32N*N*N+C33N*N*N+C34N*N*N+
	 *  C35N*N+C36N*N+C37N*N+C38*N+C39
	 *  
	 *  C1+N+N^2+N^2+K+ N+N+N+N+N^2+N^2+N^2+N^3 +N^3+N^3+N^3+N^4+N^3+N^5
	 *  N^5+N^5+N^5+N^3+N^3+N^3 + N^2+N^2+N^2+N+C39
	 *    
	 * O(N^5)
	 * 
	 * @param Liste de blocs
	 */		
	
	public static List<int[][][]> iDCT(List<int[][][]> blocs)
	{
		double[][] COS = new double[8][8];
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				COS[i][j]=Math.cos(((2*i+1)*j*Math.PI/16));
		
		List<int[][][]> Fs = new LinkedList<int[][][]>();
		double Fy = 0;
		int[] Fy1 = new int[8];
		int[][] Fy2 = new int[8][];
		double Fu = 0;
		int[] Fu1 = new int[8];
		int[][] Fu2 = new int[8][];
		double Fv = 0;
		int[] Fv1 = new int[8];
		int[][] Fv2 = new int[8][];
		
		for (int[][][] bloc : blocs)
		{
			Fy2 = new int[8][];
			Fu2 = new int[8][];
			Fv2 = new int[8][];
			for (int u=0;u<8;u++)
			{
				Fy1=new int[8];
				Fu1=new int[8];
				Fv1=new int[8];
				for (int v=0;v<8;v++)
				{
					Fy = 0;
					Fu = 0;
					Fv = 0;
					for (int i=0;i<8;i++)
						for (int j=0;j<8;j++)
						{
							Fy+=(C(i)*C(j)/4)*(COS[u][i]*COS[v][j]*bloc[Main.Y][i][j]);
							Fu+=(C(i)*C(j)/4)*(COS[u][i]*COS[v][j]*bloc[Main.U][i][j]);
							Fv+=(C(i)*C(j)/4)*(COS[u][i]*COS[v][j]*bloc[Main.V][i][j]);
						}
					Fy1[v]=(int)Math.round(Fy);
					Fu1[v]=(int)Math.round(Fu);
					Fv1[v]=(int)Math.round(Fv);
				}
				Fy2[u]=Fy1;
				Fu2[u]=Fu1;
				Fv2[u]=Fv1;
			}
			Fs.add(new int[][][]{Fy2,Fu2,Fv2});
		}
		
		return Fs;
	}
	
	/*n
	 * O(1)
	 */
	private static double C(int w)
	{
		return (w==0 ? 1/Math.sqrt(2) : 1);
	}
}
