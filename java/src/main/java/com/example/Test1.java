package com.example;

/**
 * Created by sahara on 2016/12/20.
 */

public class Test1 {
    public int age;
    public String name;

    public Test1(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public boolean equals(Object anObject) {
        if (anObject instanceof Test1) {
            Test1 anotherTest1 = (Test1)anObject;
            if (this.age == anotherTest1.age){
                return true;
            }
//            if (this.age==anotherTest1.age&&this.name.equals(anotherTest1.name)){
//                return true;
//            }
        }
        return false;
    }

    // 根据 age 计算 Test1 对象的 hashCode() 返回值
    public int hashCode()
    {
        return ((Integer)age).hashCode();
    }

    @Override
    public String toString() {
        return "Test1{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
