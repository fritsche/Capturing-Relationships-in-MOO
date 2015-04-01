package jmetal.visualization;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxPerimeter;
import com.mxgraph.view.mxStylesheet;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import com.mxgraph.util.mxCellRenderer;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class Visualization extends JFrame implements ActionListener {
	
	private Hashtable<String, Object> baseStyle = new Hashtable<String, Object>();
    private mxStylesheet stylesheet;
    private mxGraph graph = new mxGraph();
	private Object parent = graph.getDefaultParent();
	private Map<Object, Object> nodes = new HashMap<Object, Object>();
	private JButton print;
	private String title;

	public Visualization(String title, int vars, int objs, double[][] data, double threshold) {
		super(title);
		this.title = title;
		this.setLayout(new BorderLayout());
		baseStyle.put(mxConstants.STYLE_STROKECOLOR, "#000000");
		stylesheet = graph.getStylesheet();	
		int v = 0;
		int o = 0;
		double arc_size;
		double node_size = 40.0;
		double r = 250.0;
		
		 // custom vertex style
        Hashtable<String, Object> styleV = new Hashtable<String, Object>(baseStyle);
        Hashtable<String, Object> styleO = new Hashtable<String, Object>(baseStyle);
        
        styleV.put(mxConstants.STYLE_FILLCOLOR, "#66FF66");
        styleV.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        styleV.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
        styleV.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        stylesheet.putCellStyle("variable", styleV);

        styleO.put(mxConstants.STYLE_FILLCOLOR, "#FFFF66");
        styleO.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        styleO.put(mxConstants.STYLE_FONTCOLOR, "#000000");
        styleO.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
        stylesheet.putCellStyle("objective", styleO);
        
		graph.getModel().beginUpdate();
		
		try {
			for (int i=0; i<vars ; ++i) {
				for (int j=i+1; j<vars; ++j) { // relatioship var/var
					if (data[i][j] > threshold || data[i][j] < -threshold) {
						if (nodes.get(i) == null ) {
							nodes.put(i, graph.insertVertex(parent, null, ("V"+(i+1)), 10, 10, 10, 10,"variable"));
							v++;
						}
						if (nodes.get(j) == null ) {
							nodes.put(j, graph.insertVertex(parent, null, ("V"+(j+1)), 10, 10, 10, 10,"variable"));
							v++;
						}
						createEdge(data[i][j], i,j);			
					}
				}
				for (int j = vars; j < vars + objs ; ++j) { // relatioship var/obj
					if (data[i][j] > threshold || data[i][j] < -threshold) {
						if (nodes.get(i) == null ) {
							nodes.put(i, graph.insertVertex(parent, null, ("V"+(i+1)), 10, 10, 10, 10,"variable"));
							v++;
						}
						if (nodes.get(j) == null ) {
							nodes.put(j, graph.insertVertex(parent, null, ("O"+(j+1-vars)), 10, 10, 10, 10,"objective"));
							o++;
						}
						createEdge(data[i][j], i,j);						
					}	
				}
			}
			for (int i = vars; i < vars + objs ; ++i) { // relatioship obj/obj
				for (int j=i+1; j < vars + objs ; ++j) { 
					if (data[i][j] > threshold || data[i][j] < -threshold) {
						if (nodes.get(i) == null ) {
							nodes.put(i, graph.insertVertex(parent, null, ("O"+(i+1-vars)), 10, 10, 10, 10,"objective"));
							v++;
						}
						if (nodes.get(j) == null ) {
							nodes.put(j, graph.insertVertex(parent, null, ("O"+(j+1-vars)), 10, 10, 10, 10,"objective"));
							o++;
						}
						createEdge(data[i][j], i,j);			
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
		this.add(graphComponent, BorderLayout.CENTER);

		print = new JButton("Print");
		print.addActionListener(this);
		this.add(print, BorderLayout.SOUTH);

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setVisible(true);
	}
	
	public void createEdge(double value, int startNode, int endNode){
	    Hashtable<String, Object> style = new Hashtable<String, Object>(baseStyle);

		if(value > 0){
			style.put(mxConstants.STYLE_STROKECOLOR, "#6666FF");
		}else{
			style.put(mxConstants.STYLE_STROKECOLOR, "#FF6666");
		}
		style.put(mxConstants.STYLE_STROKEWIDTH, Math.abs(value)*10);
        style.put(mxConstants.STYLE_EDGE,mxConstants.SHAPE_CONNECTOR);
        style.put(mxConstants.STYLE_FONTCOLOR,"#000000");
        stylesheet.putCellStyle(Double.toString(value), style);
        graph.insertEdge(parent, null, String.valueOf(value).subSequence(0, 5),nodes.get(startNode), nodes.get(endNode),Double.toString(value));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			BufferedImage image = mxCellRenderer.createBufferedImage(graph, null, 1, Color.WHITE, true, null);
			ImageIO.write(image, "PNG", new File("img/"+title+".png"));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
