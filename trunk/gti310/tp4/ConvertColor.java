package gti310.tp4;
import java.util.*;
import java.math.*;

public class ConvertColor {

	// Matrice de conversion, source: https://cours.etsmtl.ca/gti310/private/Presentations/GTI310-3-ConceptsFondamentauxVideo-v14.pdf
	//private double matrice[][] = {{0.299,0.587,0.114},{-0.147,-0.289,0.436},{0.615, -0.515, -0.100}};
	
	public static int[][][] convertRGBToYUV(int[][][] image)
	{
		
		int[][][] imageYUV = new int[Main.COLOR_SPACE_SIZE][image[0].length][image[0][0].length];
		
		for(int i = 0; i < image[0].length; i++)
		{
			for(int j = 0; j < image[0][0].length;j++)
			{
							
				int Y = (int)(0.299*image[Main.R][i][j] + 0.587*image[Main.G][i][j] +0.114*image[Main.B][i][j]);
				int U = (int)(0.492*(image[Main.B][i][j]-Y));
				int V = (int)(0.877*(image[Main.R][i][j]-Y));
				
				imageYUV[Main.Y][i][j]=Y;
				imageYUV[Main.U][i][j]=U;
				imageYUV[Main.V][i][j]=V;					
			}			
		}		
		
		return imageYUV;
	}
	
	public static int[][][] convertYUVToRGB(int[][][] image)
	{
		int[][][] imageRGB = new int[Main.COLOR_SPACE_SIZE][image[0].length][image[0][0].length];
		
		for(int i = 0; i < image[0].length; i++)
		{
			for(int j = 0; j < image[0][0].length;j++)
			{		
				int R = (int)(1.140250855*(image[Main.V][i][j]+.877*image[Main.Y][i][j]));
				int G = (int)(-0.3947313749*(image[Main.U][i][j]+1.471403709*(image[Main.V][i][j]-1.721795786*image[Main.Y][i][j])));
				int B = (int)(2.032520325*(image[Main.U][i][j]+0.492*image[Main.Y][i][j]));
		
				R = (R<0 ? 0 :( R > 255 ? 255 : R) );
				G = (G<0 ? 0 :( G > 255 ? 255 : G) );
				B = (B<0 ? 0 :( B > 255 ? 255 : B) );				
		
				imageRGB[Main.R][i][j]=R;
				imageRGB[Main.G][i][j]=G;
				imageRGB[Main.B][i][j]=B;				
			}			
		}	

		return imageRGB;
	}
	
}
