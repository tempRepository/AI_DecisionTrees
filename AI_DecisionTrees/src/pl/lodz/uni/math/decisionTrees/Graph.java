package pl.lodz.uni.math.decisionTrees;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Graph {
    private static void generateJPG() throws IOException
    {
        Process p ;
        p=Runtime.getRuntime().exec("cat graph | uniq > graph");
        p=Runtime.getRuntime().exec("rm output.jpg");  
        p= Runtime.getRuntime().exec("dot -Tjpg graph  -o output.jpg");  
    }
    
    private static void generateGraphviz(Tree tree)
    {
        //generating code for GraphViz
        PrintWriter out;
        try {
            out = new PrintWriter("graph");
            //generating string for graphviz
            String graph="digraph G {";
            graph+=tree.toString();
            graph+="}";
            out.println(graph);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       

      
    }
    
    public static void generateGraphImage(Tree tree)
    {
        generateGraphviz(tree);
        try {
            generateJPG();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
