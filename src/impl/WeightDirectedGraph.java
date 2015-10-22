package impl;

import base.Data;
import base.Edge;
import base.VertexWithEdge;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by TaeWoo on 2015-10-19.
 */
public class WeightDirectedGraph {

    private LinkedList<VertexWithEdge> vertices;
    private int size; //number of vertieces

    // 그래프 생성자
    public WeightDirectedGraph() {

        vertices = new LinkedList<>();
        size = 0;

    }


    //Data 로 추가
    public boolean insertVertex(Data data) {

        VertexWithEdge tmpVertex = new VertexWithEdge(data);

        // 만약 그래프가 vertex가 0 개이면 첫 번째에 추가
        if (vertices.size() == 0) {

            vertices.add(tmpVertex);
            size++;
            return true;
        }

        // 만약 입력하는 데이터의 끝보다 크면 바로 뒤에 추가
        else if (data.compareTo(vertices.getLast().getData()) == 1) {
            vertices.addLast(tmpVertex);
            size++;
            return true;
        }

        // 만약 입력한 vertex가 처음이 아니라면 링크드 리스트와 각각 비교
        else {

            int indexOfVertexList = 0;

            // 입력하는 자료의 id값이 크면 index 값을 +1
            while (data.compareTo(vertices.get(indexOfVertexList).getData()) == 1) {

                indexOfVertexList++;

            }

            //중복된 값이 있으면 Return
            if (data.compareTo(vertices.get(indexOfVertexList).getData()) == 0) {
                System.out.println("중복된 값 오류");
                return false;
            }

            // 중복이 아니면 추가
            else {

                vertices.add(indexOfVertexList, tmpVertex);
                size++;
                return true;
            }


        }

    }

    //key 값으로 찾는다.
    public VertexWithEdge deleteVertex(int key) {
        //key == id in Data

        int indexOfVertexList = 0;

        VertexWithEdge tmpVertex;

        // Degree들이 0이 아닌 경우우
        if(retrieveVertex(key).outDegree !=0 || retrieveVertex(key).inDegree !=0){
            System.out.println("Degree is not Zero");
            return null;
        }

        while (indexOfVertexList < vertices.size()) {



            if ( (tmpVertex=vertices.get(indexOfVertexList)).getData().id == key) {
                // vertex 삭제 및 반환
                size--;
                return vertices.remove(indexOfVertexList);

            }

            indexOfVertexList++;

        }

        //key에 해당하는게 없는 경우
        return null;

    }

    // 목적지 vertex와 도착지 vertex
    public boolean insertEdge(VertexWithEdge vertexFrom, VertexWithEdge vertexTo, int weight) {

        //해당하는 vertexFrom또는 vertexTo가 그래프에 없다면
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;

            //vertexFrom이 그래프에 있다면
        else {

            // 추가할 edge
            Edge edge = new Edge(vertexTo, weight);

            int indexOfVertexList = 0;

            //만약 처음 넣는다면
            if (vertexFrom.getEdges().size() == 0){

                vertexFrom.getEdges().add(edge);
                vertexFrom.outDegree++;
                vertexTo.inDegree++;
                return true;

            }

            else {
                //인덱스를 올리면서 edge들을 비교
                while (indexOfVertexList < vertexFrom.getEdges().size()
                        && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getVertex().getData()) == 1
                        ) {

                    indexOfVertexList++;

                }

                //중복된 값이 있으면 Return
                if (indexOfVertexList < vertexFrom.getEdges().size()
                        && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getVertex().getData()) == 0) {
                    System.out.println("중복된 값 오류");
                    return false;
                }

                // 중복이 아니면 추가
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

    // 목적지 vertex와 도착지 vertex
    public boolean deleteEdge(VertexWithEdge vertexFrom, VertexWithEdge vertexTo) {

        //해당하는 vertexFrom또는 vertexTo가 그래프에 없다면
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;

            //vertexFrom이 그래프에 있다면
        else {

            int indexOfVertexList = 0;

            // 하나하나 확인하면서
            for (indexOfVertexList = 0; indexOfVertexList < vertexFrom.getEdges().size(); indexOfVertexList++) {

                //값이 같다면 빼버린다.
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

    // key 값으로 찾기
    public VertexWithEdge retrieveVertex(int key) {
        //key == id in Data

        int indexOfVertexList;

        // 하나하나 찾으면서
        for (indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            // 만약 같은 값이 있으면 반환
            if (vertices.get(indexOfVertexList).getData().id == key)
                return vertices.get(indexOfVertexList);

        }

        // 같은 값이 없다면 null
        return null;
    }


    public void depthFirstSearch() {

        Stack<VertexWithEdge> stack = new Stack<>();

        System.out.print("DFS : ");

        // vertex에서 processed를 모두 0으로 바꿈
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        // 하나하나의 vertex들을 확인
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            VertexWithEdge currentGraphVertex = vertices.get(indexOfVertexList);

            // processed가 0이라면
            if (currentGraphVertex.processed < 2) {
                if (currentGraphVertex.processed < 1) {

                    stack.push(currentGraphVertex);
                    // 스택에 추가
                    currentGraphVertex.processed = 1;
                }
            }

            // 스택이 빌 때까지 검사
            while (!stack.isEmpty()) {

                VertexWithEdge vertexFromStack = stack.pop();
                //Stack 에서 꺼내면 2로 flag 처리
                if(vertexFromStack.processed != 2) {
                    vertexFromStack.processed = 2;
                    System.out.print(vertexFromStack.getData().id + " ");
                }


                // 후에 vertexFromStack에 해당하는 인접 리스트들을 전부 stack에 넣어준다
                for (int edgeIndex = vertexFromStack.getEdges().size()-1;
                     edgeIndex >= 0 ; edgeIndex--) {


                    if (vertexFromStack.getEdges().get(edgeIndex).getVertex().processed != 2) {
                        stack.push(vertexFromStack.getEdges().get(edgeIndex).getVertex());
                        // 스택에 추가했으니
                        vertexFromStack.getEdges().get(edgeIndex).getVertex().processed = 1;
                    }

                }

            }

        }

    }

    public void breadthFirstSearch() {

        // 실질적으로 Queue와 LinkedList를 같은 방식으로 사용 할 수 있기에 LinkedList 사용
        LinkedList<VertexWithEdge> queue = new LinkedList<>();

        System.out.print("BFS : ");

        // vertex에서 processed를 모두 0으로 바꿈
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        // 하나하나의 vertex들을 확인
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            VertexWithEdge currentGraphVertex = vertices.get(indexOfVertexList);

            // processed가 0이라면
            if (currentGraphVertex.processed < 2) {
                if (currentGraphVertex.processed < 1) {

                    //queue에 추가
                    queue.add(currentGraphVertex);
                    currentGraphVertex.processed = 1;
                }
            }

            // queue가 빌 때까지 검사
            while (!queue.isEmpty()) {

                VertexWithEdge vertexFromQueue = queue.removeFirst();
                //Stack 에서 꺼내면 2로 flag 처리
                vertexFromQueue.processed = 2;
                System.out.print(vertexFromQueue.getData().id + " ");

                // 후에 vertexFromQueue에 해당하는 인접 리스트들을 전부 queue에 넣어준다
                for (int edgeIndex = 0; edgeIndex < vertexFromQueue.getEdges().size(); edgeIndex++) {

                    // queue에 추가 한적이 없다면
                    if (vertexFromQueue.getEdges().get(edgeIndex).getVertex().processed == 0) {
                        queue.add(vertexFromQueue.getEdges().get(edgeIndex).getVertex());

                        // queue에 추가했으니
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

        // graph가 없다면
        if (size == 0) {
            System.out.print("Nothing in Graph");
        }

        // graph가 있다면면
        else {

            for (int index = 0; index < vertices.size(); index++) {

                System.out.print(vertices.get(index).getData().id + " ");

            }
        }

        System.out.println();

    }

    public void printEdges(VertexWithEdge vertex) {

        // vertex가 있는지 확인
        if (vertices.indexOf(vertex) == -1) {
            System.out.print("No Such Vertex");
        } else {

            // vertex에 edge가 있는지 확인
            if (vertex.getEdges().size() == 0) {
                System.out.print("No Edges");
            } else {
                // 출력
                for (int index = 0; index < vertex.getEdges().size(); index++) {

                    System.out.print(" -(" + vertex.getEdges().get(index).getWeight() + ")"
                            +"->" + vertex.getEdges().get(index).getVertex().getData().id );

                }
            }
        }

        System.out.println();

    }

    public void printGraph() {

        // graph가 없으면
        if (size == 0) {
            System.out.println("Nothing in Graph");
        }

        // graph가 있다면
        else {

            // vertex
            for (int vertexIndex = 0; vertexIndex < vertices.size(); vertexIndex++) {

                VertexWithEdge currentVertex = vertices.get(vertexIndex);

                System.out.print(currentVertex.getData().id + " : ");

                printEdges(currentVertex);

            }
        }

    }
}
