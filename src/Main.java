import base.Data;
import impl.DirectedGraph;
import impl.UnDirectedGraph;
import impl.WeightDirectedGraph;
import impl.WeightUnDirectedGraph;

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
        System.out.println();

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
        System.out.println();

        // delete edge

        // cut 9->4
        directedGraph.deleteEdge(directedGraph.retrieveVertex(9), directedGraph.retrieveVertex(4));

        // print graph after delete edge
        System.out.println("Graph(After delete edges) : ");
        directedGraph.printGraph();
        System.out.println();

        // delete vertex
        directedGraph.deleteVertex(14);
        System.out.println();

        System.out.println("Graph(After delete vertex) : ");
        directedGraph.printGraph();
        System.out.println();

        System.out.println("DFS and BFS");

        // DFS
        directedGraph.depthFirstSearch();
        System.out.println();

        // BFS
        directedGraph.breadthFirstSearch();
        System.out.println();

        // DFS_RECURSION
        System.out.print("DFS(Recursion) : "); directedGraph.depthFirstSearchByRecursion();
        System.out.println();

        // DFS_STACK
        System.out.print("DFS(Stack) : "); directedGraph.depthFirstSerachByStack();;
        System.out.println();

        System.out.println();

        System.out.println("Adjacency Matrix");
        directedGraph.printAdjacencyMatrix();
        System.out.println();

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
        System.out.println();

        // insert edges

        // 1-4
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(1), unDirectedGraph.retrieveVertex(4));
        // 4-10
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(4), unDirectedGraph.retrieveVertex(10));
        // 25-10
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(25), unDirectedGraph.retrieveVertex(10));
        // 9-25
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(9), unDirectedGraph.retrieveVertex(25));
        // 7-9
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(7), unDirectedGraph.retrieveVertex(9));
        // 9-4
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(9), unDirectedGraph.retrieveVertex(4));
        // 4-7
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(4), unDirectedGraph.retrieveVertex(7));
        // 25-14
        unDirectedGraph.insertEdge(unDirectedGraph.retrieveVertex(25), unDirectedGraph.retrieveVertex(14));

        // print graph after insert edges
        System.out.println("Graph(After insert edges) : ");
        unDirectedGraph.printGraph();
        System.out.println();

        // delete edge

        // cut 9-4
        unDirectedGraph.deleteEdge(unDirectedGraph.retrieveVertex(9), unDirectedGraph.retrieveVertex(4));

        // print graph after delete edge
        System.out.println("Graph(After delete edges) : ");
        unDirectedGraph.printGraph();
        System.out.println();

        // delete vertex
        unDirectedGraph.deleteVertex(14);
        System.out.println();

        System.out.println("Graph(After delete vertex) : ");
        unDirectedGraph.printGraph();
        System.out.println();

        System.out.println("DFS and BFS");

        // DFS
        unDirectedGraph.depthFirstSearch();
        System.out.println();

        // BFS
        unDirectedGraph.breadthFirstSearch();
        System.out.println();

        // DFS_RECURSION
        System.out.print("DFS(Recursion) : "); unDirectedGraph.depthFirstSearchByRecursion();
        System.out.println();

        // DFS_STACK
        System.out.print("DFS(Stack) : "); unDirectedGraph.depthFirstSerachByStack();;
        System.out.println();

        System.out.println();

        System.out.println("Adjacency Matrix");
        unDirectedGraph.printAdjacencyMatrix();
        System.out.println();

        /***
         * WeightDirected Graph
         */

        // Graph created
        WeightDirectedGraph weightDirectedGraph = new WeightDirectedGraph();
        System.out.println("===WeightDirected Graph===");
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
        System.out.println();

        // delete edge

        // cut 9->4
        weightDirectedGraph.deleteEdge(weightDirectedGraph.retrieveVertex(9), weightDirectedGraph.retrieveVertex(4));

        // print graph after delete edge
        System.out.println("Graph(After delete edges) : ");
        weightDirectedGraph.printGraph();
        System.out.println();

        // delete vertex
        weightDirectedGraph.deleteVertex(14);
        System.out.println();

        System.out.println("Graph(After delete vertex) : ");
        weightDirectedGraph.printGraph();
        System.out.println();

        System.out.println("DFS and BFS");

        // DFS
        weightDirectedGraph.depthFirstSearch();
        System.out.println();

        // BFS
        weightDirectedGraph.breadthFirstSearch();
        System.out.println();

        // DFS_RECURSION
        System.out.print("DFS(Recursion) : "); weightDirectedGraph.depthFirstSearchByRecursion();
        System.out.println();

        // DFS_STACK
        System.out.print("DFS(Stack) : "); weightDirectedGraph.depthFirstSerachByStack();;
        System.out.println();

        System.out.println();

        System.out.println("Adjacency Matrix");
        weightDirectedGraph.printAdjacencyMatrix();
        System.out.println();

        System.out.println("Dijkstra");
        weightDirectedGraph.ShortestPathByDijkstra(
                weightDirectedGraph.retrieveVertex(4),weightDirectedGraph.retrieveVertex(9)
        ).printRoutesAndDistance();
        System.out.println();

        System.out.println("Bellman_Ford");
        weightDirectedGraph.ShortestPathByBellman_Ford(
                weightDirectedGraph.retrieveVertex(4),weightDirectedGraph.retrieveVertex(9)
        ).printRoutesAndDistance();
        System.out.println();

        System.out.println("Floyd_Warshall");
        weightDirectedGraph.ShortestPathByFloyd_Warshall(
                weightDirectedGraph.retrieveVertex(4),weightDirectedGraph.retrieveVertex(9)
                ).printRoutesAndDistance();
        System.out.println();



        /***
         * Test Graph
         */

        // Graph created
        WeightUnDirectedGraph weightUnDirectedGraph = new WeightUnDirectedGraph();
        System.out.println("===Test Graph===");
        System.out.println();

        // insert vertices to graph
        for (int i = 0; i < 7; i++)
            weightUnDirectedGraph.insertVertex(dataArray[i]);

        // print all vertices
        System.out.println("Vertices : ");
        weightUnDirectedGraph.printAllVertices();

        // insert edges

        // 1-4
        weightUnDirectedGraph.insertEdge(weightUnDirectedGraph.retrieveVertex(1), weightUnDirectedGraph.retrieveVertex(4), 1);
        // 4-10
        weightUnDirectedGraph.insertEdge(weightUnDirectedGraph.retrieveVertex(4), weightUnDirectedGraph.retrieveVertex(10), 2);
        // 10-25
        weightUnDirectedGraph.insertEdge(weightUnDirectedGraph.retrieveVertex(10), weightUnDirectedGraph.retrieveVertex(25), 3);
        // 25-9
        weightUnDirectedGraph.insertEdge(weightUnDirectedGraph.retrieveVertex(25), weightUnDirectedGraph.retrieveVertex(9), 4);
        // 9-7
        weightUnDirectedGraph.insertEdge(weightUnDirectedGraph.retrieveVertex(9), weightUnDirectedGraph.retrieveVertex(7), 5);
        // 9-4
        weightUnDirectedGraph.insertEdge(weightUnDirectedGraph.retrieveVertex(9), weightUnDirectedGraph.retrieveVertex(4), 6);
        // 4-7
        weightUnDirectedGraph.insertEdge(weightUnDirectedGraph.retrieveVertex(4), weightUnDirectedGraph.retrieveVertex(7), 7);
        // 25-14
        weightUnDirectedGraph.insertEdge(weightUnDirectedGraph.retrieveVertex(25), weightUnDirectedGraph.retrieveVertex(14), 8);

        // print graph after insert edges
        System.out.println("Graph(After insert edges) : ");
        weightUnDirectedGraph.printGraph();
        System.out.println();

        // delete edge

        // cut 9-4
        weightUnDirectedGraph.deleteEdge(weightUnDirectedGraph.retrieveVertex(9), weightUnDirectedGraph.retrieveVertex(4));


        // print graph after delete edge
        System.out.println("Graph(After delete edges) : ");
        weightUnDirectedGraph.printGraph();
        System.out.println();

        // delete vertex
        weightUnDirectedGraph.deleteVertex(14);
        System.out.println();

        System.out.println("Graph(After delete vertex) : ");
        weightUnDirectedGraph.printGraph();
        System.out.println();

        System.out.println("MSTByKruskal");
        weightUnDirectedGraph.MSTByKruskal().printGraph();
        System.out.println();

        System.out.println("MSTByPrim");
        weightUnDirectedGraph.MSTByPrim().printGraph();
        System.out.println();

        System.out.println("MSTBySollin");
        weightUnDirectedGraph.MSTBySollin().printGraph();
        System.out.println();

    }
}
