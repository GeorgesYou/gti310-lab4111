/******************************************************
 Laboratoire #4 : Compression quasi-JPEG
 
 Cours :             GTI310
 Session :           Automne 2010
 Groupe :            01
 Projet :            Laboratoire #4
 �tudiant(e)(s) :    Gabriel Desmarais
 					 Marie-�ve Benoit
 Code(s) perm. :     DESG24078908
 					 BENM22568707
 Charg�e de lab. :   Jean-Fran�ois Franche 
 Nom du fichier :    Main.java
 Date cr�e :         2010-12-1
 Date dern. modif.   2010-12-1
 *******************************************************/
package gti310.tp4;

import java.util.LinkedList;
import java.util.List;


/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author Fran�ois Caron
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
		
//		args = new String[]{"lena.ppm","50"};
		args = new String[]{"output.szl"};

		if (args[0].substring(args[0].length()-3, args[0].length()).equals("ppm"))
		{
			if (args.length==2)
			{
				List<int[][][]> blocs = new LinkedList<int[][][]>();
				blocs = Quantification.Do(DCTManager.DCT(BlocManager.split(ConvertColor.convertRGBToYUV(PPMReaderWriter.readPPMFile(args[0])))),Integer.parseInt(args[1]));
				int[][][] AC = ZigZag.GetAC(blocs);
				int[][] DC = ZigZag.GetDC(blocs);
				
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
					Entropy.writeAC(AC[2][i][0], AC[2][i][1]);

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
				
			List<int[]> Y= new LinkedList<int[]>();
			List<int[]> U= new LinkedList<int[]>();
			List<int[]> V= new LinkedList<int[]>();
			int[][] DCs = new int[space][width*height/64];
			
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
					{int[] tab = {0,0};
					Y.add(tab);}
				}
				else
				{
					i+=couple[0];
					ibloc+=couple[0];
					i+=1;
					ibloc+=1;
					Y.add(couple);
				}
			}
			
			i=0;
			ibloc=0;
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
					{int[] tab = {0,0};
					U.add(tab);}
				}
				else
				{
					i+=couple[0];
					ibloc+=couple[0];
					i+=1;
					ibloc+=1;
					U.add(couple);
				}
			}
			
			i=0;
			ibloc=0;
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
					{int[] tab = {0,0};
					V.add(tab);}
				}
				else
				{
					i+=couple[0];
					ibloc+=couple[0];
					i+=1;
					ibloc+=1;
					V.add(couple);
				}
			}
			
			PPMReaderWriter.writePPMFile("output2.ppm", 
										ConvertColor.convertYUVToRGB(
																	BlocManager.merge(DCTManager.iDCT(
																					Quantification.UnDo(
																										ZigZag.CreateBlocs(DCs, Y,U,V, width, height),
																										fq)),
																					width,
																					height)));				
		}
	}
}
