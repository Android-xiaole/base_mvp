package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyClass {
    public static void main(String[] args){
        List<Test1> list1 = new ArrayList<>();
        List<Test1> list2 = new ArrayList<>();
        list1.add(new Test1(0,"0"));
        list1.add(new Test1(1,"1"));
        list1.add(new Test1(2,"2"));
        list1.add(new Test1(3,"3"));
        list1.add(new Test1(4,"4"));
        list1.add(new Test1(5,"5"));

        list2.add(new Test1(1,"1"));
        list2.add(new Test1(1,"2"));
        list2.add(new Test1(2,"2"));
        list2.add(new Test1(3,"3"));
        list2.add(new Test1(4,"4"));
        list2.add(new Test1(5,"5"));
        list2.add(new Test1(9,"9"));
        list2.add(new Test1(8,"8"));
        list2.add(new Test1(7,"7"));
        list2.add(new Test1(6,"6"));
        Set<Test1> set = new HashSet<>();
        set.addAll(list1);
        for (Test1 test:list2) {
            if (!set.contains(test)){
                list1.add(test);
            }
        }
        for (Test1 test:list1) {
            System.out.print(test.toString()+"\n");
        }
    }

}
