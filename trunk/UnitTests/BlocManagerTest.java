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
 Nom du fichier :    BlocManagerTest.java
 Date crée :         2010-12-1
 Date dern. modif.   2010-12-1
 *******************************************************/
package UnitTests;
import java.util.LinkedList;
import java.util.List;

import gti310.tp4.BlocManager;
import gti310.tp4.ConvertColor;
import gti310.tp4.Main;
import junit.framework.TestCase;

public class BlocManagerTest extends TestCase{
	
	
	public void testSplitYUV()
	{
		// Test with array from ConvertColor
		int[][][] imageRGB = new int[Main.COLOR_SPACE_SIZE][8][16];
		
		for(int i = 0; i <8 ; i++)
		{
			for(int j = 0; j < 16 ; j++)
			{
				imageRGB[Main.R][i][j] = j;
				imageRGB[Main.G][i][j] = j;
				imageRGB[Main.B][i][j] = j;
			}
		}
	
		int[][][] imageYUV = ConvertColor.convertRGBToYUV(imageRGB);
	}
	
	public void testSplit()
	{	
		int[][][] imageYUV = new int[Main.COLOR_SPACE_SIZE][8][8];
		
		for(int i = 0; i <8 ; i++)
			for(int j = 0; j < 8 ; j++)
			{
				imageYUV[Main.Y][i][j] = j;
				imageYUV[Main.U][i][j] = j+1;
				imageYUV[Main.V][i][j] = j+2;
			}
		assertEquals(1,BlocManager.split(imageYUV).size());	
	}
	
	public void testSplit2(){
		
		int[][][] imageYUV = new int[Main.COLOR_SPACE_SIZE][16][16];
		
		for(int i = 0; i <16 ; i++)
			for(int j = 0; j < 16 ; j++)
			{
				imageYUV[Main.Y][i][j] = j;
				imageYUV[Main.U][i][j] = j+1;
				imageYUV[Main.V][i][j] = j+2;
			}
		assertEquals(4,BlocManager.split(imageYUV).size());		
	}
	
	public void testBloc(){
		
		int[][][] imageYUV = new int[Main.COLOR_SPACE_SIZE][8][16];
		
		for(int i = 0; i <8 ; i++)
			for(int j = 0; j < 16 ; j++)
			{
				imageYUV[Main.Y][i][j] = i*16+j;
				imageYUV[Main.U][i][j] = i*16+j;
				imageYUV[Main.V][i][j] = i*16+j;
			}
		
		int[][][] imageYUV2 = BlocManager.merge(BlocManager.split(imageYUV), 8, 16);
		
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
			{
				assertEquals(imageYUV[Main.Y][i][j], imageYUV2[Main.Y][i][j]);
				assertEquals(imageYUV[Main.U][i][j], imageYUV2[Main.U][i][j]);
				assertEquals(imageYUV[Main.V][i][j], imageYUV2[Main.V][i][j]);
			}
	}
}
