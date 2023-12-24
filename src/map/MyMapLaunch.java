package map;

import java.util.*;

public class MyMapLaunch {
    public static void main(String[] args) {
        MyMap<String, String> map = new MyMap<>();
//        MyMap<String, String> map1 = new MyMap<>();
        map.put("1", "14");
        map.put("34", "19");
        map.put("3", "16");
        map.put("7", "15");
        map.put("5", "134");
        map.put("33", "24");
        map.put("23", "4");
        map.put("56", "99");
        map.put("153", "111");
        map.put("135", "1124");

        System.out.println(map.size());

//        System.out.println(map.entrySet());
//        Set<String> set = map.keySet();
//        for (String s : set) {
//            System.out.println(s);
//        }

//        for (Map.Entry<String, String> m :  map1.entrySet()) {
//            System.out.println(m.getKey() + " : " + m.getValue());
//        }
        System.out.println(map.values());
    }
}
