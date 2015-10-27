package impl;

import base.Data;
import base.Edge;

import base.Vertex;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by TaeWoo on 2015-10-15.
 */
public class UnDirectedGraph {



    private LinkedList<Vertex> vertices;
    private int size; //number of vertieces

    // 그래프 생성자
    public UnDirectedGraph() {

        vertices = new LinkedList<>();
        size = 0;

    }


    //Data 로 추가
    public boolean insertVertex(Data data) {

        Vertex tmpVertex = new Vertex(data);

        // 만약 그래프가 vertex가 0 개이면 첫 번째에 추가
        if (vertices.size() == 0) {

            vertices.add(tmpVertex);
            size++;
            return true;
        }

        // 만약 입력하는 데이터가 그래프의 마지막 데이터보다 id가 크다면 LinkedList 제일 뒤에 추가
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
    public Vertex deleteVertex(int key) {

        //key == id in Data
        int indexOfVertexList = 0;


        // Degree들이 0이 아닌 경우에만 삭제가 가능!
        if(retrieveVertex(key).outDegree !=0 || retrieveVertex(key).inDegree !=0){
            System.out.println("Degree is not Zero");
            return null;
        }

        //하나하나 확인하면서 찾는다
        while(indexOfVertexList < vertices.size()) {

            if (vertices.get(indexOfVertexList).getData().id == key) {

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
    private boolean __insertEdge(Vertex vertexFrom, Vertex vertexTo) {

        //해당하는 vertexFrom또는 vertexTo가 그래프에 없다면
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;



            //vertexFrom이 그래프에 있다면
        else {

            // 추가할 edge, Weight는 0
            Edge edge = new Edge(vertexTo, 0);

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
                //index가 vertices들의 수를 넘어가지 않고
                //vertexTo의 Data에 있는 id가 현재 Vertex보다 크다면 +1
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

                    //해당 index에다가 Edge를 추가한다.
                    if(indexOfVertexList < vertexFrom.getEdges().size())
                        vertexFrom.getEdges().add(indexOfVertexList, edge);

                        //id의 값이 커서 제일 뒤에 추가하는 경우
                    else
                        vertexFrom.getEdges().addLast(edge);

                    vertexFrom.outDegree++;
                    vertexTo.inDegree++;

                    return true;
                }
            }

        }


    }

    // Edge추가
    public boolean insertEdge(Vertex vertexOne, Vertex vertexTwo){

       return  (__insertEdge(vertexOne,vertexTwo) && __insertEdge(vertexTwo,vertexOne));
    }

    //
    private boolean __deleteEdge(Vertex vertexFrom, Vertex vertexTo) {

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

    // 목적지 vertex와 도착지 vertex
    public boolean deleteEdge(Vertex vertexOne, Vertex vertexTwo) {

        return  (__deleteEdge(vertexOne, vertexTwo) && __deleteEdge(vertexTwo, vertexOne));

    }

    // key 값으로 찾기
    public Vertex retrieveVertex(int key) {
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
                    if (vertexFromStack.getEdges().get(edgeIndex).getVertex().processed != 2) {
                        stack.push(vertexFromStack.getEdges().get(edgeIndex).getVertex());
                        // ????? ?????????
                        vertexFromStack.getEdges().get(edgeIndex).getVertex().processed = 1;
                    }
                }

            }

        }

    }

    public void depthFirstSerachByStack(){

        // vertex에서 processed를 모두 0으로 바꿈
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

        // vertex에서 processed를 모두 0으로 바꿈
        for (Vertex vertex : vertices) {
            vertex.processed = 0;
        }

        // 모든 vertex들을 depthVisit을 통해서 재귀호출
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

        // 현재 vertex의 edge들의 vertex에 대해서 processed=0이면 재귀호출한다.
        for(Edge edge : vertex.getEdges()){

            if(edge.getVertex().processed == 0){
                __depthVisit(edge.getVertex());
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
                    if (vertexFromQueue.getEdges().get(edgeIndex).getVertex().processed == 0)
                        queue.add(vertexFromQueue.getEdges().get(edgeIndex).getVertex());

                    // queue?? ?????????
                    vertexFromQueue.getEdges().get(edgeIndex).getVertex().processed = 1;

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

                    System.out.print(vertex.getEdges().get(index).getVertex().getData().id + " -> ");

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

        //만약 그래프가 없다면
        if(vertices.size() == 0)
            return null;


            // 그래프가 있다면
        else{

            //Vertex의 id와 배열의 index를 matching 시키기 위한 해시맵
            HashMap<Integer, Integer> hashMap = new HashMap<>();

            //해시맵을 통해서 대응 시킴
            // key : id of Vertex, value : index of Array
            for(int index=0; index < vertices.size() ; index++){

                hashMap.put(vertices.get(index).getData().id, index);

            }


            int sizeOfMatrix = vertices.size();

            // 인접행렬을 크기에 따라서 만들고
            int [][] adjencyMatrix = new int[sizeOfMatrix][sizeOfMatrix];

            // 행렬에다가 값을 넣는다
            // 하나의 행(row)에 대해서
            for(int row = 0 ; row <sizeOfMatrix; row++){

                //HashMap을 통해서 id에 해당하는 배열의 인덱스를 찾아서 1을 넣어준다
                for(Edge edge : vertices.get(row).getEdges()){

                    adjencyMatrix[row][hashMap.get(edge.getVertex().getData().id)] = 1;
                    //edge.getVertex().getData().id : 현재 Edge가 가지고 있는 Vetex의 id
                    //hashMap.get(id) : id를 통해서 배열의 index 값을 가져온다.

                }

            }
            return adjencyMatrix;
        }


    }

}


