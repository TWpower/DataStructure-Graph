package base;

/**
 * Created by TaeWoo on 2015-09-30.
 */
public class Edge {


    //Edge�� ������ Vertex�� �����Ѵ�.
    //Weight�� �� ����ġ�� �ǹ��Ѵ�.
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
