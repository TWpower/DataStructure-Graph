package impl;

import base.Data;
import base.Vertex;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by TaeWoo on 2015-10-15.
 */
public class UnDirectedGraph {



    private LinkedList<Vertex> vertices;
    private int size; //number of vertieces

    // ????? ??????
    public UnDirectedGraph() {

        vertices = new LinkedList<>();
        size = 0;

    }


    //Data ?? ???
    public boolean insertVertex(Data data) {

        Vertex tmpVertex = new Vertex(data);

        // ???? ??????? vertex?? 0 ????? ? ?????? ???
        if (vertices.size() == 0) {

            vertices.add(tmpVertex);
            size++;
            return true;
        }

        // ???? ?????? ???????? ?????? ??? ??? ??? ???
        else if (data.compareTo(vertices.getLast().getData()) == 1) {
            vertices.addLast(tmpVertex);
            size++;
            return true;
        }

        // ???? ????? vertex?? ????? ????? ????? ??????? ???? ??
        else {

            int indexOfVertexList = 0;

            // ?????? ????? id???? ??? index ???? +1
            while (data.compareTo(vertices.get(indexOfVertexList).getData()) == 1) {

                indexOfVertexList++;

            }

            //????? ???? ?????? Return
            if (data.compareTo(vertices.get(indexOfVertexList).getData()) == 0) {
                System.out.println("????? ?? ????");
                return false;
            }

            // ????? ???? ???
            else {

                vertices.add(indexOfVertexList, tmpVertex);
                size++;
                return true;
            }


        }

    }

    //key ?????? ?????.
    public Vertex deleteVertex(int key) {
        //key == id in Data

        int indexOfVertexList = 0;


        while (indexOfVertexList < vertices.size()) {

            if (vertices.get(indexOfVertexList).getData().id == key) {

                // vertex ???? ?? ???
                size--;
                return vertices.remove(indexOfVertexList);

            }

            indexOfVertexList++;

        }

        //key?? ???????? ???? ???
        return null;

    }



    //
    private boolean __insertEdge(Vertex vertexFrom, Vertex vertexTo) {

        //?????? vertexFrom??? vertexTo?? ??????? ?????
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;

            //vertexFrom?? ??????? ????
        else {

            int indexOfVertexList = 0;

            //???? ??? ??????
            if (vertexFrom.getEdges().size() == 0){
                vertexFrom.getEdges().add(vertexTo);
                vertexFrom.outDegree++;
                vertexTo.inDegree++;
                return true;
            }

            else {
                //???????? ?????? edge???? ??
                while (indexOfVertexList < vertexFrom.getEdges().size()
                        && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getData()) == 1
                        ) {

                    indexOfVertexList++;

                }

                //????? ???? ?????? Return
                if (indexOfVertexList < vertexFrom.getEdges().size()
                        && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getData()) == 0) {
                    System.out.println("????? ?? ????");
                    return false;
                }

                // ????? ???? ???
                else {

                    if(indexOfVertexList < vertexFrom.getEdges().size())
                        vertexFrom.getEdges().add(indexOfVertexList, vertexTo);

                    else
                        vertexFrom.getEdges().addLast(vertexTo);

                    vertexFrom.outDegree++;
                    vertexTo.inDegree++;

                    return true;
                }
            }

        }


    }

    // ???? ???
    public boolean insertEdge(Vertex vertexOne, Vertex vertexTwo){

       return  (__insertEdge(vertexOne,vertexTwo) && __insertEdge(vertexTwo,vertexOne));
    }

    //
    private boolean __deleteEdge(Vertex vertexFrom, Vertex vertexTo) {

        //?????? vertexFrom??? vertexTo?? ??????? ?????
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;

            //vertexFrom?? ??????? ????
        else {

            int indexOfVertexList = 0;

            // ?????? ??????
            for (indexOfVertexList = 0; indexOfVertexList < vertexFrom.getEdges().size(); indexOfVertexList++) {

                //???? ????? ????????.
                if (vertexFrom.getEdges().get(indexOfVertexList).equals(vertexTo)) {
                    vertexFrom.getEdges().remove(indexOfVertexList);
                    vertexFrom.outDegree--;
                    vertexTo.inDegree--;

                    return true;
                }

            }

            return false;

        }

    }

    //???? ????
    public boolean deleteEdge(Vertex vertexOne, Vertex vertexTwo){

        return (__deleteEdge(vertexOne,vertexTwo) && __deleteEdge(vertexTwo,vertexOne));
    }

    // key ?????? ???
    public Vertex retrieveVertex(int key) {
        //key == id in Data

        int indexOfVertexList;

        // ?????? ?????
        for (indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            // ???? ???? ???? ?????? ???
            if (vertices.get(indexOfVertexList).getData().id == key)
                return vertices.get(indexOfVertexList);

        }

        // ???? ???? ????? null
        return null;
    }

    public void depthFirstSearch() {

        Stack<Vertex> stack = new Stack<>();

        System.out.print("DFS : ");

        // vertex???? processed?? ??? 0???? ???
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        // ???????? vertex???? ???
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            Vertex currentGraphVertex = vertices.get(indexOfVertexList);

            // processed?? 0????
            if (currentGraphVertex.processed < 2) {
                if (currentGraphVertex.processed < 1) {

                    stack.push(currentGraphVertex);
                    // ????? ???
                    currentGraphVertex.processed = 1;
                }
            }

            // ?????? ?? ?????? ???
            while (!stack.isEmpty()) {

                Vertex vertexFromStack = stack.pop();
                //Stack ???? ?????? 2?? flag ???
                if(vertexFromStack.processed != 2) {
                    vertexFromStack.processed = 2;
                    System.out.print(vertexFromStack.getData().id + " ");
                }


                // ???? vertexFromStack?? ?????? ???? ????????? ???? stack?? ??????
                for (int edgeIndex = vertexFromStack.getEdges().size()-1;
                     edgeIndex >= 0 ; edgeIndex--) {

                    // ?????? ???? ????? ???
                    if (vertexFromStack.getEdges().get(edgeIndex).processed != 2) {
                        stack.push(vertexFromStack.getEdges().get(edgeIndex));
                        // ????? ?????????
                        vertexFromStack.getEdges().get(edgeIndex).processed = 1;
                    }
                }

            }

        }

    }

    public void breadthFirstSearch() {

        // ?????????? Queue?? LinkedList?? ???? ??????? ??? ?? ?? ??? LinkedList ???
        LinkedList<Vertex> queue = new LinkedList<>();

        System.out.print("BFS : ");

        // vertex???? processed?? ??? 0???? ???
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        // ???????? vertex???? ???
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            Vertex currentGraphVertex = vertices.get(indexOfVertexList);

            // processed?? 0????
            if (currentGraphVertex.processed < 2) {
                if (currentGraphVertex.processed < 1) {

                    //queue?? ???
                    queue.add(currentGraphVertex);
                    currentGraphVertex.processed = 1;
                }
            }

            // queue?? ?? ?????? ???
            while (!queue.isEmpty()) {

                Vertex vertexFromQueue = queue.removeFirst();
                //Stack ???? ?????? 2?? flag ???
                vertexFromQueue.processed = 2;
                System.out.print(vertexFromQueue.getData().id + " ");

                // ???? vertexFromQueue?? ?????? ???? ????????? ???? queue?? ??????
                for (int edgeIndex = 0; edgeIndex < vertexFromQueue.getEdges().size(); edgeIndex++) {

                    // queue?? ??? ?????? ?????
                    if (vertexFromQueue.getEdges().get(edgeIndex).processed == 0)
                        queue.add(vertexFromQueue.getEdges().get(edgeIndex));

                    // queue?? ?????????
                    vertexFromQueue.getEdges().get(edgeIndex).processed = 1;

                }

            }

        }

    }

    public int numberOfVertices() {

        return size;
    }

    public void printAllVertices() {

        // graph?? ?????
        if (size == 0) {
            System.out.print("Nothing in Graph");
        }

        // graph?? ?????
        else {

            for (int index = 0; index < vertices.size(); index++) {

                System.out.print(vertices.get(index).getData().id + " ");

            }
        }

        System.out.println();

    }

    public void printEdges(Vertex vertex) {

        // vertex?? ????? ???
        if (vertices.indexOf(vertex) == -1) {
            System.out.print("No Such Vertex");
        } else {

            // vertex?? edge?? ????? ???
            if (vertex.getEdges().size() == 0) {
                System.out.print("No Edges");
            } else {
                // ???
                for (int index = 0; index < vertex.getEdges().size(); index++) {

                    System.out.print(vertex.getEdges().get(index).getData().id + " -> ");

                }
            }
        }

        System.out.println();

    }

    public void printGraph() {

        // graph?? ??????
        if (size == 0) {
            System.out.println("Nothing in Graph");
        }

        // graph?? ????
        else {

            // vertex
            for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++) {

                Vertex currentVertex = vertices.get(vertexIndex);

                System.out.print(currentVertex.getData().id + " : ");

                printEdges(currentVertex);

            }
        }

    }


}


