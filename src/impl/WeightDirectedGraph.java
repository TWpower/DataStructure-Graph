package impl;

import base.Data;
import base.Distance;
import base.Edge;
import base.Vertex;

import java.util.*;

/**
 * Created by TaeWoo on 2015-10-19.
 */
public class WeightDirectedGraph {
    
    
    private static int INF = 100000;

    private LinkedList<Vertex> vertices;
    private int size; //number of vertieces

    // 그래프 생성자
    public WeightDirectedGraph() {

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
    public boolean insertEdge(Vertex vertexFrom, Vertex vertexTo, int weight) {

        //해당하는 vertexFrom또는 vertexTo가 그래프에 없다면
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;



            //vertexFrom이 그래프에 있다면
        else {

            // 추가할 edge, Weight는 0
            Edge edge = new Edge(vertexTo, vertexFrom ,weight);

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
                        && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getToVertex().getData()) == 1
                        ) {

                    indexOfVertexList++;

                }

                //중복된 값이 있으면 Return
                if (indexOfVertexList < vertexFrom.getEdges().size()
                        && vertexTo.getData().compareTo(vertexFrom.getEdges().get(indexOfVertexList).getToVertex().getData()) == 0) {
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

    // 목적지 vertex와 도착지 vertex
    public boolean deleteEdge(Vertex vertexFrom, Vertex vertexTo) {

        //해당하는 vertexFrom또는 vertexTo가 그래프에 없다면
        if (vertices.indexOf(vertexFrom) == -1 || vertices.indexOf(vertexTo) == -1)
            return false;

            //vertexFrom이 그래프에 있다면
        else {

            // 하나하나 확인하면서
            for (Edge edge : vertexFrom.getEdges()) {

                //값이 같다면 빼버린다.
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

        // vertex에서 processed를 모두 0으로 바꿈
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        // 하나하나의 vertex들을 확인
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            Vertex currentGraphVertex = vertices.get(indexOfVertexList);

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

                Vertex vertexFromStack = stack.pop();
                //Stack 에서 꺼내면 2로 flag 처리
                if(vertexFromStack.processed != 2) {
                    vertexFromStack.processed = 2;
                    System.out.print(vertexFromStack.getData().id + " ");
                }


                // 후에 vertexFromStack에 해당하는 인접 리스트들을 전부 stack에 넣어준다
                for (int edgeIndex = vertexFromStack.getEdges().size()-1;
                     edgeIndex >= 0 ; edgeIndex--) {


                    if (vertexFromStack.getEdges().get(edgeIndex).getToVertex().processed != 2) {
                        stack.push(vertexFromStack.getEdges().get(edgeIndex).getToVertex());
                        // 스택에 추가했으니
                        vertexFromStack.getEdges().get(edgeIndex).getToVertex().processed = 1;
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

                stack.push(edge.getToVertex());
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

            if(edge.getToVertex().processed == 0){
                __depthVisit(edge.getToVertex());
            }
        }


    }

    public void breadthFirstSearch() {

        // 실질적으로 Queue와 LinkedList를 같은 방식으로 사용 할 수 있기에 LinkedList 사용
        LinkedList<Vertex> queue = new LinkedList<>();

        System.out.print("BFS : ");

        // vertex에서 processed를 모두 0으로 바꿈
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            vertices.get(indexOfVertexList).processed = 0;

        }

        // 하나하나의 vertex들을 확인
        for (int indexOfVertexList = 0; indexOfVertexList < vertices.size(); indexOfVertexList++) {

            Vertex currentGraphVertex = vertices.get(indexOfVertexList);

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

                Vertex vertexFromQueue = queue.removeFirst();
                //Stack 에서 꺼내면 2로 flag 처리
                vertexFromQueue.processed = 2;
                System.out.print(vertexFromQueue.getData().id + " ");

                // 후에 vertexFromQueue에 해당하는 인접 리스트들을 전부 queue에 넣어준다
                for (int edgeIndex = 0; edgeIndex < vertexFromQueue.getEdges().size(); edgeIndex++) {

                    // queue에 추가 한적이 없다면
                    if (vertexFromQueue.getEdges().get(edgeIndex).getToVertex().processed == 0) {
                        queue.add(vertexFromQueue.getEdges().get(edgeIndex).getToVertex());

                        // queue에 추가했으니
                        vertexFromQueue.getEdges().get(edgeIndex).getToVertex().processed = 1;
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

    public void printEdges(Vertex vertex) {

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
                            +"->" + vertex.getEdges().get(index).getToVertex().getData().id );

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

                    adjencyMatrix[row][hashMap.get(edge.getToVertex().getData().id)] = edge.getWeight();
                    //edge.getToVertex().getData().id : 현재 Edge가 가지고 있는 Vetex의 id
                    //hashMap.get(id) : id를 통해서 배열의 index 값을 가져온다.

                }

            }
            return adjencyMatrix;
        }


    }

    // Edge Weight가 음수이지 않은 경우!
    public Distance ShortestPathByDijkstra(Vertex startVertex, Vertex destinationVertex){

        // 값이 있는지 확인
        if (vertices.indexOf(startVertex) == -1 || vertices.indexOf(destinationVertex) == -1)
            return null;


        //Vertex의 id와 배열의 index를 matching 시키기 위한 해시맵
        HashMap<Integer, Integer> idToArrayIndex = new HashMap<>();

        //해시맵을 통해서 대응 시킴
        // key : id of Vertex, value : index of Array
        for(int index=0; index < vertices.size() ; index++){

            idToArrayIndex.put(vertices.get(index).getData().id, index);

        }

        //배열의 index와 Vertex의 id를 matching 시키기 위한 해시맵
        HashMap<Integer, Integer> arrayIndexToId = new HashMap<>();

        //해시맵을 통해서 대응 시킴
        //key : index of Array, value : id of Vertex
        for(int index=0; index < vertices.size() ; index++){

            arrayIndexToId.put(index, vertices.get(index).getData().id);

        }

        // 거리를 저장하기 위한 배열 d
        // 이전 위치를 저장하기위한 배열 p

        int []d = new int[vertices.size()]; // distance
        Arrays.fill(d,INF); // 처음 거리는 정해지지 않았으므로 거리는 INF
        int []p = new int[vertices.size()]; // previous stop
        Arrays.fill(p,-1); // 아직 정해지지 않은 부분에 대해서는 -1로 설정

        // 처리 전과 후를 위한 List
        LinkedList<Vertex> before = new LinkedList<>();
        before.addAll(vertices);

        LinkedList<Vertex> after = new LinkedList<>();

        // startVertex의 id와 destinaionVertex의 id를 저장하는 변수
        int startVertexId = startVertex.getData().id;
        int destinationVertexId = destinationVertex.getData().id;

        // id에 따른 배열 값에 대한 변수
        int startVertexArrayIndex = idToArrayIndex.get(startVertexId);
        int destinationVertexArrayIndex = idToArrayIndex.get(destinationVertexId);

        //자기 자신까지의 거리는 0
        d[startVertexArrayIndex] = 0;

        //처음 시작에 해당하는 부분부터 우선 process

        after.add(before.remove(before.indexOf(startVertex)));

        // 처음 시작하는 Vertex에 연결된 Edge들의 Weight를 통해서 거리를 구하고 d에 표시

        for(Edge edge : startVertex.getEdges()){

            int v = idToArrayIndex.get(edge.getToVertex().getData().id);

            if(d[v] > d[startVertexArrayIndex] + edge.getWeight()) {
                d[v] = d[startVertexArrayIndex] + edge.getWeight();
                p[v] = startVertexArrayIndex;
            }
        }

        //나머지 부분들 처리
        //before에 있는 모든 Vertex들을 꺼내서 처리하면 완료!

        while(before.size() != 0){


            int shortestPathArrayIndex=idToArrayIndex.get(before.getFirst().getData().id);

            // before에 남겨져 있는 vertex들 중에서 출발점으로부터 거리가 가장 짧은 vertex의 배열 index 값을 찾는다
            for(Vertex vertex : before){

                if(d[shortestPathArrayIndex] > d[idToArrayIndex.get(vertex.getData().id)])
                    shortestPathArrayIndex = idToArrayIndex.get(vertex.getData().id);

            }

            // 뽑은 놈에 대한 vertex를 after에 넣고 최단거리를 구한다

            Vertex pickedVertex = retrieveVertex(arrayIndexToId.get(shortestPathArrayIndex));

            after.add(before.remove(before.indexOf(pickedVertex)));

            for(Edge edge : pickedVertex.getEdges()){

                int v = idToArrayIndex.get(edge.getToVertex().getData().id);

                if(d[v] > d[shortestPathArrayIndex] + edge.getWeight()) {
                    d[v] = d[shortestPathArrayIndex] + edge.getWeight();
                    p[v] = shortestPathArrayIndex;
                }
            }

        }


        Distance distance = new Distance();

        // 거리 추가
        distance.setDistance(d[destinationVertexArrayIndex]);

        // 역추적을 통해서 경로 찾기
        int pivot = destinationVertexArrayIndex;

        while(pivot != -1){

            distance.getRoutes().addFirst(retrieveVertex(arrayIndexToId.get(pivot)));

            pivot = p[pivot];

        }

        return distance;
    }

    // negative weight cycle이 없는 경우!
    public Distance ShortestPathByBellman_Ford(Vertex startVertex, Vertex destinationVertex){

        // 값이 있는지 확인
        if (vertices.indexOf(startVertex) == -1 || vertices.indexOf(destinationVertex) == -1)
            return null;


        //Vertex의 id와 배열의 index를 matching 시키기 위한 해시맵
        HashMap<Integer, Integer> idToArrayIndex = new HashMap<>();

        //해시맵을 통해서 대응 시킴
        //key : id of Vertex, value : index of Array
        for(int index=0; index < vertices.size() ; index++){

            idToArrayIndex.put(vertices.get(index).getData().id, index);

        }

        //배열의 index와 Vertex의 id를 matching 시키기 위한 해시맵
        HashMap<Integer, Integer> arrayIndexToId = new HashMap<>();

        //해시맵을 통해서 대응 시킴
        //key : index of Array, value : id of Vertex
        for(int index=0; index < vertices.size() ; index++){

            arrayIndexToId.put(index, vertices.get(index).getData().id);

        }

        int []d = new int[vertices.size()]; // distance
        Arrays.fill(d,INF);
        int []p = new int[vertices.size()]; // previous stop
        Arrays.fill(p,-1); //아직 정해지지 않은 부분에 대해서는 -1로 설정

        // startVertex의 id와 destinaionVertex의 id를 저장하는 변수
        int startVertexId = startVertex.getData().id;
        int destinationVertexId = destinationVertex.getData().id;

        // id에 따른 배열 값에 대한 변수
        int startVertexArrayIndex = idToArrayIndex.get(startVertexId);
        int destinationVertexArrayIndex = idToArrayIndex.get(destinationVertexId);

        // 처음부분을 거리를 0으로 설정
        d[startVertexArrayIndex] = 0;

        // 전체 vertex의 수 -1 만큼 아래의 작업을 반복한다.
        for(int count=0 ; count < vertices.size() -1 ; count++){

            // 각각의 vertex에 대해
            for(Vertex vertex : vertices){

                // 각각의 Edge에 대해서
                for(Edge edge : vertex.getEdges()){

                    //현재 vertex까지의 거리에서 연결된 edge의 가중치를 더한게 향하고 있는 Vertex까지의 거리보다 짧다면
                    if(d[idToArrayIndex.get(vertex.getData().id)] + edge.getWeight()
                            < d[idToArrayIndex.get(edge.getToVertex().getData().id)]){

                        //그 값을 갱신해주고 이전 위치를 기억한다(p)
                        d[idToArrayIndex.get(edge.getToVertex().getData().id)]
                                = d[idToArrayIndex.get(vertex.getData().id)] + edge.getWeight();

                        p[idToArrayIndex.get(edge.getToVertex().getData().id)]
                                = idToArrayIndex.get(vertex.getData().id);
                    }
                }
            }
        }

        Distance distance = new Distance();

        // 거리 추가
        distance.setDistance(d[destinationVertexArrayIndex]);

        int pivot = destinationVertexArrayIndex;

        // 역추적을 통해서 경로 찾기
        while(pivot != -1){

            distance.getRoutes().addFirst(retrieveVertex(arrayIndexToId.get(pivot)));

            pivot = p[pivot];

        }

        return distance;
    }

    // negative weight cycle이 없는 경우!
    public Distance ShortestPathByFloyd_Warshall(Vertex startVertex, Vertex destinationVertex){


        // 값이 있는지 확인
        if (vertices.indexOf(startVertex) == -1 || vertices.indexOf(destinationVertex) == -1)
            return null;


        //Vertex의 id와 배열의 index를 matching 시키기 위한 해시맵
        HashMap<Integer, Integer> idToArrayIndex = new HashMap<>();

        //해시맵을 통해서 대응 시킴
        //key : id of Vertex, value : index of Array
        for(int index=0; index < vertices.size() ; index++){

            idToArrayIndex.put(vertices.get(index).getData().id, index);

        }

        //배열의 index와 Vertex의 id를 matching 시키기 위한 해시맵
        HashMap<Integer, Integer> arrayIndexToId = new HashMap<>();

        //해시맵을 통해서 대응 시킴
        //key : index of Array,  value : id of Vertex
        for(int index=0; index < vertices.size() ; index++){

            arrayIndexToId.put(index, vertices.get(index).getData().id);

        }


        // 계산을 위한 인접 행렬
        int [][] weightMatrix = makeAdjacencyMatrixForSP();

        int i, j, stop;

        for (stop = 0; stop < vertices.size(); stop++) {
            for (i = 0; i < vertices.size(); i++) {
                for (j = 0; j < vertices.size(); j++) {


                    if (weightMatrix[i][stop] + weightMatrix[stop][j] < weightMatrix[i][j])
                        weightMatrix[i][j] = weightMatrix[i][stop] + weightMatrix[stop][j];

                }
            }
        }

        // 경로 계산 끝
        if(weightMatrix[idToArrayIndex.get(startVertex.getData().id)][idToArrayIndex.get(destinationVertex.getData().id)] == INF){
            // 경로가 존재하지 않는 경우

            Distance distance = new Distance();

            distance.setDistance(INF);

            return distance;

        }

        // 경로가 존재하는 경우

        Distance distance = new Distance();

        // 거리 추가
        distance.setDistance(weightMatrix[idToArrayIndex.get(startVertex.getData().id)][idToArrayIndex.get(destinationVertex.getData().id)]);

        distance.getRoutes().addFirst(destinationVertex);

        // 처음에 목적지 id에 해당하는 index를 track index로 지정
        // 역추적을 통해서 경로 찾기
        int trackIndex = idToArrayIndex.get(destinationVertex.getData().id);

        // 목적지(열)를 기준으로 행렬의 최소값을 갖는 출발지(행)를 찾는 방식으로 Tracking
        while(true){

            int trackRow = 0;

            for(int row = 0 ; row < vertices.size(); row ++){

                // 행들을 하나하나 조사하면서 trackIndex까지의 최단거리인 row를 trackRow에 넣어준다다
               if(weightMatrix[trackRow][trackIndex] > weightMatrix[row][trackIndex]
                        && (row !=trackIndex))
                    trackRow = row;

            }
            // 다 돌면 trackIndex까지의 거리가 최소인 장소가 나옴 그게 trackRow임

            Vertex tmpVertex = retrieveVertex(arrayIndexToId.get(trackRow));

            distance.getRoutes().addFirst(tmpVertex);

            trackIndex = trackRow;

            if(tmpVertex.equals(startVertex))
                break;
        }

        return distance;
    }

    private int[][] makeAdjacencyMatrixForSP() {

        //만약 그래프가 없다면
        if(vertices.size() == 0)
            return null;


            // 그래프가 있다면
        else{

            //Vertex의 id와 배열의 index를 matching 시키기 위한 해시맵
            HashMap<Integer, Integer> idToArrayIndex = new HashMap<>();

            //해시맵을 통해서 대응 시킴
            // key : id of Vertex, value : index of Array
            for(int index=0; index < vertices.size() ; index++){

                idToArrayIndex.put(vertices.get(index).getData().id, index);

            }


            int sizeOfMatrix = vertices.size();

            // 인접행렬을 크기에 따라서 만들고
            int [][] adjencyMatrix = new int[sizeOfMatrix][sizeOfMatrix];

            //거리가 연결되지 않은 부분은 거리를 INF로 설정
            // -> makeAdjacencyMatrix()와 다른 부분
            for (int[] row: adjencyMatrix)
                Arrays.fill(row, INF);


            for(int index=0 ; index< sizeOfMatrix ; index++){

                adjencyMatrix[index][index] = 0;

            }

            // 행렬에다가 값을 넣는다
            // 하나의 행(row)에 대해서
            for(int row = 0 ; row <sizeOfMatrix; row++){

                //HashMap을 통해서 id에 해당하는 배열의 인덱스를 찾아서 1을 넣어준다
                for(Edge edge : vertices.get(row).getEdges()){

                    adjencyMatrix[row][idToArrayIndex.get(edge.getToVertex().getData().id)] = edge.getWeight();
                    //edge.getToVertex().getData().id : 현재 Edge가 가지고 있는 Vetex의 id
                    //hashMap.get(id) : id를 통해서 배열의 index 값을 가져온다.

                }

            }
            return adjencyMatrix;
        }

    }

}
