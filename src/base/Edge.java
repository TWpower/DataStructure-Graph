package base;

/**
 * Created by TaeWoo on 2015-09-30.
 */
public class Edge {


    //Edge는 목적지 Vertex를 포함한다.
    //Weight는 그 가중치를 의미한다.
    private Vertex vertex;
    private int weight;

    public Edge(Vertex vertex,int weight){
        this.vertex = vertex;
        this.weight = weight;
    }

    public Vertex getVertex(){

        return vertex;

    }

    public int getWeight(){

        return weight;
    }

}
