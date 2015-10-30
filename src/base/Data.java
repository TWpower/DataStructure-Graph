package base;

/**
 * Created by TaeWoo on 2015-09-30.
 */

/**
 * Data for Simulation
 */

public class Data implements ICompare<Data> {

    // Primary Key ������ �ϴ� id
    public int id;
    public String name;

    // id�� ���ؼ� �ʱ�ȭ
    public Data(int id){this.id = id;}

    // ���� ���� ���ϰ��� �ϴ� Data�� id���� ũ�ٸ� 1
    // �۴ٸ� -1
    // �Ȱ��ٸ� 0
    public int compareTo(Data data) {

        if (this == data || this.id == data.id)
            return 0;

        return this.id > data.id ? 1 : -1;
    }

}
