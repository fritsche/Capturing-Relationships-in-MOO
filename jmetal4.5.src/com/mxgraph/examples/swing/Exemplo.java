package com.mxgraph.examples.swing;

/**
 *
 * @author Andrei
 */
import java.util.Hashtable;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import com.mxgraph.view.mxPerimeter;

public class Exemplo extends JFrame {

    private static final long serialVersionUID = 672772281200016954L;

    public static final String MY_CUSTOM_VERTEX_STYLE = "MY_CUSTOM_VERTEX_STYLE";
    public static final String MY_CUSTOM_EDGE_STYLE = "MY_CUSTOM_EDGE_STYLE";

    public static final String MY_CUSTOM_VERTEX_STYLE2 = "MY_CUSTOM_VERTEX_STYLE2";
    public static final String MY_CUSTOM_EDGE_STYLE2 = "MY_CUSTOM_EDGE_STYL2";
    
    private static void setStyleSheet(mxGraph graph) {

        Hashtable<String, Object> style;
        mxStylesheet stylesheet = graph.getStylesheet();

        // base style
        Hashtable<String, Object> baseStyle = new Hashtable<String, Object>();
        baseStyle.put(mxConstants.STYLE_STROKECOLOR, "#000000");

        // custom vertex style
        style = new Hashtable<String, Object>(baseStyle);
        style.put(mxConstants.STYLE_FILLCOLOR, "#00FF00");
        style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_PERIMETER, mxPerimeter.EllipsePerimeter);
        stylesheet.putCellStyle(MY_CUSTOM_VERTEX_STYLE, style);

        // custom edge style
        style = new Hashtable<String, Object>(baseStyle);
        style.put(mxConstants.STYLE_STROKEWIDTH, 1);
        style.put(mxConstants.STYLE_STROKECOLOR, "#0220f0");
        //para stroke:http://www.w3schools.com/svg/svg_stroking.asp
        stylesheet.putCellStyle(MY_CUSTOM_EDGE_STYLE, style);

        
        // custom vertex2 style
        style = new Hashtable<String, Object>(baseStyle);
        style.put(mxConstants.STYLE_FILLCOLOR, "#700090");
        style.put(mxConstants.STYLE_SHAPE,mxConstants.SHAPE_ELLIPSE);
        style.put(mxConstants.STYLE_PERIMETER,mxPerimeter.EllipsePerimeter);
        stylesheet.putCellStyle(MY_CUSTOM_VERTEX_STYLE2, style);

        // custom edge2 style
        style = new Hashtable<String, Object>(baseStyle);
        style.put(mxConstants.STYLE_STROKECOLOR, "#FF0001");
        style.put(mxConstants.STYLE_STROKEWIDTH, 10);
        style.put(mxConstants.STYLE_EDGE,mxConstants.SHAPE_CONNECTOR);
        stylesheet.putCellStyle(MY_CUSTOM_EDGE_STYLE2, style);
    }

    public Exemplo() {
        super("Hello, World!");

        final mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        // create styles
        setStyleSheet(graph);

        graph.getModel().beginUpdate();
        try {
            //mxGraph.insertVertex(parent, id, value, x, y, width(largura), height(altura), style)
            //mxGraph.insertEdge(parent, id(identificador), value(nome), source(vertex), target(vertex), style) 
           
            // shape="Ellipse
            Object v5 = graph.insertVertex(parent, null, "no1", 40, 80, 30, 30, MY_CUSTOM_VERTEX_STYLE);          
            Object v6 = graph.insertVertex(parent, null, "no2", 90, 140, 30, 30, MY_CUSTOM_VERTEX_STYLE);
            graph.insertEdge(parent, null,"Aresta entre nós circulares", v5, v6,MY_CUSTOM_EDGE_STYLE2);
            
            Object v3 = graph.insertVertex(parent, null, "Andrei", 400, 300, 40, 40, MY_CUSTOM_VERTEX_STYLE);
            Object v4 = graph.insertVertex(parent, null, "Strickler", 640, 100, 40, 40,MY_CUSTOM_VERTEX_STYLE2);

            graph.insertEdge(parent, null, "Edge", v5, v4, MY_CUSTOM_EDGE_STYLE);
            graph.insertEdge(parent, null, "Aresta", v3, v4, MY_CUSTOM_EDGE_STYLE2);

        } finally {
            graph.getModel().endUpdate();
        }

        final mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);

    }

    public static void main(String[] args) {
        Exemplo frame = new Exemplo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 320);
        frame.setVisible(true);
    }
}
