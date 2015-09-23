/*SMPSO Test*/

package jmetal.learning;

import jmetal.core.Algorithm;
import jmetal.metaheuristics.nsgaII.*;
import jmetal.metaheuristics.smpso.SMPSO;
import jmetal.metaheuristics.moead.SplittedMOEAD_DRA;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.problems.WFG.WFG1;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.core.SolutionSet;
import jmetal.util.JMException;

import java.io.IOException; // ClassNotFoundException
import java.util.HashMap;

import jmetal.operators.mutation.Mutation;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.SelectionFactory;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.visualization.Visualization;

import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.KendallsCorrelation;

public class SMPSOTest {

	private int n_vars = 0, n_objs = 0, qntExecucoes = 1, split = 26;
	private String output = "visualization"; // "visualization" or "file", for no output use any other string
	private double threshold = 0.3;
	private Learning learning;
	private String Kendalls = "Kendalls";
	private String Pearsons = "Pearsons";
	private String Spearmans = "Spearmans";
	

	
	int gen;
	int i;

	public SMPSOTest() throws ClassNotFoundException, JMException  {

		SplittedMOEAD_DRA algorithm = (SplittedMOEAD_DRA) buildAlgorithm();
		//NSGAII algorithm = (NSGAII) buildAlgorithm();
	    // Execute the Algorithm 
		for(i = 1; i <= qntExecucoes; i++){
			System.out.println("Execution: "+i);
		    SolutionSet population;
		    algorithm.execute();
		   	algorithm.initialize();
		   	gen=0;
		   	do {
		      population = algorithm.executeIteration();
		      if (gen%split == 0) {
		      	runLearning(population);
		      }
		      gen++;
		    } while (!algorithm.isStopConditionSatisfied());
		 	population = algorithm.postExecution();
			runLearning(population);
		}
  	}

	protected void runLearning(SolutionSet population) throws JMException {
		System.out.println(gen);
		learning = new Learning(population);
		double[][] result;

		Solution aux = population.get(0);
		n_vars = aux.numberOfVariables();
		n_objs = aux.getNumberOfObjectives();

 		result = learning.kendallsCorrelation();
 		if(output.equals("file")){
			learning.printToFileDouble("out/"+Kendalls+"/double/execucao_"+i+"/"+Integer.toString(gen)+".txt");
			learning.printToFileBin(threshold,"out/"+Kendalls+"/binario/execucao_"+i+"/"+Integer.toString(gen)+".txt");
		} else if (output.equals("visualization")){
			new Visualization(Integer.toString(gen)+" "+Kendalls, n_vars, n_objs, result, threshold);
		}
		result =  learning.spearmansCorrelation();
		if(output.equals("file")){
			learning.printToFileDouble("out/"+Spearmans+"/double/execucao_"+i+"/"+Integer.toString(gen)+".txt");
			learning.printToFileBin(threshold,"out/"+Spearmans+"/binario/execucao_"+i+"/"+Integer.toString(gen)+".txt");
		} else if (output.equals("visualization")){
			new Visualization(Integer.toString(gen)+" "+Spearmans, n_vars, n_objs, result, threshold);
		}
		result = learning.pearsonsCorrelation();
		if(output.equals("file")){
			learning.printToFileDouble("out/"+Pearsons+"/double/execucao_"+i+"/"+Integer.toString(gen)+".txt");
			learning.printToFileBin(threshold,"out/"+Pearsons+"/binario/execucao_"+i+"/"+Integer.toString(gen)+".txt");
		} else if (output.equals("visualization")){
			new Visualization(Integer.toString(gen)+" "+Pearsons, n_vars, n_objs, result, threshold);
		}
		//double[][] mutualInf = learning.mutualInformation();
		
	    //new Visualization(Integer.toString(gen), n_vars, n_objs, kendalls, limiar);
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
		//Algorithm algorithm = new SplittedMOEAD_DRA(problem);		
		Algorithm algorithm = new NSGAII(problem);
		Mutation  mutation  ;  // "Turbulence" operator
	    QualityIndicator indicators ; // Object to get quality indicators
	    indicators = null ;

		
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
	    Operator  selection ; // Selection operator

	    // Crossover operator FOR MOEA/D-DRA 
	    /*
	    parameters = new HashMap() ;
	    parameters.put("CR", 1.0) ;
	    parameters.put("F", 0.5) ;
	    crossover = CrossoverFactory.getCrossoverOperator("DifferentialEvolutionCrossover", parameters);                   
	    */
	    parameters = new HashMap() ;
	    parameters.put("probability", 0.9) ;
	    parameters.put("distributionIndex", 20.0) ;
	    crossover = CrossoverFactory.getCrossoverOperator("SBXCrossover", parameters);                   

	    
	    // Mutation operator
	    parameters = new HashMap() ;
	    parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
	    parameters.put("distributionIndex", 20.0) ;
	    mutation = MutationFactory.getMutationOperator("PolynomialMutation", parameters);                    
	    
	    // Selection Operator 
	    parameters = null ;
	    selection = SelectionFactory.getSelectionOperator("BinaryTournament2", parameters) ;                           

	    // Add the operators to the algorithm
	    algorithm.addOperator("crossover",crossover);
	    algorithm.addOperator("mutation",mutation);
	    algorithm.addOperator("selection",selection);

	    // Add the indicator object to the algorithm
	    algorithm.setInputParameter("indicators", indicators) ;


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
