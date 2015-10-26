package impl;

import base.Data;
import base.Edge;
import base.Vertex;
import base.VertexWithEdge;

import java.util.*;

/**
 * Created by TaeWoo on 2015-09-30.
 */
public class DirectedGraph {

    private LinkedList<VertexWithEdge> vertices; // �׷��� �ȿ� vertex��
    private int size; //number of vertieces

    // �׷��� ������
    public DirectedGraph() {

        vertices = new LinkedList<>();
        size = 0;

    }


    //Data �� �߰�
    public boolean insertVertex(Data data) {

        VertexWithEdge tmpVertex = new VertexWithEdge(data);

        // ���� �׷����� vertex�� 0 ���̸� ù ��°�� �߰�
        if (vertices.size() == 0) {

            vertices.add(tmpVertex);
            size++;
            return true;
        }

        // ���� �Է��ϴ� �������� ������ ũ�� �ٷ� �ڿ� �߰�
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
    public VertexWithEdge deleteVertex(int key) {
        //key == id in Data

        int indexOfVertexList = 0;

        VertexWithEdge tmpVertex;

        // Degree���� 0�� �ƴ� ����
       if(retrieveVertex(key).outDegree !=0 || retrieveVertex(key).inDegree !=0){
            System.out.println("Degree is not Zero");
            return null;
        }

        while (indexOfVertexList < vertices.size()) {



            if ( (tmpVertex=vertices.get(indexOfVertexList)).getData().id == key) {
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
    public boolean insertEdge(VertexWithEdge vertexFrom, VertexWithEdge vertexTo) {

        //�ش��ϴ� vertexFrom�Ǵ� vertexTo�� �׷����� ���ٸ�
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;

            //vertexFrom�� �׷����� �ִٸ�
        else {

            // �߰��� edge
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

                    if(indexOfVertexList < vertexFrom.getEdges().size())
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

    // ������ vertex�� ������ vertex
    public boolean deleteEdge(VertexWithEdge vertexFrom, VertexWithEdge vertexTo) {

        //�ش��ϴ� vertexFrom�Ǵ� vertexTo�� �׷����� ���ٸ�
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;

            //vertexFrom�� �׷����� �ִٸ�
        else {

            int indexOfVertexList = 0;

            // �ϳ��ϳ� Ȯ���ϸ鼭
            for (indexOfVertexList = 0; indexOfVertexList < vertexFrom.getEdges().size(); indexOfVertexList++) {

                //���� ���ٸ� ��������.
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

    // key ������ ã��
    public VertexWithEdge retrieveVertex(int key) {
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


    public void depthFirstSearch() {

        Stack<VertexWithEdge> stack = new Stack<>();

        System.out.print("DFS : ");

        // vertex���� processed�� ��� 0���� �ٲ�
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        // �ϳ��ϳ��� vertex���� Ȯ��
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            VertexWithEdge currentGraphVertex = vertices.get(indexOfVertexList);

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

                VertexWithEdge vertexFromStack = stack.pop();
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

    public void depthFirstSearchByRecursion(){

        // vertex���� processed�� ��� 0���� �ٲ�
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {


            VertexWithEdge currentGraphVertex = vertices.get(indexOfVertexList);

            if(currentGraphVertex.processed == 0){
                depthVisit(currentGraphVertex);
            }

        }


    }

    private void depthVisit(VertexWithEdge vertexWithEdge){

        vertexWithEdge.processed =1;

        for(int index=0; index < vertexWithEdge.getEdges().size() ; index++){

            if(vertexWithEdge.getEdges().get(index).getVertex().processed == 0){
                depthVisit(vertexWithEdge.getEdges().get(index).getVertex());
            }

        }

        vertexWithEdge.processed = 2;
        System.out.println("" + vertexWithEdge.getData().id);

    }

    public void breadthFirstSearch() {

        // ���������� Queue�� LinkedList�� ���� ������� ��� �� �� �ֱ⿡ LinkedList ���
        LinkedList<VertexWithEdge> queue = new LinkedList<>();

        System.out.print("BFS : ");

        // vertex���� processed�� ��� 0���� �ٲ�
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        // �ϳ��ϳ��� vertex���� Ȯ��
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            VertexWithEdge currentGraphVertex = vertices.get(indexOfVertexList);

            // processed�� 0�̶��
            if (currentGraphVertex.processed < 2) {
                if (currentGraphVertex.processed < 1) {

                    //queue�� �߰�
                    queue.add(currentGraphVertex);
                    currentGraphVertex.processed = 1;
                }
            }

            // queue�� �� ������ �˻�
            while (!queue.isEmpty()) {

            VertexWithEdge vertexFromQueue = queue.removeFirst();
                //Stack ���� ������ 2�� flag ó��
                vertexFromQueue.processed = 2;
                System.out.print(vertexFromQueue.getData().id + " ");

                // �Ŀ� vertexFromQueue�� �ش��ϴ� ���� ����Ʈ���� ���� queue�� �־��ش�
                for (int edgeIndex = 0; edgeIndex < vertexFromQueue.getEdges().size(); edgeIndex++) {

                    // queue�� �߰� ������ ���ٸ�
                    if (vertexFromQueue.getEdges().get(edgeIndex).getVertex().processed == 0) {
                        queue.add(vertexFromQueue.getEdges().get(edgeIndex).getVertex());

                        // queue�� �߰�������
                        vertexFromQueue.getEdges().get(edgeIndex).getVertex().processed = 1;
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

        // graph�� �ִٸ��
        else {

            for (int index = 0; index < vertices.size(); index++) {

                System.out.print(vertices.get(index).getData().id + " ");

            }
        }

        System.out.println();

    }

    public void printEdges(VertexWithEdge vertex) {

        // vertex�� �ִ��� Ȯ��
        if (vertices.indexOf(vertex) == -1) {
            System.out.print("No Such Vertex");
        } else {

            // vertex�� edge�� �ִ��� Ȯ��
            if (vertex.getEdges().size() == 0) {
                System.out.print("No Edges");
            } else {
                // ���
                for (int index = 0; index < vertex.getEdges().size(); index++) {

                    System.out.print(vertex.getEdges().get(index).getVertex().getData().id + " -> ");

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
            for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++) {

                VertexWithEdge currentVertex = vertices.get(vertexIndex);

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

            HashMap<Integer, Integer> hashMap = new HashMap<>();

            for(int index=0; index < vertices.size() ; index++){

                hashMap.put(vertices.get(index).getData().id, index);

            }


            int sizeOfMatrix = vertices.size();

            int [][] adjencyMatrix = new int[sizeOfMatrix][sizeOfMatrix];

            // ��Ŀ��ٰ� ���� �ִ´�
            for(int row = 0 ; row <sizeOfMatrix; row++){

                int numOfEdges = 0;

                while(numOfEdges < vertices.get(row).getEdges().size()){

                    adjencyMatrix[row][hashMap.get(vertices.get(row).getEdges().get(numOfEdges).getVertex().getData().id)] = 1;

                    numOfEdges++;
                }

            }

            return adjencyMatrix;

        }


    }

}
