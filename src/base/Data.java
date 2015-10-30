package base;

/**
 * Created by TaeWoo on 2015-09-30.
 */

/**
 * Data for Simulation
 */

public class Data implements ICompare<Data> {

    // Primary Key 역할을 하는 id
    public int id;
    public String name;

    // id를 통해서 초기화
    public Data(int id){this.id = id;}

    // 현재 내가 비교하고자 하는 Data의 id보다 크다면 1
    // 작다면 -1
    // 똑같다면 0
    public int compareTo(Data data) {

        if (this == data || this.id == data.id)
            return 0;

        return this.id > data.id ? 1 : -1;
    }

}
