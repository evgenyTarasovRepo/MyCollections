package set;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MySetLaunch {
    public static void main(String[] args) {
//        Set<Integer> s = new HashSet<>();
        MySet<Integer> set = new MySet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);

        MySet<Integer> set2 = new MySet<>();
        set2.add(1);
        set2.add(2);
//        set2.add(5);
//        set2.add(6);

        set.retainAll(set2);
        System.out.println(Arrays.toString(set.toArray()));
    }
}
