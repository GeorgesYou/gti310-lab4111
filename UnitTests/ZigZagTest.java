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
				yuv[0][i][j]=4+i*8+j;
				yuv[1][i][j]=4+i*8+j;
				yuv[2][i][j]=4+i*8+j;
			}
		
		List<int[][][]> blocs = new LinkedList<int[][][]>();
		blocs.add(yuv);
		
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
		assertEquals(blocs.get(0)[0][i][j], ZigZag.CreateBlocs(ZigZag.GetDC(blocs), ZigZag.GetAC(blocs)).get(0)[0][i][j]);
	}
}
