package com.shiv.exception.research;

class LinkNode<T>{
    protected LinkNode prevNode;
    protected LinkNode nextNode;
    protected T data;

    public LinkNode(T data){
        this.data=data;
    }
}

public class DoublyLinkedList<T> {
    /* root node */
    private LinkNode head;
    /* leaf node */
    private LinkNode tail;
    private int length;

    /**
     * add a new node
     * @param data
     */
    public void addNode(T data){
        LinkNode node=new LinkNode<>(data);
        if(length==0){
            head=node;
        }
        else {
            tail.nextNode=node;
            node.prevNode=tail;
        }
        tail=node;
        length++;
    }

    /**
     * deletion from last node [LIFO]
     */
    public void remove(){
        if(length==1){
            head=null;
            tail=null;
        }
        else {
            tail=tail.prevNode;
            tail.nextNode=null;
        }
        length--;
    }

    public int length(){
        return length;
    }

    /**
     * back traversal
     */
    public void show(){
        StringBuilder stringBuilder=new StringBuilder("{");
        LinkNode tempNode=tail;
        while (tempNode.prevNode!=null){
            stringBuilder.append(tempNode.data).append(",");
            tempNode=tempNode.prevNode;
        }
        stringBuilder.append(tempNode.data).append("}");
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList=new DoublyLinkedList();
        doublyLinkedList.addNode(10);
        doublyLinkedList.addNode(11);
        doublyLinkedList.addNode(12);
        doublyLinkedList.addNode(13);
        doublyLinkedList.addNode(14);
        doublyLinkedList.addNode(15);
        doublyLinkedList.addNode(16);
        doublyLinkedList.addNode(17);
        doublyLinkedList.addNode(18);
        doublyLinkedList.addNode(19);
        doublyLinkedList.show();
        System.out.println("Length-"+doublyLinkedList.length());
        doublyLinkedList.remove();
        doublyLinkedList.show();
        System.out.println("Length-"+doublyLinkedList.length());
    }
}
