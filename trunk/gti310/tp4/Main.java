package gti310.tp4;

import java.util.LinkedList;
import java.util.List;



/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author François Caron
 */
public class Main {

	/*
	 * The entire application assumes that the blocks are 8x8 squares.
	 */
	public static final int BLOCK_SIZE = 8;
	
	/*
	 * The number of dimensions in the color spaces.
	 */
	public static final int COLOR_SPACE_SIZE = 3;
	
	/*
	 * The RGB color space.
	 */
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	/*
	 * The YUV color space.
	 */
	public static final int Y = 0;
	public static final int U = 1;
	public static final int V = 2;
	
	/**
	 * The application's entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		args = new String[]{"lena.ppm","20"};
		args = new String[]{"output.szl"};

		if (args[0].substring(args[0].length()-3, args[0].length()).equals("ppm"))
		{
			if (args.length==2)
			{
				List<int[][][]> blocs = new LinkedList<int[][][]>();
				blocs = Quantification.Do(DCTManager.DCT(BlocManager.split(ConvertColor.convertRGBToYUV(PPMReaderWriter.readPPMFile(args[0])))),Integer.parseInt(args[1]));
				int[][][] AC = ZigZag.GetAC(blocs);
				int[][] DC = ZigZag.GetDC(blocs);

//				for (int i=0;i<DC[0].length;i++)
//					System.out.println(DC[0][i]+" "+DC[1][i]+" "+DC[2][i]);
				
				//DC
				for (int i=0;i<DC[0].length;i++)
					Entropy.writeDC(DC[0][i]);
				
				for (int i=0;i<DC[1].length;i++)
					Entropy.writeDC(DC[1][i]);
	
				for (int i=0;i<DC[2].length;i++)
					Entropy.writeDC(DC[2][i]);
				
				//AC
				for (int i=0;i<AC[0].length;i++)
					Entropy.writeAC(AC[0][i][0], AC[0][i][1]);
	
				for (int i=0;i<AC[1].length;i++)					
					Entropy.writeAC(AC[1][i][0], AC[1][i][1]);

				for (int i=0;i<AC[2].length;i++)
				{
//					if (!(AC[2][i][0]==0 && AC[2][i][1]==0))
//						System.out.println("zeroes :"+AC[2][i][0]+" value :"+AC[2][i][1]);
					Entropy.writeAC(AC[2][i][0], AC[2][i][1]);
				}

				SZLReaderWriter.writeSZLFile("output.szl", PPMReaderWriter.readPPMFile(args[0])[0].length, PPMReaderWriter.readPPMFile(args[0])[0][0].length, Integer.parseInt(args[1]));
			}
		}
		
		if (args[0].substring(args[0].length()-3, args[0].length()).equals("szl"))
		{
			int[] header = SZLReaderWriter.readSZLFile(args[0]);
			int height = header[0];
			int width = header[1];
			int space = header[2];
			int fq = header[3];
				if (space!=3) System.exit(0);
				
			int[][][] ACs = new int[space][width*height*63/64][2];
			int[][] DCs = new int[space][(width*height/64)];
			
			//DC
			for (int i=0;i<(width*height/64);i++)
				DCs[0][i]=Entropy.readDC();
			
			for (int i=0;i<(width*height/64);i++)
				DCs[1][i]=Entropy.readDC();
			
			for (int i=0;i<(width*height/64);i++)
				DCs[2][i]=Entropy.readDC();
			
			//AC
			int i=0;
			int ibloc=0;
			int k=0;
			int eob=0;
			while (true)
			{
				if (i>=(width*height*63/64))
					break;
				int[] couple = Entropy.readAC();
				if (couple[0]==0 && couple[1]==0)
				{
					i+=63-ibloc;
					ibloc=0;
					eob++;
				}
				else
				{
					i+=couple[0];
					ibloc+=couple[0];
					i+=1;
					ibloc+=1;
					ACs[0][k] = couple;
					k++;
				}
			}
//			System.out.println("Y "+k+" "+eob);
			
			i=0;
			ibloc=0;
			k=0;
			eob=0;
			while (true)
			{
				if (i>=(width*height*63/64))
					break;
				int[] couple = Entropy.readAC();
				if (couple[0]==0 && couple[1]==0)
				{
					i+=63-ibloc;
					ibloc=0;
					eob++;
				}
				else
				{
					i+=couple[0];
					ibloc+=couple[0];
					i+=1;
					ibloc+=1;
					ACs[1][k] = couple;
					k++;
				}
			}
//			System.out.println("U "+k+" "+eob);
			
			i=0;
			ibloc=0;
			k=0;
			eob=0;
			while (true)
			{
//				System.out.println("V:i "+i+"   ib "+ibloc);
				if (i>=(width*height*63/64))
					break;
				int[] couple = Entropy.readAC();
//				System.out.println(" 0:"+couple[0]+"    1:"+couple[1]);
				if (couple[0]==0 && couple[1]==0)
				{
					i+=63-ibloc;
					ibloc=0;
					eob++;
				}
				else
				{
					i+=couple[0];
					ibloc+=couple[0];
					i+=1;
					ibloc+=1;
					ACs[2][k] = couple;
					k++;
				}
			}
//			System.out.println("V "+k+" "+eob);
			
//			for (int[] tab:ACs[0])
//			{
//				if (!(tab[0]==0 && tab[1]==0))
//				System.out.println("zeroes :"+tab[0]+" value :"+tab[1]);
//			}
			
			PPMReaderWriter.writePPMFile("output2.ppm", 
										ConvertColor.convertYUVToRGB(
																	BlocManager.merge(DCTManager.iDCT(
																					Quantification.UnDo(
																										ZigZag.CreateBlocs(DCs, ACs, width, height),
																										fq)),
																					width,
																					height)));				
		}
	}
}
