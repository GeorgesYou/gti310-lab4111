package UnitTests;
import java.util.LinkedList;
import java.util.List;

import gti310.tp4.BlocManager;
import gti310.tp4.Main;
import junit.framework.TestCase;

public class BlocManagerTest extends TestCase{
	
	public void testSplit(){
		
		int[][][] imageYUV = new int[Main.COLOR_SPACE_SIZE][8][8];
		
		for(int i = 0; i <8 ; i++)
		{
			for(int j = 0; j < 8 ; j++)
			{
				imageYUV[Main.Y][i][j] = j;
				imageYUV[Main.U][i][j] = j+1;
				imageYUV[Main.V][i][j] = j+2;
			}
		}
	
		assertEquals(1,BlocManager.split(imageYUV).size());	
		
	}
	
	public void testSplit2(){
		
		int[][][] imageYUV = new int[Main.COLOR_SPACE_SIZE][16][16];
		
		for(int i = 0; i <16 ; i++)
		{
			for(int j = 0; j < 16 ; j++)
			{
				imageYUV[Main.Y][i][j] = j;
				imageYUV[Main.U][i][j] = j+1;
				imageYUV[Main.V][i][j] = j+2;
			}
		}

		assertEquals(4,BlocManager.split(imageYUV).size());	
		
	}
	
	

}