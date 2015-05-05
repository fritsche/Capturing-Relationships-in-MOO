package jmetal.visualization;

import java.io.*;
import java.util.StringTokenizer;

public class VisualizationFromFile {

	public VisualizationFromFile (int v, int o, double t, String file, String title) {
		
		int n = v + o;

		double[][] matrix = new double[n][n];
		
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String aux = br.readLine();
			int i = 0;
			while (aux != null) {
				StringTokenizer st = new StringTokenizer(aux);
				while (st.hasMoreTokens()) {
					double value = (new Double(st.nextToken())).doubleValue();
					matrix[i/n][i%n] = value;
					i++;
				}
				aux = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Visualization(title, v, o, matrix, t) ;
	}

	public static void main(String[] args) {

		String[] methods = {"Kendalls", "Pearsons", "Spearmans"};
		int[] shots = {0, 156, 312, 468, 598, 745};
		for (String m : methods) {
			for (int s : shots) {
				new VisualizationFromFile (16, 5, 0.25, ("out/"+m+"/shot"+s+"_clean.txt"), (m+s));	
			}
		}
	}

}
