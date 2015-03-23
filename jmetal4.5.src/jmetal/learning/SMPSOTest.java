/*SMPSO Test*/

package jmetal.learning;

import jmetal.core.Algorithm;
import jmetal.metaheuristics.smpso.SMPSO;
import jmetal.core.Problem;
import jmetal.problems.WFG.WFG1;
import jmetal.core.SolutionSet;
import jmetal.util.JMException;
import java.io.IOException; // ClassNotFoundException
import java.util.HashMap;
import jmetal.operators.mutation.Mutation;
import jmetal.operators.mutation.MutationFactory;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.visualization.Visualization;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.KendallsCorrelation;

public class SMPSOTest {

	private int n_vars = 0, n_objs = 0;

	private Learning learning;

	public SMPSOTest() throws ClassNotFoundException, JMException {

		Algorithm algorithm = buildAlgorithm();

	    // Execute the Algorithm 
	    long initTime = System.currentTimeMillis();
	    SolutionSet population = algorithm.execute();
	    long estimatedTime = System.currentTimeMillis() - initTime;

	  	learning = new Learning(population);
		double[][] kendalls = learning.kendallsCorrelation();
		Solution aux = population.get(0);
	    n_vars = aux.numberOfVariables();
	    n_objs = aux.getNumberOfObjectives();

	    new Visualization("TESTE", n_vars, n_objs, kendalls, 0.3);

	}

	protected void printHeader(){
		for (int i = 1 ; i <= n_vars ; ++i ) {
			System.out.print("\tVar" + i);
		}
		for (int i = 1 ; i <= n_objs ; ++i ) {
			System.out.print("\tObj" + i);
		}
		System.out.println();
	}

	protected void correlationAnalisys(){
	
		System.out.println("Covariance:");
		double[][] result = learning.covariance();
		printHeader();
		printMatrix(result);
		System.out.println();

		System.out.println("Pearsons Correlation:");
		double[][] pearsons = learning.pearsonsCorrelation();
		printHeader();
		printMatrix(pearsons);
		System.out.println();

		System.out.println("Spearmans Correlation:");
		double[][] spearmans = learning.spearmansCorrelation();
		printHeader();
		printMatrix(spearmans);
		System.out.println();

		System.out.println("Kendalls Correlation:");
		double[][] kendalls = learning.kendallsCorrelation();
		printHeader();
		printMatrix(kendalls);
		System.out.println();
	}

	protected Algorithm buildAlgorithm() throws ClassNotFoundException, JMException {
		Problem problem = new WFG1("Real", 4, 12, 5);
		Algorithm algorithm = new SMPSO(problem);		
		Mutation  mutation  ;  // "Turbulence" operator
	    
	    // Algorithm parameters
	    algorithm.setInputParameter("swarmSize",1000);
	    algorithm.setInputParameter("archiveSize",1000);
	    algorithm.setInputParameter("maxIterations",2500);

	    HashMap parameters = new HashMap() ;
	    parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
	    parameters.put("distributionIndex", 20.0) ;
	    mutation = MutationFactory.getMutationOperator("PolynomialMutation", parameters);                    

	    algorithm.addOperator("mutation", mutation);

	    return algorithm;
	}

	protected double[][] buildMatrixData(SolutionSet population) throws JMException {
		int size = population.size();
	    Solution aux = population.get(0);
	    n_vars = aux.numberOfVariables();
	    n_objs = aux.getNumberOfObjectives();

		 double[][] data = new double[size][n_vars + n_objs];

	    for (int i = 0; i<size ; ++i) {
	    	Solution s = population.get(i);
	    	Variable[] vars = s.getDecisionVariables();
	    	int j = 0;
	    	for ( Variable v : vars )
	    		data[i][j++] = v.getValue();
	    	for (int k = 0; k < n_objs ; ++k)
	    		data[i][j++] = s.getObjective(k);
	    }
	    return data;
	}

	protected void printMatrix(double[][] matrix){
		for (double[] i : matrix) {
			for (double j : i) {
				String a = String.format("%.3f", j) ;
				System.out.print("\t" + a);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, JMException {
		new SMPSOTest();
	}

}
