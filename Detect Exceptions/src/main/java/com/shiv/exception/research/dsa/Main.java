package com.shiv.exception.research.dsa;

public class Main {
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
        DoublyLinkedList<Integer> doublyLinkedList1=new DoublyLinkedList<>();
        doublyLinkedList1.addNode(126);
        doublyLinkedList1.addNode(186);
        doublyLinkedList1.addNode(120);
        doublyLinkedList1.addNode(123);
        doublyLinkedList1.addNode(124);
        doublyLinkedList1.addNode(125);
        System.out.println(doublyLinkedList);
        System.out.println(doublyLinkedList1);
        System.out.println(addTwoList(doublyLinkedList,doublyLinkedList1));
    }

    /**
     * add two linked list without changing its order
     * @param doublyLinkedList1
     * @param doublyLinkedList2
     * @return
     */
    public static DoublyLinkedList<Integer> addTwoList(DoublyLinkedList<Integer> doublyLinkedList1, DoublyLinkedList<Integer> doublyLinkedList2){
        LinkNode<Integer> tempNode= doublyLinkedList2.getHeadNode();
        while (tempNode!=null){
            doublyLinkedList1.addNode(tempNode.data);
            tempNode=tempNode.nextNode;
        }
        return doublyLinkedList1;
    }

}
