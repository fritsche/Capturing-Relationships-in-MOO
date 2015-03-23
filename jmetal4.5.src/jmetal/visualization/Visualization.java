package jmetal.visualization;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.util.HashMap;
import java.util.Map;


public class Visualization extends JFrame {

	public Visualization(String title, int vars, int objs, double[][] data, double threshold) {
		super(title);

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		Map nodes = new HashMap();
		int v = 0;
		int o = 0;

		graph.getModel().beginUpdate();
		
		try {
			for (int i=0; i<vars ; ++i) {
				for (int j=i+1; j<vars; ++j) { // relatioship var/var
					if (data[i][j] > threshold || data[i][j] < -threshold) {
						if (nodes.get(i) == null ) {
							nodes.put(i, graph.insertVertex(parent, null, ("V"+(i+1)), (120*(v+1)), 40, 40, 40));
							v++;
						}
						if (nodes.get(j) == null ) {
							nodes.put(j, graph.insertVertex(parent, null, ("V"+(j+1)), (120*(v+1)), 40, 40, 40));
							v++;
						}
						graph.insertEdge(parent, null, String.format("%.3f", data[i][j]), nodes.get(i), nodes.get(j));			
					}
				}
				for (int j = vars; j < vars + objs ; ++j) { // relatioship var/obj
					if (data[i][j] > threshold || data[i][j] < -threshold) {
						if (nodes.get(i) == null ) {
							nodes.put(i, graph.insertVertex(parent, null, ("V"+(i+1)), (120*(v+1)), 40, 40, 40));
							v++;
						}
						if (nodes.get(j) == null ) {
							nodes.put(j, graph.insertVertex(parent, null, ("O"+(j+1-vars)), (120*(o+1)), 120, 40, 40));
							o++;
						}
						graph.insertEdge(parent, null, String.format("%.3f", data[i][j]), nodes.get(i), nodes.get(j));			
					}	
				}
			}
			for (int i = vars; i < vars + objs ; ++i) { // relatioship obj/obj
				for (int j=i+1; j < vars + objs ; ++j) { 
					if (data[i][j] > threshold || data[i][j] < -threshold) {
						if (nodes.get(i) == null ) {
							nodes.put(i, graph.insertVertex(parent, null, ("O"+(i+1-vars)), (120*(o+1)), 40, 40, 40));
							v++;
						}
						if (nodes.get(j) == null ) {
							nodes.put(j, graph.insertVertex(parent, null, ("O"+(j+1-vars)), (120*(o+1)), 120, 40, 40));
							o++;
						}
						graph.insertEdge(parent, null, String.format("%.3f", data[i][j]), nodes.get(i), nodes.get(j));			
					}
				}
			}

		} 
		finally {
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 320);
		setVisible(true);
	}
}
