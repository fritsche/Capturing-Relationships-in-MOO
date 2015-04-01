/*SMPSO Test*/

package jmetal.learning;

import jmetal.core.Algorithm;
import jmetal.metaheuristics.smpso.SMPSO;
import jmetal.metaheuristics.moead.SplittedMOEAD_DRA;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.problems.WFG.WFG1;
import jmetal.core.SolutionSet;
import jmetal.util.JMException;
import java.io.IOException; // ClassNotFoundException
import java.util.HashMap;
import jmetal.operators.mutation.Mutation;
import jmetal.operators.crossover.CrossoverFactory;
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

	int gen;

	public SMPSOTest() throws ClassNotFoundException, JMException  {

		SplittedMOEAD_DRA algorithm = (SplittedMOEAD_DRA) buildAlgorithm();

	    // Execute the Algorithm 
	    SolutionSet population;
	   	algorithm.initialize();
	   	gen=0;
	    do {
	      population = algorithm.executeIteration();
	      if (gen%26 == 0) {
	      	runLearning(population);
	      }
	      System.out.println(gen++);
	    } while (!algorithm.isStopConditionSatisfied());
	 	population = algorithm.postExecution();
		runLearning(population);
  	}

	protected void runLearning(SolutionSet population) throws JMException {
		learning = new Learning(population);
		double[][] kendalls = learning.kendallsCorrelation();
		learning.printToFile("out/"+Integer.toString(gen)+".txt");
		//double[][] mutualInf = learning.mutualInformation();
	  	Solution aux = population.get(0);
	    n_vars = aux.numberOfVariables();
	    n_objs = aux.getNumberOfObjectives();

	    new Visualization(Integer.toString(gen), n_vars, n_objs, kendalls, 0.4);
	    //new Visualization("WFG1", n_vars, n_objs, mutualInf, 0.7);
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
		Algorithm algorithm = new SplittedMOEAD_DRA(problem);		
		Mutation  mutation  ;  // "Turbulence" operator
	    
	    // Algorithm parameters
	    /* SMPSO
	    algorithm.setInputParameter("swarmSize",100);
	    algorithm.setInputParameter("archiveSize",100);
	    algorithm.setInputParameter("maxIterations",250); 
		*/

	    algorithm.setInputParameter("populationSize",1000);
	    algorithm.setInputParameter("maxEvaluations",150000);
	    algorithm.setInputParameter("dataDirectory", "./weightVectors");

		algorithm.setInputParameter("finalSize", 300) ; // used by MOEAD_DRA

	    algorithm.setInputParameter("T", 20) ;
	    algorithm.setInputParameter("delta", 0.9) ;
	    algorithm.setInputParameter("nr", 2) ;

	    HashMap  parameters ; // Operator parameters
	    Operator  crossover ; 
	    // Crossover operator 
	    parameters = new HashMap() ;
	    parameters.put("CR", 1.0) ;
	    parameters.put("F", 0.5) ;
	    crossover = CrossoverFactory.getCrossoverOperator("DifferentialEvolutionCrossover", parameters);                   
	    
	    // Mutation operator
	    parameters = new HashMap() ;
	    parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
	    parameters.put("distributionIndex", 20.0) ;
	    mutation = MutationFactory.getMutationOperator("PolynomialMutation", parameters);                    
	    
	    algorithm.addOperator("crossover",crossover);
	    algorithm.addOperator("mutation",mutation);
	    

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

	public static void main(String[] args) throws ClassNotFoundException, JMException  {
		new SMPSOTest();
	}

}
