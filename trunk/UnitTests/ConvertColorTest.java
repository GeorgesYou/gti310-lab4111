package UnitTests;

import gti310.tp4.BlocManager;
import gti310.tp4.ConvertColor;
import gti310.tp4.Main;
import junit.framework.TestCase;

public class ConvertColorTest extends TestCase {

	public void testConvertYUVToRGB(){
		
		int[][][] imageRGB = new int[Main.COLOR_SPACE_SIZE][256][256];
					
		for(int i = 0; i <256 ; i++)
		{
			for(int j = 0; j < 256 ; j++)
			{
				imageRGB[Main.R][i][j] = j;
				imageRGB[Main.G][i][j] = j;
				imageRGB[Main.B][i][j] = j;
			}
		}
	
		int[][][] imageYUV = ConvertColor.convertRGBToYUV(imageRGB);
		
		int[][][] imageRGB2 = ConvertColor.convertYUVToRGB(imageYUV);
		
		for(int i = 0; i <256 ; i++)
		{
			for(int j = 0; j < 256 ; j++)
			{
				assertTrue((imageRGB2[Main.R][i][j]>= 0) && imageRGB2[Main.R][i][j]<256);
				assertTrue((imageRGB2[Main.G][i][j]>=0) && imageRGB2[Main.R][i][j]<256);
				assertTrue((imageRGB2[Main.B][i][j]>=0) && imageRGB2[Main.R][i][j]<256);
			}
		}
		
	}
	
	public void testConvertYUVToRGB2(){
		
		int[][][] imageRGB = new int[Main.COLOR_SPACE_SIZE][256][256];
		
		for(int i = 0; i <256 ; i++)
		{
			for(int j = 0; j < 256 ; j++)
			{
				imageRGB[Main.R][i][j] = j;
				imageRGB[Main.G][i][j] = j;
				imageRGB[Main.B][i][j] = j;
			}
		}
	
		int[][][] imageYUV = ConvertColor.convertRGBToYUV(imageRGB);
		
		int[][][] imageRGB2 = ConvertColor.convertYUVToRGB(imageYUV);
		
		for(int i = 0; i <256 ; i++)
		{
			for(int j = 0; j < 256 ; j++)
			{
				assertTrue(imageRGB2[Main.R][i][j]-imageRGB[Main.R][i][j] <=1);
				assertTrue(imageRGB2[Main.G][i][j]-imageRGB[Main.G][i][j]<=1);
				assertTrue(imageRGB2[Main.B][i][j]-imageRGB[Main.B][i][j]<=1);
			}
		}
	}
}
