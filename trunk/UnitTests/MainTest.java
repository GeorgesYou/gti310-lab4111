package UnitTests;

import gti310.tp4.BlocManager;
import gti310.tp4.ConvertColor;
import gti310.tp4.DCTManager;
import gti310.tp4.Entropy;
import gti310.tp4.PPMReaderWriter;
import gti310.tp4.Quantification;
import gti310.tp4.ZigZag;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

public class MainTest  extends TestCase{
	public void testMain2()
	{
		assertEquals(1, 1);
		List<int[][][]> blocs = new LinkedList<int[][][]>();
		blocs = Quantification.Do(DCTManager.DCT(BlocManager.split(ConvertColor.convertRGBToYUV(PPMReaderWriter.readPPMFile("lena.ppm")))),Integer.parseInt("50"));
		
		PPMReaderWriter.writePPMFile("output2.ppm", 
				ConvertColor.convertYUVToRGB(
											BlocManager.merge(DCTManager.iDCT(
															Quantification.UnDo(
																				blocs,
																				70)),
															256,
															256)));	
	}
	
	
//	public void testMain()
//	{
//		List<int[][][]> listBlocs = new LinkedList<int[][][]>();
//		
//		int[][] blocIniY = {{200,202,189,188,189,175,175,175},{200,203,198,188,189,182,178,175},{203,200,200,195,200,187,185,175},{200,200,200,200,197,187,187,187},{200,205,200,200,195,188,187,175},{200,200,200,200,200,190,187,175},{205,200,199,200,191,187,187,175},{210,200,200,200,188,185,187,186}};//new int[Main.COLOR_SPACE_SIZE][8][8];
//		int[][] blocIniU = {{200,202,189,188,189,175,175,175},{200,203,198,188,189,182,178,175},{203,200,200,195,200,187,185,175},{200,200,200,200,197,187,187,187},{200,205,200,200,195,188,187,175},{200,200,200,200,200,190,187,175},{205,200,199,200,191,187,187,175},{210,200,200,200,188,185,187,186}};//new int[Main.COLOR_SPACE_SIZE][8][8];
//		int[][] blocIniV = {{200,202,189,188,189,175,175,175},{200,203,198,188,189,182,178,175},{203,200,200,195,200,187,185,175},{200,200,200,200,197,187,187,187},{200,205,200,200,195,188,187,175},{200,200,200,200,200,190,187,175},{205,200,199,200,191,187,187,175},{210,200,200,200,188,185,187,186}};//new int[Main.COLOR_SPACE_SIZE][8][8];
//		int[][][] blocIni = {blocIniY,blocIniU,blocIniV};
//		listBlocs.add(blocIni);
//		listBlocs.add(blocIni);
//		listBlocs.add(blocIni);
//		int[][][] AC = ZigZag.GetAC(Quantification.Do(DCTManager.DCT(listBlocs), 70));
//		int[][] DC = ZigZag.GetDC(Quantification.Do(DCTManager.DCT(listBlocs), 70));
//
//		//DC
//		for (int i=0;i<DC[0].length;i++)
//			Entropy.writeDC(DC[0][i]);
//		for (int i=0;i<DC[1].length;i++)
//			Entropy.writeDC(DC[1][i]);
//		for (int i=0;i<DC[2].length;i++)
//			Entropy.writeDC(DC[2][i]);
//		//AC
//		for (int i=0;i<AC[0].length;i++)
//			Entropy.writeAC(AC[0][i][0], AC[0][i][1]);
//		for (int i=0;i<AC[1].length;i++)
//			Entropy.writeAC(AC[1][i][0], AC[1][i][1]);
//		for (int i=0;i<AC[2].length;i++)
//			Entropy.writeAC(AC[2][i][0], AC[2][i][1]);
//		
//		
//		
//		
//					Entropy.loadBitstream(Entropy.getBitstream());
//		
//		
//		
//		int space=3;
//		int width=3*8;
//		int height=8;
//		
//		int[][][] ACs = new int[space][width*height*63/64][2];
//		int[][] DCs = new int[space][(width*height/64)];
//		
//		//DC
//		for (int i=0;i<(width*height/64);i++)
//			DCs[0][i]=Entropy.readDC();
//		
//		for (int i=0;i<(width*height/64);i++)
//			DCs[1][i]=Entropy.readDC();
//		
//		for (int i=0;i<(width*height/64);i++)
//			DCs[2][i]=Entropy.readDC();
//		
//		//AC
//		int i=0;
//		int ibloc=0;
//		int k=0;
//		while (true)
//		{
////			System.out.println("Y:i "+i+"   ib "+ibloc);
//			if (i>=(width*height*63/64))
//				break;
//			int[] couple = Entropy.readAC();
////			System.out.println(couple[0]+" "+couple[1]);
////			System.out.println(" 0:"+couple[0]+"    1:"+couple[1]);
//			if (couple[0]==0 && couple[1]==0)
//			{
//				i+=63-ibloc;
//				ibloc=0;
//			}
//			else
//			{
//				i+=couple[0];
//				ibloc+=couple[0];
//				i+=1;
//				ibloc+=1;
//				ACs[0][k] = couple;
//				k++;
//			}
//		}
//		System.out.println("Y "+k);
//		
//		i=0;
//		ibloc=0;
//		k=0;
//		while (true)
//		{
////			System.out.println("U:i "+i+"   ib "+ibloc);
//			if (i>=(width*height*63/64))
//				break;
//			int[] couple = Entropy.readAC();
////			System.out.println(couple[0]+" "+couple[1]);
////			System.out.println(" 0:"+couple[0]+"    1:"+couple[1]);
//			if (couple[0]==0 && couple[1]==0)
//			{
//				i+=63-ibloc;
//				ibloc=0;
//			}
//			else
//			{
//				i+=couple[0];
//				ibloc+=couple[0];
//				i+=1;
//				ibloc+=1;
//				ACs[1][k] = couple;
////				System.out.println(ACs[1][k][0]+" "+ACs[1][k][1]+" "+i);
//				k++;
//			}
//		}
//		System.out.println("U "+k);
//		
//		i=0;
//		ibloc=0;
//		k=0;
//		while (true)
//		{
////			System.out.println("V:i "+i+"   ib "+ibloc);
//			if (i>=(width*height*63/64))
//				break;
//			int[] couple = Entropy.readAC();
////			System.out.println(" 0:"+couple[0]+"    1:"+couple[1]);
//			if (couple[0]==0 && couple[1]==0)
//			{
//				i+=63-ibloc;
//				ibloc=0;
//			}
//			else
//			{
//				i+=couple[0];
//				ibloc+=couple[0];
//				i+=1;
//				ibloc+=1;
//				ACs[2][k] = couple;
//				k++;
//			}
//		}
//		System.out.println("V "+k);
//		
//		
//		List<int[][][]> blocs3 = DCTManager.iDCT(Quantification.UnDo(ZigZag.CreateBlocs(DCs, ACs, width, height), 70));
//		
////		for (int b=0;b<blocs3.size();b++)
////		for(int w = 0 ; w < 8;w++)
////			for (int j=0; j<8;j++)
////			{
////				System.out.println(b+"Debut: "+listBlocs.get(b)[2][w][j]+" Fin: "+blocs3.get(0)[2][w][j]);
////			}
//	}
}
