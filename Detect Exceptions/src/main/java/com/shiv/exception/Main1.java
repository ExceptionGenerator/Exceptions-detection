package com.shiv.exception;
interface A{
    default void test() {
        System.out.println("inside test");
    }
}
public class Main1 implements A{
    public static void main(String[] args) {
        A a=new Main1();
        a.test();
    }
}
