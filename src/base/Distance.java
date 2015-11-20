package base;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by TaeWoo on 2015-11-07.
 */
public class Distance {


    private int distance;
    private LinkedList<Vertex> routes;

    //Constructor

    public Distance(){

        this.distance = Integer.MAX_VALUE;
        routes = new LinkedList<>();
    }

    //Getter

    public int getDistance() {
        return distance;
    }

    public LinkedList<Vertex> getRoutes() {
        return routes;
    }


    //Setter

    public void setDistance(int distance) {
        this.distance = distance;
    }

    //Methods

    public void printRoutesAndDistance(){

        String routes = "";

        for(Vertex vertex : this.routes){

            routes += vertex.getData().id + " ";
        }

        routes += ": " + distance;

        System.out.println(routes);

    }

}
