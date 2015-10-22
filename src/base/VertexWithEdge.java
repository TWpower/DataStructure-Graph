package base;

import java.util.LinkedList;

/**
 * Created by TaeWoo on 2015-10-20.
 */
public class VertexWithEdge {

    private LinkedList<Edge> edges;
    private Data data;
    public int inDegree;
    public int outDegree;
    public int processed;

    public VertexWithEdge(Data data) {

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
