import base.Data;
import impl.DirectedGraph;
import impl.UnDirectedGraph;
import impl.WeightDirectedGraph;

public class Main {

    public static void main(String[] args) {

        // Data for Vertex
        Data[] dataArray = {new Data(4), new Data(9),
                new Data(10), new Data(7),
                new Data(1), new Data(25),
                new Data(14),};

        /***
         * Directed Graph
         */

        // Graph created
        DirectedGraph directedGraph = new DirectedGraph();
        System.out.println("===Directed Graph===");
        System.out.println();

        // insert vertices to graph
        for (int i = 0; i < 7; i++)
            directedGraph.insertVertex(dataArray[i]);

        // print all vertices
        System.out.println("Vertices : ");
        directedGraph.printAllVertices();

        // insert edges



        // 1->4
        directedGraph.insertEdge(directedGraph.retrieveVertex(1), directedGraph.retrieveVertex(4));
        // 4->10
        directedGraph.insertEdge(directedGraph.retrieveVertex(4), directedGraph.retrieveVertex(10));
        // 25->10
        directedGraph.insertEdge(directedGraph.retrieveVertex(25), directedGraph.retrieveVertex(10));
        // 9->25
        directedGraph.insertEdge(directedGraph.retrieveVertex(9), directedGraph.retrieveVertex(25));
        // 7->9
        directedGraph.insertEdge(directedGraph.retrieveVertex(7), directedGraph.retrieveVertex(9));
        // 9->4
        directedGraph.insertEdge(directedGraph.retrieveVertex(9), directedGraph.retrieveVertex(4));
        // 4->7
        directedGraph.insertEdge(directedGraph.retrieveVertex(4), directedGraph.retrieveVertex(7));
        // 25->14
        directedGraph.insertEdge(directedGraph.retrieveVertex(25), directedGraph.retrieveVertex(14));

        // print graph after insert edges
        System.out.println("Graph(After insert edges) : ");
        directedGraph.printGraph();

        // delete edge

        // cut 9->4
        directedGraph.deleteEdge(directedGraph.retrieveVertex(9), directedGraph.retrieveVertex(4));

        // print graph after delete edge
        System.out.println("Graph(After delete edges) : ");
        directedGraph.printGraph();

        // delete vertex
        directedGraph.deleteVertex(14);
        System.out.println("Graph(After delete vertex) : ");
        directedGraph.printGraph();

        System.out.println("DFS and BFS");

        // DFS
        directedGraph.depthFirstSearch();
        System.out.println();

        // BFS
        directedGraph.breadthFirstSearch();
        System.out.println();

        // DFS_RECURSION
        directedGraph.depthFirstSearchByRecursion();


        /***
         * Undirected Graph
         */

        // Graph created
        UnDirectedGraph unDirectedGraph = new UnDirectedGraph();
        System.out.println("===UnDirected Graph===");
        System.out.println();

        // insert vertices to graph
        for (int i = 0; i < 7; i++)
            unDirectedGraph.insertVertex(dataArray[i]);

        // print all vertices
        System.out.println("Vertices : ");
        unDirectedGraph.printAllVertices();

        // insert edges

        // 1-4
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(1), unDirectedGraph.retrieveVertex(4));
        // 4-10
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(4), unDirectedGraph.retrieveVertex(10));
        // 10-25
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(10), unDirectedGraph.retrieveVertex(25));
        // 25-9
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(25), unDirectedGraph.retrieveVertex(9));
        // 9-7
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(9), unDirectedGraph.retrieveVertex(7));
        // 9-4
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(9), unDirectedGraph.retrieveVertex(4));
        // 4-7
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(4), unDirectedGraph.retrieveVertex(7));
        // 25-14
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(25), unDirectedGraph.retrieveVertex(14));

        // print graph after insert edges
        System.out.println("Graph(After insert edges) : ");
        unDirectedGraph.printGraph();

        // delete edge

        // cut 9-4
        unDirectedGraph.deleteEdge(unDirectedGraph.retrieveVertex(9),unDirectedGraph.retrieveVertex(4));

        // print graph after delete edge
        System.out.println("Graph(After delete edges) : ");
        unDirectedGraph.printGraph();

        System.out.println("DFS and BFS");

        // DFS
        unDirectedGraph.depthFirstSearch();
        System.out.println();

        // BFS
        unDirectedGraph.breadthFirstSearch();
        System.out.println();


        /***
         * WeightDirected Graph
         */

        // Graph created
        WeightDirectedGraph weightDirectedGraph = new WeightDirectedGraph();
        System.out.println("===UnDirected Graph===");
        System.out.println();

        // insert vertices to graph
        for (int i = 0; i < 7; i++)
            weightDirectedGraph.insertVertex(dataArray[i]);

        // print all vertices
        System.out.println("Vertices : ");
        weightDirectedGraph.printAllVertices();

        // insert edges

        // 1-4
        weightDirectedGraph.insertEdge(weightDirectedGraph.retrieveVertex(1), weightDirectedGraph.retrieveVertex(4), 1);
        // 4-10
        weightDirectedGraph.insertEdge(weightDirectedGraph.retrieveVertex(4), weightDirectedGraph.retrieveVertex(10), 2);
        // 10-25
        weightDirectedGraph.insertEdge(weightDirectedGraph.retrieveVertex(10), weightDirectedGraph.retrieveVertex(25), 3);
        // 25-9
        weightDirectedGraph.insertEdge(weightDirectedGraph.retrieveVertex(25), weightDirectedGraph.retrieveVertex(9), 4);
        // 9-7
        weightDirectedGraph.insertEdge(weightDirectedGraph.retrieveVertex(9), weightDirectedGraph.retrieveVertex(7), 5);
        // 9-4
        weightDirectedGraph.insertEdge(weightDirectedGraph.retrieveVertex(9), weightDirectedGraph.retrieveVertex(4), 6);
        // 4-7
        weightDirectedGraph.insertEdge(weightDirectedGraph.retrieveVertex(4), weightDirectedGraph.retrieveVertex(7), 7);
        // 25-14
        weightDirectedGraph.insertEdge(weightDirectedGraph.retrieveVertex(25), weightDirectedGraph.retrieveVertex(14), 8);

        // print graph after insert edges
        System.out.println("Graph(After insert edges) : ");
        weightDirectedGraph.printGraph();

        // delete edge

        // cut 9-4
        weightDirectedGraph.deleteEdge(weightDirectedGraph.retrieveVertex(9),weightDirectedGraph.retrieveVertex(4));

        // print graph after delete edge
        System.out.println("Graph(After delete edges) : ");
        weightDirectedGraph.printGraph();

        System.out.println("DFS and BFS");

        // DFS
        weightDirectedGraph.depthFirstSearch();
        System.out.println();

        // BFS
        weightDirectedGraph.breadthFirstSearch();
        System.out.println();


        System.out.println();
        System.out.println();
        System.out.println();

        directedGraph.printAdjacencyMatrix();


    }
}
