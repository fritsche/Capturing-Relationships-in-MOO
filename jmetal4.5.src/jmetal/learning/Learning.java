// Learning.java

package jmetal.learning;

import jmetal.core.SolutionSet;
import jmetal.util.JMException;
import jmetal.core.Solution;
import jmetal.core.Variable;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.correlation.SpearmansCorrelation;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.KendallsCorrelation;

public class Learning {

	private int n_vars, n_objs;
	private double[][] data;

	Learning (SolutionSet population) throws JMException {
		buildMatrixData(population);	
	}

	protected void buildMatrixData(SolutionSet population) throws JMException {
		int size = population.size();
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
		return (new Covariance(data)).getCovarianceMatrix().getData();
	} 

	public double [][] pearsonsCorrelation () {
		return (new PearsonsCorrelation(data)).getCorrelationMatrix().getData();
	}

	public double [][] spearmansCorrelation () {
		return (new SpearmansCorrelation()).computeCorrelationMatrix(data).getData();
	}

	public double [][] kendallsCorrelation () {
		return (new KendallsCorrelation(data)).getCorrelationMatrix().getData();
	}

}