package base;

/**
 * Created by TaeWoo on 2015-09-30.
 */

/**
 * Data for Simulation
 */

public class Data implements ICompare<Data> {

    public int id;
    public String name;

    public Data(int id){this.id = id;}


    public int compareTo(Data data) {

        if (this == data || this.id == data.id)
            return 0;

        return this.id > data.id ? 1 : -1;
    }

}
