package jmetal.visualization;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.util.HashMap;
import java.util.Map;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

public class Visualization extends JFrame {

	public Visualization(String title, int vars, int objs, double[][] data, double threshold) {
		super(title);

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		Map<Object, Object> nodes = new HashMap<Object, Object>();
		int v = 0;
		int o = 0;
		double arc_size;
		double node_size = 40.0;
		double r = 250.0;

		graph.getModel().beginUpdate();
		
		try {
			for (int i=0; i<vars ; ++i) {
				for (int j=i+1; j<vars; ++j) { // relatioship var/var
					if (data[i][j] > threshold || data[i][j] < -threshold) {
						if (nodes.get(i) == null ) {
							nodes.put(i, graph.insertVertex(parent, null, ("V"+(i+1)), 10, 10, 10, 10));
							v++;
						}
						if (nodes.get(j) == null ) {
							nodes.put(j, graph.insertVertex(parent, null, ("V"+(j+1)), 10, 10, 10, 10));
							v++;
						}
						graph.insertEdge(parent, null, String.format("%.3f", data[i][j]), nodes.get(i), nodes.get(j));			
					}
				}
				for (int j = vars; j < vars + objs ; ++j) { // relatioship var/obj
					if (data[i][j] > threshold || data[i][j] < -threshold) {
						if (nodes.get(i) == null ) {
							nodes.put(i, graph.insertVertex(parent, null, ("V"+(i+1)), 10, 10, 10, 10));
							v++;
						}
						if (nodes.get(j) == null ) {
							nodes.put(j, graph.insertVertex(parent, null, ("O"+(j+1-vars)), 10, 10, 10, 10));
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
							nodes.put(i, graph.insertVertex(parent, null, ("O"+(i+1-vars)), 10, 10, 10, 10));
							v++;
						}
						if (nodes.get(j) == null ) {
							nodes.put(j, graph.insertVertex(parent, null, ("O"+(j+1-vars)), 10, 10, 10, 10));
							o++;
						}
						graph.insertEdge(parent, null, String.format("%.3f", data[i][j]), nodes.get(i), nodes.get(j));			
					}
				}
			}
			
			arc_size = 360.0 / (double) nodes.size();
			double angle = 0;
			for (Map.Entry<Object, Object> entry : nodes.entrySet()) {
				mxCell cell = (mxCell) entry.getValue();
				double x = (r * Math.cos(Math.toRadians(angle))) + node_size + r;
				double y = (r * Math.sin(Math.toRadians(angle))) + node_size + r;
				double width = node_size;
				double height = node_size;
				mxGeometry geometry = new mxGeometry(x, y, width, height);
				cell.setGeometry(geometry);
				angle += arc_size;
			}

		} 
		finally {
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setVisible(true);
	}
}
