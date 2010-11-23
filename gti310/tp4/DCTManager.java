package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class DCTManager {

	public static List<int[]> DCT(List<int[][][]> blocs)
	{
		
		//Coder une table cos[i][u]
		
		
		List<int[][]> Fs = new LinkedList<int[][]>();
		int Fy = 0;
		int Fu = 0;
		int Fv = 0;
		
		for (int[][][] bloc : blocs)
		{
			Fy = 0;
			Fu = 0;
			Fv = 0;
			for (int i=0;i<8;i++)
				for (int j=0;j<8;j++)
				{
					Fy+=Math.cos(((2*i+1)*u*Math.PI/16)*((2*j+1)*v*Math.PI/16))*bloc[Main.Y][i][j];
					Fu+=Math.cos(((2*i+1)*u*Math.PI/16)*((2*j+1)*v*Math.PI/16))*bloc[Main.U][i][j];
					Fv+=Math.cos(((2*i+1)*u*Math.PI/16)*((2*j+1)*v*Math.PI/16))*bloc[Main.V][i][j];
				}
			Fs.add(F);
		}
		
		return Fs;
	}

	public static void iDCT()
	{
		
	}
}
