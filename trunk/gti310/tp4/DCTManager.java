package gti310.tp4;

import java.util.LinkedList;
import java.util.List;

public class DCTManager {

	public static List<int[][][]> DCT(List<int[][][]> blocs)
	{
		
		double[][] COS = new double[8][8];
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				COS[i][j]=Math.cos(((2*i+1)*j*Math.PI/16));
		
		List<int[][][]> Fs = new LinkedList<int[][][]>();
		double Fy = 0;
		int[] Fy1 = new int[8];
		int[][] Fy2 = new int[8][];
		double Fu = 0;
		int[] Fu1 = new int[8];
		int[][] Fu2 = new int[8][];
		double Fv = 0;
		int[] Fv1 = new int[8];
		int[][] Fv2 = new int[8][];
		
		for (int[][][] bloc : blocs)
		{
			Fy2 = new int[8][];
			Fu2 = new int[8][];
			Fv2 = new int[8][];
			for (int u=0;u<8;u++)
			{
				Fy1=new int[8];
				Fu1=new int[8];
				Fv1=new int[8];
				for (int v=0;v<8;v++)
				{
					Fy = 0;
					Fu = 0;
					Fv = 0;
					for (int i=0;i<8;i++)
						for (int j=0;j<8;j++)
						{
							Fy+=COS[i][u]*COS[j][v]*bloc[Main.Y][i][j];
							Fu+=COS[i][u]*COS[j][v]*bloc[Main.U][i][j];
							Fv+=COS[i][u]*COS[j][v]*bloc[Main.V][i][j];
						}
					Fy*=(C(u)*C(v)/4);
					Fu*=(C(u)*C(v)/4);
					Fv*=(C(u)*C(v)/4);
					Fy1[v]=(int)Math.round(Fy);
					Fu1[v]=(int)Math.round(Fu);
					Fv1[v]=(int)Math.round(Fv);
				}
				Fy2[u]=Fy1;
				Fu2[u]=Fu1;
				Fv2[u]=Fv1;
			}
			Fs.add(new int[][][]{Fy2,Fu2,Fv2});
		}
		
		return Fs;
	}

	public static List<int[][][]> iDCT(List<int[][][]> blocs)
	{
		double[][] COS = new double[8][8];
		for (int i=0;i<8;i++)
			for (int j=0;j<8;j++)
				COS[i][j]=Math.cos(((2*i+1)*j*Math.PI/16));
		
		List<int[][][]> Fs = new LinkedList<int[][][]>();
		double Fy = 0;
		int[] Fy1 = new int[8];
		int[][] Fy2 = new int[8][];
		double Fu = 0;
		int[] Fu1 = new int[8];
		int[][] Fu2 = new int[8][];
		double Fv = 0;
		int[] Fv1 = new int[8];
		int[][] Fv2 = new int[8][];
		
		for (int[][][] bloc : blocs)
		{
			Fy2 = new int[8][];
			Fu2 = new int[8][];
			Fv2 = new int[8][];
			for (int u=0;u<8;u++)
			{
				Fy1=new int[8];
				Fu1=new int[8];
				Fv1=new int[8];
				for (int v=0;v<8;v++)
				{
					Fy = 0;
					Fu = 0;
					Fv = 0;
					for (int i=0;i<8;i++)
						for (int j=0;j<8;j++)
						{
							Fy+=(C(i)*C(j)/4)*(COS[u][i]*COS[v][j]*bloc[Main.Y][i][j]);
							Fu+=(C(i)*C(j)/4)*(COS[u][i]*COS[v][j]*bloc[Main.U][i][j]);
							Fv+=(C(i)*C(j)/4)*(COS[u][i]*COS[v][j]*bloc[Main.V][i][j]);
						}
					Fy1[v]=(int)Math.round(Fy);
					Fu1[v]=(int)Math.round(Fu);
					Fv1[v]=(int)Math.round(Fv);
				}
				Fy2[u]=Fy1;
				Fu2[u]=Fu1;
				Fv2[u]=Fv1;
			}
			Fs.add(new int[][][]{Fy2,Fu2,Fv2});
		}
		
		return Fs;
	}
	
	private static double C(int w)
	{
		return (w==0 ? 1/Math.sqrt(2) : 1);
	}
}
