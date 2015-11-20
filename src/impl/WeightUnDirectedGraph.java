package impl;

import base.Data;
import base.Distance;
import base.Edge;
import base.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by TaeWoo on 2015-10-15.
 */
public class WeightUnDirectedGraph {


    private LinkedList<Vertex> vertices;
    private int size; //number of vertieces

    public WeightUnDirectedGraph() {

        vertices = new LinkedList<>();
        size = 0;

    }


    public boolean insertVertex(Data data) {

        Vertex tmpVertex = new Vertex(data);

        if (vertices.size() == 0) {

            vertices.add(tmpVertex);
            size++;
            return true;
        }

        else if (data.compareTo(vertices.getLast().getData()) == 1) {
            vertices.addLast(tmpVertex);
            size++;
            return true;
        }

        else {

            int indexOfVertexList = 0;

            while (data.compareTo(vertices.get(indexOfVertexList).getData()) == 1) {

                indexOfVertexList++;

            }

            if (data.compareTo(vertices.get(indexOfVertexList).getData()) == 0) {
                System.out.println("?????????????? ?????? ????????????");
                return false;
            }

            else {

                vertices.add(indexOfVertexList, tmpVertex);
                size++;
                return true;
            }


        }

    }

    public Vertex deleteVertex(int key) {

        //key == id in Data
        int indexOfVertexList = 0;


        if (retrieveVertex(key).outDegree != 0 || retrieveVertex(key).inDegree != 0) {
            System.out.println("Degree is not Zero");
            return null;
        }

        while (indexOfVertexList < vertices.size()) {

            if (vertices.get(indexOfVertexList).getData().id == key) {

                size--;
                return vertices.remove(indexOfVertexList);

            }

            indexOfVertexList++;

        }

        return null;

    }

    private boolean __insertEdge(Vertex vertexFrom, Vertex vertexTo, int weight) {

        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;


        else {

            Edge edge = new Edge(vertexTo, vertexFrom, weight);

            int indexOfVertexList = 0;

            if (vertexFrom.getEdges().size() == 0) {
                vertexFrom.getEdges().add(edge);
                vertexFrom.outDegree++;
                vertexTo.inDegree++;
                return true;
            } else {


                while (indexOfVertexList < vertexFrom.getEdges().size()
                        && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getToVertex().getData()) == 1
                        ) {

                    indexOfVertexList++;

                }

                if (indexOfVertexList < vertexFrom.getEdges().size()
                        && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getToVertex().getData()) == 0) {
                    System.out.println("?????????????? ?????? ????????????");
                    return false;
                }

                else {

                    if (indexOfVertexList < vertexFrom.getEdges().size())
                        vertexFrom.getEdges().add(indexOfVertexList, edge);

                    else
                        vertexFrom.getEdges().addLast(edge);

                    vertexFrom.outDegree++;
                    vertexTo.inDegree++;

                    return true;
                }
            }

        }


    }

    // Edge????????
    public boolean insertEdge(Vertex vertexOne, Vertex vertexTwo, int weight) {

        return (__insertEdge(vertexOne, vertexTwo, weight) && __insertEdge(vertexTwo, vertexOne, weight));
    }

    //
    private boolean __deleteEdge(Vertex vertexFrom, Vertex vertexTo) {

        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;

        else {

            for (Edge edge : vertexFrom.getEdges()) {

                if (edge.getToVertex().equals(vertexTo)) {
                    vertexFrom.getEdges().remove(edge);
                    vertexFrom.outDegree--;
                    vertexTo.inDegree--;

                    return true;
                }

            }

            return false;

        }


    }

    public boolean deleteEdge(Vertex vertexOne, Vertex vertexTwo) {

        return (__deleteEdge(vertexOne, vertexTwo) && __deleteEdge(vertexTwo, vertexOne));

    }

    public Vertex retrieveVertex(int key) {
        //key == id in Data

        int indexOfVertexList;

        for (indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            if (vertices.get(indexOfVertexList).getData().id == key)
                return vertices.get(indexOfVertexList);

        }

        return null;
    }

    public void depthFirstSearch() {

        Stack<Vertex> stack = new Stack<>();

        System.out.print("DFS : ");

        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            Vertex currentGraphVertex = vertices.get(indexOfVertexList);

            if (currentGraphVertex.processed < 2) {
                if (currentGraphVertex.processed < 1) {

                    stack.push(currentGraphVertex);
                    currentGraphVertex.processed = 1;
                }
            }

            while (!stack.isEmpty()) {

                Vertex vertexFromStack = stack.pop();
                if (vertexFromStack.processed != 2) {
                    vertexFromStack.processed = 2;
                    System.out.print(vertexFromStack.getData().id + " ");
                }


                for (int edgeIndex = vertexFromStack.getEdges().size() - 1;
                     edgeIndex >= 0; edgeIndex--) {

                    if (vertexFromStack.getEdges().get(edgeIndex).getToVertex().processed != 2) {
                        stack.push(vertexFromStack.getEdges().get(edgeIndex).getToVertex());
                        vertexFromStack.getEdges().get(edgeIndex).getToVertex().processed = 1;
                    }
                }

            }

        }

    }

    public void depthFirstSerachByStack() {

        for (Vertex vertex : vertices) {
            vertex.processed = 0;
        }

        Stack<Vertex> stack = new Stack();

        stack.push(vertices.getFirst());

        while (!stack.empty()) {

            Vertex currentVertex = stack.pop();

            if (currentVertex.processed == 1)
                continue;

            currentVertex.processed = 1;
            System.out.print(currentVertex.getData().id + " ");

            for (int index = 0; index < currentVertex.getEdges().size(); index++) {

                Edge edge = currentVertex.getEdges().get(currentVertex.getEdges().size() - 1 - index);

                stack.push(edge.getToVertex());
            }
        }


    }

    public void depthFirstSearchByRecursion() {

        for (Vertex vertex : vertices) {
            vertex.processed = 0;
        }

        for (Vertex currentGraphVertex : vertices) {

            if (currentGraphVertex.processed == 0) {
                __depthVisit(currentGraphVertex);
            }
        }
    }

    //For depthFirstSearchByRecursion
    private void __depthVisit(Vertex vertex) {

        vertex.processed = 1;
        System.out.print(vertex.getData().id + " ");

        for (Edge edge : vertex.getEdges()) {

            if (edge.getToVertex().processed == 0) {
                __depthVisit(edge.getToVertex());
            }
        }


    }

    public void breadthFirstSearch() {

        LinkedList<Vertex> queue = new LinkedList<>();

        System.out.print("BFS : ");

        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            Vertex currentGraphVertex = vertices.get(indexOfVertexList);

            if (currentGraphVertex.processed < 2) {
                if (currentGraphVertex.processed < 1) {

                    queue.add(currentGraphVertex);
                    currentGraphVertex.processed = 1;
                }
            }

            while (!queue.isEmpty()) {

                Vertex vertexFromQueue = queue.removeFirst();
                vertexFromQueue.processed = 2;
                System.out.print(vertexFromQueue.getData().id + " ");

                for (int edgeIndex = 0; edgeIndex < vertexFromQueue.getEdges().size(); edgeIndex++) {

                    if (vertexFromQueue.getEdges().get(edgeIndex).getToVertex().processed == 0)
                        queue.add(vertexFromQueue.getEdges().get(edgeIndex).getToVertex());

                    vertexFromQueue.getEdges().get(edgeIndex).getToVertex().processed = 1;

                }

            }

        }

    }

    public int numberOfVertices() {

        return size;
    }

    public void printAllVertices() {

        if (size == 0) {
            System.out.print("Nothing in Graph");
        }

        else {

            for (int index = 0; index < vertices.size(); index++) {

                System.out.print(vertices.get(index).getData().id + " ");

            }
        }

        System.out.println();

    }

    public void printEdges(Vertex vertex) {

        if (vertices.indexOf(vertex) == -1) {
            System.out.print("No Such Vertex");
        } else {

            if (vertex.getEdges().size() == 0) {
                System.out.print("No Edges");
            } else {

                for (int index = 0; index < vertex.getEdges().size(); index++) {

                    System.out.print(vertex.getEdges().get(index).getToVertex().getData().id + " -> ");

                }
            }
        }

        System.out.println();

    }

    public void printGraph() {

        if (size == 0) {
            System.out.println("Nothing in Graph");
        }

        else {

            // vertex
            for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++) {

                Vertex currentVertex = vertices.get(vertexIndex);

                System.out.print(currentVertex.getData().id + " : ");

                printEdges(currentVertex);

            }
        }

    }

    public void printAdjacencyMatrix() {

        int[][] adjencyMatrix = makeAdjacencyMatrix();

        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {

                System.out.print(adjencyMatrix[i][j] + "  ");

            }

            System.out.println();

        }

    }

    public int[][] makeAdjacencyMatrix() {

        if (vertices.size() == 0)
            return null;


        else {

            HashMap<Integer, Integer> hashMap = new HashMap<>();

            // key : id of Vertex, value : index of Array
            for (int index = 0; index < vertices.size(); index++) {

                hashMap.put(vertices.get(index).getData().id, index);

            }


            int sizeOfMatrix = vertices.size();

            int[][] adjencyMatrix = new int[sizeOfMatrix][sizeOfMatrix];

            for (int row = 0; row < sizeOfMatrix; row++) {

                for (Edge edge : vertices.get(row).getEdges()) {

                    adjencyMatrix[row][hashMap.get(edge.getToVertex().getData().id)] = 1;
                }

            }
            return adjencyMatrix;
        }


    }

    // Point : Edge
    // 1. Put edges to the edgeList(sort by weight) (frankly it is Minimum Heap)
    // 2. remove edge element from heap and add to the graph
    // 3. if there is a cycle then remove that edge(which we inserted at step 2)
    public WeightUnDirectedGraph MSTByKruskal() {


        if (vertices.size() == 0)
            return null;

        WeightUnDirectedGraph tmpGraph = new WeightUnDirectedGraph();

        // Use this as a Heap
        LinkedList<Edge> edgeList = new LinkedList<>();

        // Put edges to the edgeList(sort by weight)
        for (Vertex currentVertex : vertices) {
            for (Edge currentEdge : currentVertex.getEdges()) {
                insertEdgeToList(edgeList,currentEdge);
            }
        }

        // Make new Graph(Tree) by using edgeList
        while (!edgeList.isEmpty()) {

            Edge edge = edgeList.removeFirst();

            // Make new vertex for tmpGraph
            if (tmpGraph.retrieveVertex(edge.getFromVertex().getData().id) == null)
                tmpGraph.insertVertex(new Data(edge.getFromVertex().getData().id));

            if (tmpGraph.retrieveVertex(edge.getToVertex().getData().id) == null)
                tmpGraph.insertVertex(new Data(edge.getToVertex().getData().id));

            // insert Edge and Weight between vertices
            tmpGraph.insertEdge(
                    tmpGraph.retrieveVertex(edge.getFromVertex().getData().id)
                    , tmpGraph.retrieveVertex(edge.getToVertex().getData().id)
                    , edge.getWeight());


            // Check if there is a cycle
            if (tmpGraph.hasCycle()) {
                // if there is a cycle
                // then delete edge
                tmpGraph.deleteEdge(
                        tmpGraph.retrieveVertex(edge.getFromVertex().getData().id)
                        , tmpGraph.retrieveVertex(edge.getToVertex().getData().id));
                System.out.println("Cycle Exist");
            }

        }


        return tmpGraph;
    }

    // Point : Vertex
    // 1. Remove Edge from PQ
    // 2. check edge's toVertex is in Graph
    // 3. if toVertex is not exist then add that vertex to the graph and edges
    // 3-1. if to Vertex is exist in Graph then ignore this Edge
    public WeightUnDirectedGraph MSTByPrim() {

        if (vertices.size() == 0)
            return null;

        WeightUnDirectedGraph tmpGraph = new WeightUnDirectedGraph();

        LinkedList<Edge> edgeList = new LinkedList<>();

        // Insert First Vertex to Graph
        tmpGraph.insertVertex(new Data(vertices.getFirst().getData().id));

        // Get First tmpGraph Vertex
        Vertex currentVertex = vertices.getFirst();
        Vertex nextVertex;

        //Insert First Vertex Edges
        for(Edge edge : currentVertex.getEdges()){

            insertEdgeToList(edgeList, edge);

        }

        // keep make graph(actually tree) until edgeList size get to 0
        while (edgeList.size() != 0) {

            // First One is lowest Weight Edge
            Edge edge = edgeList.removeFirst();

            // Vertex Exist => Already lowest weight path exist
            if(tmpGraph.retrieveVertex(edge.getToVertex().getData().id) != null)
                continue;

            // insert Vertex
            tmpGraph.insertVertex(new Data(edge.getToVertex().getData().id));

            nextVertex = edge.getToVertex();

            // insert edge between two vertices
            tmpGraph.insertEdge(tmpGraph.retrieveVertex(edge.getFromVertex().getData().id)
                    , tmpGraph.retrieveVertex(edge.getToVertex().getData().id)
                    , edge.getWeight());

            // insert next Vertex's Edges
            for(Edge tmpEdge : nextVertex.getEdges()){
                insertEdgeToList(edgeList, tmpEdge);
            }
        }

        return tmpGraph;
    }

    // Point : Forest to Tree
    //
    // While T has more than one component
    //  For each component C of T :
    //   Begin with an empty set of edges S
    //   For each vertex v in C :
    //    Find the cheapest edge from v to a vertex outside of C, and add it to S
    //   Add the cheapest edge in S to T
    public WeightUnDirectedGraph MSTBySollin() {

       LinkedList<WeightUnDirectedGraph> graphLinkedList = new LinkedList<>();

        for(int i = 0 ; i < vertices.size() ; i ++){
            graphLinkedList.add(new WeightUnDirectedGraph());
        }

        // 각각의 vertex에 대해서 graph라고 만듦
        for(int i = 0 ; i < vertices.size() ; i ++){
            graphLinkedList.get(i).insertVertex(new Data(vertices.get(i).getData().id));
        }

         int index = 0;
        WeightUnDirectedGraph currentGraph;

         // While T has more than one component:
        while(graphLinkedList.size() > 1){

            if(index >= graphLinkedList.size())
                index = 0;


            // For each component C of T:
            currentGraph = graphLinkedList.get(index);

                // Begin with an empty set of edges S
                LinkedList<Edge> edgeList = new LinkedList<>();

                // For each vertex v in C:
                for(Vertex currentVertex : currentGraph.vertices){

                    // Find the cheapest edge from v to a vertex outside of C, and add it to S

                    // 현재 큰 그래프에서 찾음
                    Vertex vertex = retrieveVertex(currentVertex.getData().id);

                    for(Edge edge : vertex.getEdges()){

                        if(currentGraph.retrieveVertex(edge.getToVertex().getData().id) == null)
                            insertEdgeToList(edgeList, edge);

                    }

                }

                int graphIndex = findGraphIndex(graphLinkedList, edgeList.getFirst().getToVertex());

                // Add the cheapest edge in S to T

                if(mergeGraph(currentGraph, graphLinkedList.get(graphIndex), edgeList.removeFirst()))
                    graphLinkedList.remove(graphIndex);

            index++;


        }

        return graphLinkedList.getFirst();
    }

    // Similar To Heap
    // insert Edge to the edgeList, sorted by weight, do note insert same edge in the list
    private boolean insertEdgeToList(LinkedList<Edge> edgeList, Edge edge) {

        // index for list
        int indexOfVertexList = 0;
        // flag for SameEdge Checking
        boolean hasSameEdge = false;

        // First
        if (edgeList.size() == 0) {
            edgeList.add(edge);
        }

        //Last
        if (edge.getWeight() >= edgeList.getLast().getWeight()
                && !sameEdge(edge, edgeList.getLast())) {
            edgeList.add(edge);
        }

        // find position of edge in edgeList(depends on weight)
        while (indexOfVertexList < edgeList.size() &&
                edgeList.get(indexOfVertexList).getWeight() <= edge.getWeight()
                ) {

            // same weight and same edge => Same Edge -> do not add
            if (edgeList.get(indexOfVertexList).getWeight() == edge.getWeight()
                    &&
                    sameEdge(edge, edgeList.get(indexOfVertexList))) {
                hasSameEdge = true;
                break;
            }

            indexOfVertexList++;
        }

        // if edge is already in the edgeList then return false
        if(hasSameEdge) {
            return false;
        }

        edgeList.add(indexOfVertexList, edge);
        return true;

    }

    //check if two edges are same
    private boolean sameEdge(Edge edgeOne, Edge edgeTwo) {

        // same weight and same pair of vertices => Same Edge

        // check weight first
        // if weight is different then different edge
        if (edgeOne.getWeight() != edgeTwo.getWeight())
            return false;

        // v1 Edge1 v2
        // v1 Edge2 v2
        // weight checked before
        if (edgeOne.getFromVertex().equals(edgeTwo.getFromVertex())
                && edgeOne.getToVertex().equals(edgeTwo.getToVertex()))
            return true;

            // v1 Edge1 v2
            // v2 Edge2 v1
        else if (edgeOne.getFromVertex().equals(edgeTwo.getToVertex())
                && edgeOne.getToVertex().equals(edgeTwo.getFromVertex()))
            return true;

        else
            return false;

    }

    // find which graph has specific vertex in graphLinkedList
    private int findGraphIndex(LinkedList<WeightUnDirectedGraph> graphLinkedList ,Vertex vertex){

        int graphIndex =0;

        for(;graphIndex < graphLinkedList.size() ; graphIndex++){

            // vertex is exist
          if(graphLinkedList.get(graphIndex).retrieveVertex(vertex.getData().id)  != null)
              break;
        }

        // return graph index in graphLinkedList
        return graphIndex;
    }

    // merge toGraph and fromGraph, and put edge to the toGraph
    private boolean mergeGraph(WeightUnDirectedGraph toGraph, WeightUnDirectedGraph fromGraph, Edge edge){

        // Add "fromGraph" To "toGraph"
        // Both graphs are disjoint!!
        LinkedList<Edge> edgeList = new LinkedList<>();

        // move all vertices from fromGraph to toGraph
        for(int i = 0 ; i < fromGraph.size ; i ++){

            if(!toGraph.vertices.contains(fromGraph.vertices.get(i)))
            toGraph.insertVertex(new Data(fromGraph.vertices.get(i).getData().id));

            // add all edges from fromGraph to toGraph
            for(Edge tmpEdge : fromGraph.vertices.get(i).getEdges()){

                insertEdgeToList(edgeList, tmpEdge);

            }

        } // add all vertices to toGraph

        // insert all edges to the toGraph
        for(Edge tmpEdge : edgeList){

            toGraph.insertEdge(
                    toGraph.retrieveVertex(tmpEdge.getFromVertex().getData().id)
                    ,toGraph.retrieveVertex(tmpEdge.getToVertex().getData().id)
                    ,tmpEdge.getWeight());

        }

        // insert "edge"(see above parameter) to the toGraph
        toGraph.insertEdge(toGraph.retrieveVertex(edge.getFromVertex().getData().id)
                ,toGraph.retrieveVertex(edge.getToVertex().getData().id)
                ,edge.getWeight());

        return true;
    }

    // Using DFS Concept
    // Check if graph has cycle
    private boolean hasCycle() {

        // set processed to 0
        for (Vertex vertex : vertices) {
            vertex.processed = 0;
        }

        // Stack for parent and vertices
        Stack<Vertex> stack = new Stack();
        Stack<Vertex> parentStack = new Stack<>();

        stack.push(vertices.getFirst());

        Vertex parentVertex = null; // first it is null

        while (!stack.empty()) {

            Vertex currentVertex = stack.pop();

            if (currentVertex.processed == 1)
                continue;

            currentVertex.processed = 1;

            for (Edge edge : currentVertex.getEdges()) {

                // toVertex == parentVertex && toVertex == 1
                // is is processed and not a parent Vertex => already visited => Cycle!
                if (!edge.getToVertex().equals(parentVertex)
                        && edge.getToVertex().processed == 1)
                    return true;

                //  push to stack if it is not a parent Vertex
                if (!edge.getToVertex().equals(parentVertex)) {
                    stack.push(edge.getToVertex());
                    parentStack.push(currentVertex);
                }


            }

            if(parentStack.size() !=0)
                parentVertex = parentStack.pop();
            else
                parentVertex = null;

        }

        return false;
    }


}


