package com.shiv.exception.rd;

public class QueueImpl<T> {
    private T[] queue;
    private int front;
    private int rear;
    private int queueSize;

    public QueueImpl(int queueSize){
        queue= (T[]) new Object[queueSize];
        front=-1;
        rear=-1;
        this.queueSize=queueSize;
    }

    public void enque(T data){
        if(rear==queueSize-1){
            System.out.println("Queue has full");
            return;
        }
        queue[++rear]=data;
        front++;
    }
}
