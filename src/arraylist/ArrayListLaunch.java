package arraylist;

import java.util.ArrayList;

public class ArrayListLaunch {
    public static void main(String[] args) {
       ArrayList<Integer> list = new ArrayList<>();
//      list.addAll()
//       list.indexOf()
//       list.add(1);
//       list.add(2);
//       list.add(3);
//
//       ArrayList<Integer> list2 = new ArrayList<>();
//       list2.add(1);
//       list2.add(2);
//       list2.add(3);
//
//       list.retainAll(list2);
//       System.out.println(list);
//       System.out.println(list2);
       MyArrayList<Integer> myArrayList = new MyArrayList();
       myArrayList.add( 1);
       myArrayList.add(2);
       myArrayList.add(3);
       myArrayList.add(4);
       myArrayList.add(5);
//       myArrayList.add(4);
//       myArrayList.add(5);
//       myArrayList.add(6);
//       myArrayList.add(7);
//       myArrayList.add(8);
//       myArrayList.add(9);
//       myArrayList.add(10);
//       myArrayList.add(11);

       MyArrayList<Integer> nal = new MyArrayList();
       nal.add(1);
       nal.add(2);
       nal.add(8);
       nal.add(7);
//       myArrayList.print();
       myArrayList.retainAll(nal);
//       System.out.println();
//       myArrayList.print();

       System.out.println("---------------------");
//       myArrayList.print();
//       myArrayList.addAll(0, nal);
       int i = 3;
//       System.out.println(myArrayList.containsAll(nal));
//       System.out.println();
//       myArrayList.print();
//
//       int i = myArrayList.contains(3);
//       System.out.println(i);
//
//       int s = myArrayList.size();
//       System.out.println(s);
//
//       myArrayList.removeByIndex(10);
//       int s1 = myArrayList.size();
//       System.out.println(s1);
//
//       myArrayList.removeByElement(7);
//       System.out.println(myArrayList.size());


//       myArrayList.clear();

    }
}
