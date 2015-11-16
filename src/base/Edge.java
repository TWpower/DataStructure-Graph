package base;

/**
 * Created by TaeWoo on 2015-09-30.
 */
public class Edge {


    //Edge는 목적지 Vertex를 포함한다.
    //Weight는 그 가중치를 의미한다.
    private Vertex toVertex;
    private Vertex fromVertex;
    private int weight;

    public Edge(Vertex toVertex, Vertex fromVertex ,int weight){
        this.toVertex = toVertex;
        this.fromVertex = fromVertex;
        this.weight = weight;
    }

    public Vertex getToVertex(){

        return toVertex;

    }

    public Vertex getFromVertex(){

        return fromVertex;

    }

    public int getWeight(){

        return weight;
    }

}
