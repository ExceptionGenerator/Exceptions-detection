package com.shiv.exception.research;

public class StackImpl<T> {
    private int stackSize;
    private T[] stackArray;
    private int top;

    public StackImpl(int stackSize){
        this.stackSize=stackSize;
        stackArray=(T[]) new Object[stackSize];
        this.top=-1;
    }

    public void push(T data){
        if(top==stackSize-1){
            System.out.println("Stack has full");
            return;
        }
        stackArray[++top]=data;
    }

    public void pop(){
        if(top==-1){
            System.out.println("Stack has empty");
            return;
        }
        stackArray[top--]=null;
    }

    public void show(){
        for(int i=0;i<=top;i++){
            System.out.print(stackArray[i]+"\t");
        }
        System.out.println();
    }

    public void increaseStackSize(int newStackSize){
        this.stackSize=newStackSize;
        T[] tempArray=(T[]) new Object[stackSize];
        for(int i=0;i<stackArray.length;i++)
            tempArray[i]=stackArray[i];
        stackArray=tempArray;
    }

    public static void main(String[] args) {
        StackImpl<Integer> stack=new StackImpl(5);
        stack.pop();
        stack.push(12);
        stack.push(13);
        stack.push(14);
        stack.push(15);
        stack.increaseStackSize(10);
        stack.push(16);
        stack.push(17);
        stack.push(18);
        stack.push(19);
        stack.push(20);
        stack.push(21);
        stack.show();
        stack.pop();
        stack.show();
        stack.pop();
        stack.show();
    }
}
