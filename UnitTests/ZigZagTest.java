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
package UnitTests;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import gti310.tp4.ZigZag;


public class ZigZagTest extends TestCase{
	public void testZigZag()
	{
//		int[][][] yuv = new int[3][8][8];
//		for (int i=0;i<8;i++)
//			for (int j=0;j<8;j++)
//			{
//				yuv[0][i][j]=i*8+j;
//				yuv[1][i][j]=i*8+j;
//				yuv[2][i][j]=i*8+j;
//			}
//		
//		yuv[0][7][7]=0;
//		yuv[1][7][7]=0;
//		yuv[2][7][7]=0;
//		
//		List<int[][][]> blocs = new LinkedList<int[][][]>();
//		blocs.add(yuv);
//		blocs.add(yuv);
//		blocs.add(yuv);
//		
//		int[][][] AC = ZigZag.GetAC(blocs);
////		for (int i=0;i<AC[0].length;i++)
////				System.out.println(AC[0][i][0]+" zeroes and a "+AC[0][i][1]);
//		
//		int[][] DC = ZigZag.GetDC(blocs);
////		for (int i=0;i<DC[0].length;i++)
////			System.out.println("DC is "+DC[0][i]);
//		
//		List<int[][][]> blocs2 = ZigZag.CreateBlocs(DC, AC, 8*3, 8);
//		
//		for (int b=0;b<blocs2.size();b++)
//		for (int i=0;i<8;i++)
//			for (int j=0;j<8;j++)
//			{
////				System.out.println(blocs.get(b)[0][i][j]+" vs "+blocs2.get(b)[0][i][j]);
//				assertEquals(blocs.get(b)[0][i][j], blocs2.get(b)[0][i][j]);
//				assertEquals(blocs.get(b)[1][i][j], blocs2.get(b)[1][i][j]);
//				assertEquals(blocs.get(b)[2][i][j], blocs2.get(b)[2][i][j]);
//			}
	}
}
