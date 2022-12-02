package com.shiv.exception.research.dsa;

public class QueueImpl<T> {
    private int rear;
    private int front;
    private int queueSize;
    private T[] queueArray;

    public QueueImpl(int queueSize){
        this.queueSize=queueSize;
        queueArray=(T[]) new Object[queueSize];
        rear=-1;
        front=-1;
    }

    public void enqueue(T data){
        if(front==0 && rear==queueSize-1){
            System.out.println("Queue has full");
            return;
        }
        if(front==-1)
            front=0;
        queueArray[++rear]=data;
    }

    public void dequeue(){
        if(front==-1){
            System.out.println("Queue has empty");
            return;
        }
        if(front>=rear){
            /*If both pointer has same index then we need to reset the queue*/
            front=-1;
            rear=-1;
        }else
            queueArray[front++]=null;
    }

    public void show(){
        if(front==-1){
            System.out.println("Queue has empty");
            return;
        }
        for(int i=front;i<=rear;i++)
            System.out.print(queueArray[i]+"\t");
        System.out.println();
    }

    public static void main(String[] args) {
        QueueImpl<Integer> queue= new QueueImpl<>(4);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.show();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.show();
    }
}
