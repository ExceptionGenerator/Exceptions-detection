package com.shiv.exception.research;

import java.util.ArrayList;
import java.util.List;
class Counter{
    private int count=0;

    public void increment(){
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Random {
    static int count=0;

    public static void increment(){
        count=count+1;
    }

    private final static List<String> names=new ArrayList<>();
    public static void main(String[] args) throws Throwable{
        Counter counter=new Counter();
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=1000;i++)
                    counter.increment();
            }
        },"thread1");

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=1000;i++)
                    counter.increment();
            }
        },"thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
//        System.out.println(names);
        System.out.println(counter.getCount());
    }
}
