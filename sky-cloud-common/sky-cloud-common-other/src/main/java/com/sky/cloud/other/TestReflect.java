package com.sky.cloud.other;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class TestReflect {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {

        Test1 te1 = new Test1();

        Class<Test1> cla = Test1.class;//获取Class对象

        Field f1 = cla.getDeclaredField("a");
        Field f2 = cla.getDeclaredField("s");
        Field f3 = cla.getDeclaredField("C");

        f1.setAccessible(true);
        f2.setAccessible(true);
        f3.setAccessible(true);

        System.out.println("a = "+f1.get(te1));
        System.out.println("s = "+f2.get(Test1.class));
        System.out.println("C = "+f3.get(te1));

        System.out.println("========================");

        f1.set(te1, 2);
        f2.set(Test1.class, "sss");
        f3.set(te1, 2);

        System.out.println("a = "+f1.get(te1));
        System.out.println("s = "+f2.get(Test1.class));
        System.out.println("C = "+f3.get(te1));

        System.out.println("========================");

        System.out.println(te1.getA());
        System.out.println(te1.getS());
        System.out.println(te1.getC());

    }
}

class Test1{

    private int a = 1;
    private static String s = "s";
    private final int C = 5;

    public int getC(){
        return C;
    }
    public String getS(){
        return s;
    }
    public int getA(){
        return a;
    }
}
