package com.shiv.exception.research.dsa;

import lombok.AllArgsConstructor;
import lombok.Data;

class Node<T>{
    Node nextNode;
    Node prevNode;
    T data;
    public Node(T data){
        this.data=data;
    }
}
public class SinglyLinkedList<T> {
    private Node head;
    private Node tail;
    private int length;

    public void addNode(T data){
        Node node=new Node<>(data);
        if(head==null){
            head=node;
        }else{
            tail.nextNode=node;
            node.prevNode=tail;
        }
        tail=node;
        length++;
    }

    public void removeNode(){
        if(length==1){
            head=null;
            tail=null;
        }else{
            Node tempNode=tail.prevNode;
            tempNode.nextNode=null;
            tail=tempNode;
        }
        length--;
    }

    public void traverse(){
        StringBuilder stringBuilder=new StringBuilder("{");
        Node tempNode=head;
        while (tempNode.nextNode!=null){
            stringBuilder.append(tempNode.data).append(",");
            tempNode=tempNode.nextNode;
        }
        stringBuilder.append(tempNode.data).append("}");
        System.out.println(stringBuilder);
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList=new SinglyLinkedList<>();
        singlyLinkedList.addNode(11);
        singlyLinkedList.addNode(12);
        singlyLinkedList.addNode(13);
        singlyLinkedList.addNode(14);
        singlyLinkedList.addNode(15);
        singlyLinkedList.traverse();

        SinglyLinkedList<Student> singlyLinkedList1=new SinglyLinkedList<>();
        singlyLinkedList1.addNode(new Student(1,"Shiv1"));
        singlyLinkedList1.addNode(new Student(2,"Shiv2"));
        singlyLinkedList1.addNode(new Student(3,"Shiv3"));
        singlyLinkedList1.addNode(new Student(4,"Shiv4"));
        singlyLinkedList1.addNode(new Student(5,"Shiv5"));
        singlyLinkedList1.traverse();
        singlyLinkedList1.removeNode();
        singlyLinkedList1.removeNode();
        singlyLinkedList1.removeNode();
        singlyLinkedList1.removeNode();
        singlyLinkedList1.traverse();
    }
}

@Data
@AllArgsConstructor
class Student{
    private int id;
    private String name;
}
