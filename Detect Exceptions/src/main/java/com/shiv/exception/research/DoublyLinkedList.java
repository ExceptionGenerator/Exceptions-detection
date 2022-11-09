package com.shiv.exception.research;

class LinkNode<T extends Comparable<T>> implements Comparable<LinkNode<T>>{
    protected LinkNode prevNode;
    protected LinkNode nextNode;
    protected T data;

    public LinkNode(T data){
        this.data=data;
    }

    public T getT(){
        return data;
    }

    @Override
    public int compareTo(LinkNode<T> o) {
        return getT().compareTo(o.getT());
    }
}

public class DoublyLinkedList<T extends Comparable<T>> {
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
        LinkNode node=new LinkNode<T>(data);
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
        LinkNode tempNode=head;
        while (tempNode.nextNode!=null){
            stringBuilder.append(tempNode.data).append(",");
            tempNode=tempNode.nextNode;
        }
        stringBuilder.append(tempNode.data).append("}");
        System.out.println(stringBuilder.toString());
    }

    /**
     * sorting element in ascending order
     */
    public void sort(){
        LinkNode current=head,index=null;
        Comparable temp;
        if(head==null)
            return;
        while (current!=null){
            index=current.nextNode;
            while (index!=null){
                if(current.data.compareTo(index.data)>0){
                    temp=current.data;
                    current.data=index.data;
                    index.data=temp;
                }
                index=index.nextNode;
            }
            current=current.nextNode;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList=new DoublyLinkedList();
        doublyLinkedList.addNode(2);
        doublyLinkedList.addNode(5);
        doublyLinkedList.addNode(13);
        doublyLinkedList.addNode(14);
        doublyLinkedList.addNode(18);
        doublyLinkedList.addNode(11);
        doublyLinkedList.addNode(1);
        doublyLinkedList.addNode(8);
        doublyLinkedList.addNode(9);
        doublyLinkedList.show();
        doublyLinkedList.sort();
        doublyLinkedList.show();
    }
}
