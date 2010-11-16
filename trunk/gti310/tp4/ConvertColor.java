package gti310.tp4;
import java.util.*;

public class ConvertColor {

	// Matrice de conversion, source: https://cours.etsmtl.ca/gti310/private/Presentations/GTI310-3-ConceptsFondamentauxVideo-v14.pdf
	//private double matrice[][] = {{0.299,0.587,0.114},{-0.147,-0.289,0.436},{0.615, -0.515, -0.100}};
	
	public double[][][] convertRGBToYUV(int[][][] image)
	{
		
		double[][][] imageYUV = new double[Main.COLOR_SPACE_SIZE][][];
		
		for(int i = 0; i < image.length; i++)
		{
			for(int j = 0; j < image[0].length;j++)
			{
				double Y = 0.299*image[Main.R][i][j] + 0.587*image[Main.G][i][j] +0.114*image[Main.B][i][j];
				double U = 0.492*(image[Main.B][i][j]-Y);
				double V = 0.877*(image[Main.R][i][j]-Y);
				
				imageYUV[Main.Y][i][j]=Y;
				imageYUV[Main.U][i][j]=U;
				imageYUV[Main.V][i][j]=V;				
			}			
		}
		
		
		return imageYUV;
	}
}
