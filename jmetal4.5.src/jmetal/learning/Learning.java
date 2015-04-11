// Learning.java

package jmetal.learning;

import jmetal.core.SolutionSet;
import jmetal.util.JMException;
import jmetal.core.Solution;
import jmetal.core.Variable;

import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.stat.correlation.KendallsCorrelation;

import JavaMI.MutualInformation;

import java.io.PrintWriter;
import java.io.FileWriter;

public class Learning {

	private int n_vars, n_objs;
	private double[][] data;
	private double[][] result;
	private int size;

	Learning (SolutionSet population) throws JMException {
		buildMatrixData(population);	
	}

	protected void buildMatrixData(SolutionSet population) throws JMException {
		size = population.size();
	    Solution aux = population.get(0);
	    n_vars = aux.numberOfVariables();
	    n_objs = aux.getNumberOfObjectives();

		data = new double[size][n_vars + n_objs];

	    for (int i = 0; i<size ; ++i) {
	    	Solution s = population.get(i);
	    	Variable[] vars = s.getDecisionVariables();
	    	int j = 0;
	    	for ( Variable v : vars )
	    		data[i][j++] = v.getValue();
	    	for (int k = 0; k < n_objs ; ++k)
	    		data[i][j++] = s.getObjective(k);
	    }
	}

	public double [][] covariance () {
		result = (new Covariance(data)).getCovarianceMatrix().getData();
		return result;
	} 

	public double [][] pearsonsCorrelation () {
		result = (new PearsonsCorrelation(data)).getCorrelationMatrix().getData();
		return result;
	}

	public double [][] spearmansCorrelation () {
		result = (new SpearmansCorrelation()).computeCorrelationMatrix(data).getData();
		return result;
	}

	public double [][] kendallsCorrelation () {
		result = (new KendallsCorrelation(data)).getCorrelationMatrix().getData();
		return result;
	}
	
	public double [][] mutualInformation(){
		int total = n_objs + n_vars;
		
		/*Inicialização da nova matriz com 0*/
		double[][] newMatrix = new double[total][total];
		for(int a = 0; a < total; a++){
			for(int b = 0; b < total; b++){
				newMatrix[a][b] = 0;
			}
		}
			
		double[] v1 = new double[size];
		double[] v2 = new double[size];
		for(int k = 0;k<total;k++){
			for(int l = k+1;l<total;l++){
				for(int j = 0; j < size; j++){
					v1[j] = data[j][k];
				}
				for(int j = 0; j < size; j++){
					v2[j] = data[j][l];		
				}
	
				/*guarda os valores na matriz com o valor da mutual information entre 2 variaveis*/
				double res = MutualInformation.calculateMutualInformation(v1,v2);
				newMatrix[k][l] = res;
				newMatrix[l][k] = res;
			}
		}
		result = newMatrix;
		return result;
	}

	public void printToFileDouble(String file) {

		try {
			FileWriter writer = new FileWriter(file);
			PrintWriter print = new PrintWriter(writer);

			for (double[] dd : result) {
				for (double d : dd) {
					print.printf("%f\t",d);
				}
				print.println();
			}

			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printToFileBin(double limiar,String file) {

		try {
			FileWriter writer = new FileWriter(file);
			PrintWriter print = new PrintWriter(writer);

			for (double[] dd : result) {
				for (double d : dd) {
					if(d > Math.abs(limiar)){
						print.printf("1\t");
					}else{
						print.printf("0\t");
					}
				}
				print.println();
			}

			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}