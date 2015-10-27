package impl;

import base.Data;
import base.Edge;
import base.Vertex;
import java.util.*;

/**
 * Created by TaeWoo on 2015-09-30.
 */
public class DirectedGraph {

    private LinkedList<Vertex> vertices; // �׷��� �ȿ� Vertex��
    private int size; //number of vertieces

    // �׷��� ������
    public DirectedGraph() {

        vertices = new LinkedList<>();
        size = 0;

    }


    //Data �� �߰�
    public boolean insertVertex(Data data) {

        Vertex tmpVertex = new Vertex(data);

        // ���� �׷����� vertex�� 0 ���̸� ù ��°�� �߰�
        if (vertices.size() == 0) {

            vertices.add(tmpVertex);
            size++;
            return true;
        }

        // ���� �Է��ϴ� �����Ͱ� �׷����� ������ �����ͺ��� id�� ũ�ٸ� LinkedList ���� �ڿ� �߰�
        else if (data.compareTo(vertices.getLast().getData()) == 1) {
            vertices.addLast(tmpVertex);
            size++;
            return true;
        }

        // ���� �Է��� vertex�� ó���� �ƴ϶�� ��ũ�� ����Ʈ�� ���� ��
        else {

            int indexOfVertexList = 0;

            // �Է��ϴ� �ڷ��� id���� ũ�� index ���� +1
            while (data.compareTo(vertices.get(indexOfVertexList).getData()) == 1) {

                indexOfVertexList++;

            }

            //�ߺ��� ���� ������ Return
            if (data.compareTo(vertices.get(indexOfVertexList).getData()) == 0) {
                System.out.println("�ߺ��� �� ����");
                return false;
            }

            // �ߺ��� �ƴϸ� �߰�
            else {

                vertices.add(indexOfVertexList, tmpVertex);
                size++;
                return true;
            }


        }

    }

    //key ������ ã�´�.
    public Vertex deleteVertex(int key) {

        //key == id in Data
        int indexOfVertexList = 0;


        // Degree���� 0�� �ƴ� ��쿡�� ������ ����!
       if(retrieveVertex(key).outDegree !=0 || retrieveVertex(key).inDegree !=0){
            System.out.println("Degree is not Zero");
            return null;
        }

        //�ϳ��ϳ� Ȯ���ϸ鼭 ã�´�
        while(indexOfVertexList < vertices.size()) {

            if (vertices.get(indexOfVertexList).getData().id == key) {

                // vertex ���� �� ��ȯ
                size--;
                return vertices.remove(indexOfVertexList);

            }

            indexOfVertexList++;

        }

        //key�� �ش��ϴ°� ���� ���
        return null;

    }

    // ������ vertex�� ������ vertex
    public boolean insertEdge(Vertex vertexFrom, Vertex vertexTo) {

        //�ش��ϴ� vertexFrom�Ǵ� vertexTo�� �׷����� ���ٸ�
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;



            //vertexFrom�� �׷����� �ִٸ�
        else {

            // �߰��� edge, Weight�� 0
            Edge edge = new Edge(vertexTo, 0);

            int indexOfVertexList = 0;

            //���� ó�� �ִ´ٸ�
            if (vertexFrom.getEdges().size() == 0){
                vertexFrom.getEdges().add(edge);
                vertexFrom.outDegree++;
                vertexTo.inDegree++;
                return true;
            }

            else {

                //�ε����� �ø��鼭 edge���� ��
                //index�� vertices���� ���� �Ѿ�� �ʰ�
                //vertexTo�� Data�� �ִ� id�� ���� Vertex���� ũ�ٸ� +1
                while (indexOfVertexList < vertexFrom.getEdges().size()
                       && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getVertex().getData()) == 1
                ) {

                    indexOfVertexList++;

                }

                //�ߺ��� ���� ������ Return
                if (indexOfVertexList < vertexFrom.getEdges().size()
                        && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getVertex().getData()) == 0) {
                    System.out.println("�ߺ��� �� ����");
                    return false;
                }

                // �ߺ��� �ƴϸ� �߰�
                else {

                    //�ش� index���ٰ� Edge�� �߰��Ѵ�.
                    if(indexOfVertexList < vertexFrom.getEdges().size())
                    vertexFrom.getEdges().add(indexOfVertexList, edge);

                    //id�� ���� Ŀ�� ���� �ڿ� �߰��ϴ� ���
                    else
                    vertexFrom.getEdges().addLast(edge);

                    vertexFrom.outDegree++;
                    vertexTo.inDegree++;

                    return true;
                }
            }

        }

    }

    // ������ vertex�� ������ vertex
    public boolean deleteEdge(Vertex vertexFrom, Vertex vertexTo) {

        //�ش��ϴ� vertexFrom�Ǵ� vertexTo�� �׷����� ���ٸ�
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;

            //vertexFrom�� �׷����� �ִٸ�
        else {

            int indexOfVertexList = 0;

            // �ϳ��ϳ� Ȯ���ϸ鼭
            for (indexOfVertexList = 0; indexOfVertexList < vertexFrom.getEdges().size(); indexOfVertexList++) {

                //���� ���ٸ� ��������.
                if (vertexFrom.getEdges().get(indexOfVertexList).getVertex().equals(vertexTo)) {
                    vertexFrom.getEdges().remove(indexOfVertexList);
                    vertexFrom.outDegree--;
                    vertexTo.inDegree--;

                    return true;
                }

            }

            return false;

        }

    }

    // key ������ ã��
    public Vertex retrieveVertex(int key) {
        //key == id in Data

        int indexOfVertexList;

        // �ϳ��ϳ� ã���鼭
        for (indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            // ���� ���� ���� ������ ��ȯ
            if (vertices.get(indexOfVertexList).getData().id == key)
                return vertices.get(indexOfVertexList);

        }

        // ���� ���� ���ٸ� null
        return null;
    }

    //Bad Algorithm
    public void depthFirstSearch() {

        Stack<Vertex> stack = new Stack<>();

        System.out.print("DFS : ");

        // vertex���� processed�� ��� 0���� �ٲ�
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        // �ϳ��ϳ��� vertex���� Ȯ��
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            Vertex currentGraphVertex = vertices.get(indexOfVertexList);

            // processed�� 0�̶��
            if (currentGraphVertex.processed < 2) {
                if (currentGraphVertex.processed < 1) {

                    stack.push(currentGraphVertex);
                    // ���ÿ� �߰�
                    currentGraphVertex.processed = 1;
                }
            }

            // ������ �� ������ �˻�
            while (!stack.isEmpty()) {

                Vertex vertexFromStack = stack.pop();
                //Stack ���� ������ 2�� flag ó�� �׸��� stack�� ������ ������ processed=1��
                if(vertexFromStack.processed != 2) {
                    vertexFromStack.processed = 2;
                    System.out.print(vertexFromStack.getData().id + " ");
                }


                // �Ŀ� vertexFromStack�� �ش��ϴ� ���� ����Ʈ���� ���� stack�� �־��ش�
                for (int edgeIndex = vertexFromStack.getEdges().size()-1;
                     edgeIndex >= 0 ; edgeIndex--) {


                    if (vertexFromStack.getEdges().get(edgeIndex).getVertex().processed != 2) {
                        stack.push(vertexFromStack.getEdges().get(edgeIndex).getVertex());
                        // ���ÿ� �߰�������
                        vertexFromStack.getEdges().get(edgeIndex).getVertex().processed = 1;
                    }

                }

            }

        }

    }

    public void depthFirstSerachByStack(){

        // vertex���� processed�� ��� 0���� �ٲ�
        for (Vertex vertex : vertices) {
            vertex.processed = 0;
        }

        Stack<Vertex> stack = new Stack();

        stack.push(vertices.getFirst());

        while(!stack.empty()){

            Vertex currentVertex = stack.pop();

            if(currentVertex.processed == 1)
                continue;

            currentVertex.processed = 1;
            System.out.print(currentVertex.getData().id + " ");

            for(int index = 0 ; index < currentVertex.getEdges().size() ; index++){

                Edge edge = currentVertex.getEdges().get(currentVertex.getEdges().size() -1 -index);

                stack.push(edge.getVertex());
            }
        }


    }

    public void depthFirstSearchByRecursion(){

        // vertex���� processed�� ��� 0���� �ٲ�
        for (Vertex vertex : vertices) {
            vertex.processed = 0;
        }

        // ��� vertex���� depthVisit�� ���ؼ� ���ȣ��
        for (Vertex currentGraphVertex : vertices) {

            if(currentGraphVertex.processed == 0){
                __depthVisit(currentGraphVertex);
            }
        }
    }

    //For depthFirstSearchByRecursion
    private void __depthVisit(Vertex vertex){

        vertex.processed =1;
        System.out.print(vertex.getData().id + " ");

        // ���� vertex�� edge���� vertex�� ���ؼ� processed=0�̸� ���ȣ���Ѵ�.
        for(Edge edge : vertex.getEdges()){

            if(edge.getVertex().processed == 0){
                __depthVisit(edge.getVertex());
            }
        }


    }

    public void breadthFirstSearch() {

        // ���������� Queue�� LinkedList�� ���� ������� ��� �� �� �ֱ⿡ LinkedList ���
        // enqueue : addLast ���, dequeue : removeFisrt ���
        LinkedList<Vertex> queue = new LinkedList<>();

        System.out.print("BFS : ");

        // vertex���� processed�� ��� 0���� �ٲ�
        for (Vertex vertex : vertices) {

            vertex.processed = 0;

        }

        // �ϳ��ϳ��� vertex���� Ȯ��
        for (Vertex currentGraphVertex : vertices) {

            // processed�� 0�̶��
            if (currentGraphVertex.processed < 2) {
                if (currentGraphVertex.processed < 1) {

                    //queue�� �߰��Ŀ� processed�� 1�� ǥ��
                    queue.addLast(currentGraphVertex);
                    currentGraphVertex.processed = 1;
                }
            }

            // queue�� �� ������ �˻�
            while (!queue.isEmpty()) {

            Vertex vertexFromQueue = queue.removeFirst();
                //Queue ���� ������ 2�� flag ó��
                vertexFromQueue.processed = 2;
                System.out.print(vertexFromQueue.getData().id + " ");

                // �Ŀ� vertexFromQueue�� �ش��ϴ� ���� ����Ʈ����  ���� queue�� �־��ش�
                for (Edge edge : vertexFromQueue.getEdges()) {

                    // queue�� �߰� ������ ���ٸ�
                    if (edge.getVertex().processed == 0) {
                        queue.add(edge.getVertex());

                        // queue�� �߰�������
                        edge.getVertex().processed = 1;
                    }
                }

            }

        }

    }

    public int numberOfVertices() {

        return size;
    }

    public void printAllVertices() {

        // graph�� ���ٸ�
        if (size == 0) {
            System.out.print("Nothing in Graph");
        }

        // graph�� �ִٸ� �ϳ��ϳ� ������ش�.
        else {

            for (Vertex vertex : vertices) {

                System.out.print(vertex.getData().id + " ");

            }
        }

        System.out.println();

    }

    public void printEdges(Vertex vertex) {

        // vertex�� �ִ��� Ȯ��
        if (vertices.indexOf(vertex) == -1) {
            System.out.print("No Such Vertex");

        } else {

            // vertex�� edge�� �ִ��� Ȯ��
            if (vertex.getEdges().size() == 0) {
                System.out.print("No Edges");

            } else {
                // ���
                for (Edge edge : vertex.getEdges()) {

                    System.out.print(edge.getVertex().getData().id + " -> ");

                }
            }
        }

        System.out.println();

    }

    public void printGraph() {

        // graph�� ������
        if (size == 0) {
            System.out.println("Nothing in Graph");
        }

        // graph�� �ִٸ�
        else {

            // vertex
            for (Vertex currentVertex : vertices) {

                System.out.print(currentVertex.getData().id + " : ");

                printEdges(currentVertex);

            }
        }

    }

    public void printAdjacencyMatrix() {

        int [][] adjencyMatrix = makeAdjacencyMatrix();

        for(int i = 0; i<vertices.size() ; i++){
            for(int j = 0 ; j < vertices.size() ; j++){

                System.out.print(adjencyMatrix[i][j] + "  ");

            }

            System.out.println();

        }

    }

    public int[][] makeAdjacencyMatrix(){

        //���� �׷����� ���ٸ�
        if(vertices.size() == 0)
            return null;


        // �׷����� �ִٸ�
        else{

            //Vertex�� id�� �迭�� index�� matching ��Ű�� ���� �ؽø�
            HashMap<Integer, Integer> hashMap = new HashMap<>();

            //�ؽø��� ���ؼ� ���� ��Ŵ
            // key : id of Vertex, value : index of Array
            for(int index=0; index < vertices.size() ; index++){

                hashMap.put(vertices.get(index).getData().id, index);

            }


            int sizeOfMatrix = vertices.size();

            // ��������� ũ�⿡ ���� �����
            int [][] adjencyMatrix = new int[sizeOfMatrix][sizeOfMatrix];

            // ��Ŀ��ٰ� ���� �ִ´�
            // �ϳ��� ��(row)�� ���ؼ�
            for(int row = 0 ; row <sizeOfMatrix; row++){

                //HashMap�� ���ؼ� id�� �ش��ϴ� �迭�� �ε����� ã�Ƽ� 1�� �־��ش�
                for(Edge edge : vertices.get(row).getEdges()){

                    adjencyMatrix[row][hashMap.get(edge.getVertex().getData().id)] = 1;
                    //edge.getVertex().getData().id : ���� Edge�� ������ �ִ� Vetex�� id
                    //hashMap.get(id) : id�� ���ؼ� �迭�� index ���� �����´�.

                }

            }
            return adjencyMatrix;
        }


    }

}
