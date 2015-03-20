package com.mxgraph.examples.swing;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class HelloWorld extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;

	public HelloWorld()
	{
		super("Hello, World!");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			Object v1 = graph.insertVertex(parent, null, "V1", 20, 20, 80,
					30);
			Object v2 = graph.insertVertex(parent, null, "O1!", 240, 150,
					80, 30);
			Object v3 = graph.insertVertex(parent, null, "O2!", 100, 50,
					180, 130);
			graph.insertEdge(parent, null, "0.5", v1, v2);
			graph.insertEdge(parent, null, "0.6", v1, v3);
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public static void main(String[] args)
	{
		HelloWorld frame = new HelloWorld();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}

}
