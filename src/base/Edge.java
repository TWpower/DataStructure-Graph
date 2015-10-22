package base;

/**
 * Created by TaeWoo on 2015-09-30.
 */
public class Edge {

    private VertexWithEdge vertex;
    private int weight;

    public Edge(VertexWithEdge vertex,int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    public VertexWithEdge getVertex(){

        return vertex;

    }

    public int getWeight(){

        return weight;
    }

}
