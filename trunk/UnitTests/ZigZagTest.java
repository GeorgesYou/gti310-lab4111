package UnitTests;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import gti310.tp4.ZigZag;


public class ZigZagTest extends TestCase{
	public void testZigZag()
	{
		int[][][] yuv = new int[3][8][8];
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
			{
				yuv[0][i][j]=i*8+j;
				yuv[1][i][j]=i*8+j;
				yuv[2][i][j]=i*8+j;
			}
		
		List<int[][][]> blocs = new LinkedList<int[][][]>();
		blocs.add(yuv);
		
		int[][][] AC = ZigZag.GetAC(blocs);
		for (int i=0;i<15;i++)
				System.out.println(AC[0][i][0]+" zeroes and a "+AC[0][i][1]);
		
		int[][] DC = ZigZag.GetDC(blocs);
		for (int i=0;i<DC[0].length;i++)
			System.out.println("DC is "+DC[0][i]);
		
		List<int[][][]> blocs2 = ZigZag.CreateBlocs(DC, AC);
		
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				System.out.println(blocs.get(0)[0][i][j]+" vs "+blocs2.get(0)[0][i][j]);
				//assertEquals(blocs.get(0)[0][i][j], blocs2.get(0)[0][i][j]);
	}
}
