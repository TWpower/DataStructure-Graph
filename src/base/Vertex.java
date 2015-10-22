package base;

import java.util.LinkedList;

/**
 * Created by TaeWoo on 2015-09-30.
 */
public class Vertex {

    private LinkedList<Vertex> edges;
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

    public LinkedList<Vertex> getEdges(){

        return edges;
    }

    public void deleteEdges(){
        edges = null;
    }

}
