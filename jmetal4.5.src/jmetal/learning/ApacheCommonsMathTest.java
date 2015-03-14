/* apache commons math test */

package jmetal.learning;

import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.KendallsCorrelation;

public class ApacheCommonsMathTest {

	public ApacheCommonsMathTest(){
		System.out.println("Testing Apache Commons Math...\n");

		System.out.println("Input data:");
		double[][] data = { {1.0, 10.0, 5.0, 3.0},
							{2.0, 20.0, 4.0, 3.0},
							{3.0, 30.0, 3.0, 3.0},
							{4.0, 40.0, 2.0, 3.0},
							{5.0, 50.0, 1.0, 3.0}};
		printMatrix(data);
		System.out.println();

		System.out.println("Covariance:");
		Covariance covariance = new Covariance(data);
		RealMatrix matrix = covariance.getCovarianceMatrix();
		double[][] result = matrix.getData();
		printMatrix(result);
		System.out.println();

		System.out.println("Pearsons Correlation:");
		double[][] pearsons = (new PearsonsCorrelation(data)).getCorrelationMatrix().getData();
		printMatrix(pearsons);
		System.out.println();

		System.out.println("Spearmans Correlation:");
		double[][] spearmans = (new SpearmansCorrelation()).computeCorrelationMatrix(data).getData();
		printMatrix(spearmans);
		System.out.println();

		System.out.println("Kendalls Correlation:");
		double[][] kendalls = (new KendallsCorrelation(data)).getCorrelationMatrix().getData();
		printMatrix(kendalls);
		System.out.println();

	}

	protected void printMatrix(double[][] matrix){
		for (double[] i : matrix) {
			for (double j : i) {
				System.out.print("\t" + j);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		new ApacheCommonsMathTest();
	}

}