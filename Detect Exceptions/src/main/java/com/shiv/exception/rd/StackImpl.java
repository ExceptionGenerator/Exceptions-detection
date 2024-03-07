package com.shiv.exception.rd;

public class StackImpl<T> {

    private int stackSize;
    private T[] stack;
    private int tos=-1;

//    @IDCResource(displayName = "Shiv", resourceCode = "DTR", getStatus = true)
    public StackImpl(int stackSize){
        stack= (T[]) new Object[stackSize];
        this.stackSize=stackSize;
    }

    public void push(T data){
        if(tos==stackSize-1)
            return;
        stack[++tos]=data;
    }

    public void pop(){
        if(tos==-1)
            return;
        stack[tos--]=null;
    }

    public String getAllData(){
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<=tos;i++)
            stringBuilder.append(stack[i]).append("\t");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        StackImpl<Integer> stack1=new StackImpl<>(56);
        stack1.push(25);
        stack1.push(26);
        stack1.push(225);
        stack1.push(2);
        stack1.push(5);
        System.out.println(stack1.getAllData());
        stack1.pop();
        System.out.println(stack1.getAllData());
    }
}
