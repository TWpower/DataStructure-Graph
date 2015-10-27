package base;

import java.util.LinkedList;

/**
 * Created by TaeWoo on 2015-10-26.
 */
public class Vertex {

    //LinkedList를 통해서 Edge의 값을 저장
    //Data는 Vertex 안에 집어 넣을 Data
    //inDegree와 outDegree는 차수
    //processed는 Traverse를 위한 Flag
    private LinkedList<Edge> edges;
    private Data data;
    public int inDegree;
    public int outDegree;
    public int processed;

    public Vertex(Data data) {

        edges = new LinkedList<>();
        this.data = data;
        inDegree = 0;
        outDegree = 0;
        processed = 0;

    }

    public Data getData(){

        return data;
    }

    public LinkedList<Edge> getEdges(){

        return edges;
    }

    public void deleteEdges(){
        edges = null;
    }
    
    
}
