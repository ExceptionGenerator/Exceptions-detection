package com.shiv.exception.research;

class Node<T>{
    Node next;
    T data;
    public Node(T data){
        this.data=data;
    }
}

public class LinkedListImpl<T> {
    private Node head;
    private int length;

    public LinkedListImpl(){
        head=null;
    }

    public void add(T data){
        Node node=new Node<>(data);
        if(head==null)
            head=node;
        else{
            Node tempNode=head;
            while(tempNode.next!=null)
                tempNode=tempNode.next;
            tempNode.next=node;
        }
        length++;
    }

    /**
     * Remove nodes according to LIFO
     */
    public void remove(){
        if(length==0){
            System.out.println("Linked list has empty");
            return;
        }
        else if(length==1)
            head=null;
        else{
            Node tempNode=head;
            Node prevNode=head;
            while (tempNode.next!=null){
                tempNode=tempNode.next;
                if(tempNode.next!=null)
                    prevNode=tempNode;
            }
            prevNode.next=null;
        }
        length--;
    }

    public void show(){
        StringBuilder stringBuilder=new StringBuilder("{");
        if(length==0){
            System.out.println("Linked list has empty");
            return;
        }else {
            Node tempNode=head;
            while (tempNode.next!=null){
                stringBuilder.append(tempNode.data).append(",");
                tempNode=tempNode.next;
            }
            stringBuilder.append(tempNode.data);
            stringBuilder.append("}");
        }
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        LinkedListImpl<Integer> linkedList=new LinkedListImpl<>();
        linkedList.add(11);
        linkedList.add(12);
        linkedList.add(13);
        linkedList.add(14);
        linkedList.add(15);
        linkedList.show();
        linkedList.remove();
        linkedList.show();
    }
}
