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

import gti310.tp4.Quantification;
import junit.framework.TestCase;

public class QuantificationTest extends TestCase{
	public void testQuant()
	{
		int[][][] yuv = new int[3][8][8];
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
			{
				yuv[0][i][j]=100*(i*8+j)+100;
				yuv[1][i][j]=100*(i*8+j)+200;
				yuv[2][i][j]=100*(i*8+j)+300;
			}
		
		List<int[][][]> blocs = new LinkedList<int[][][]>();
		blocs.add(yuv);
		blocs.add(yuv);
		blocs.add(yuv);
		
		List<int[][][]> blocs2 = Quantification.Do(blocs, 70);
		List<int[][][]> blocs3 = Quantification.UnDo(blocs2, 70);
		
//		for (int b=0;b<blocs2.size();b++)
//		for (int i=0;i<8;i++)
//			for (int j=0;j<8;j++)
//				System.out.println("Orig: "+blocs.get(b)[0][i][j]+" Quant: "+blocs2.get(b)[0][i][j]+" UnQuant: "+blocs3.get(0)[0][i][j]);
//				assertEquals(blocs.get(0)[0][i][j], blocs3.get(0)[0][i][j]);
	}
}
