package base;

/**
 * Created by TaeWoo on 2015-09-30.
 */
public class Edge {


    //Edge�� ������ Vertex�� �����Ѵ�.
    //Weight�� �� ����ġ�� �ǹ��Ѵ�.
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
