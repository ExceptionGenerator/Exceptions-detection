package com.shiv.exception.research;

import lombok.ToString;

@ToString
public class BinaryTreeImpl {
    @ToString
    static class Node{
        static int value;
        private Node leftNode,rightNode;
        public Node(int value){
            this.value=value;
            leftNode=null;
            rightNode=null;
        }
    }

    public void insertNode(Node node,int value){
        if(value>Node.value){
            if(node.rightNode==null){
                System.out.println(value+" is inserted right of "+Node.value);
                node.rightNode=new Node(value);
            }else
                System.out.println(value+" is inserted right of "+Node.value);
        }
        else{
            if(node.leftNode==null){
                System.out.println(value+" is inserted left of "+Node.value);
                node.leftNode=new Node(value);
            }
            else
                System.out.println(value+" is inserted left of "+Node.value);
        }
    }

    public static void main(String[] args) {
        BinaryTreeImpl binaryTree=new BinaryTreeImpl();
        Node rootNode=new Node(10);
        binaryTree.insertNode(rootNode,1);
        binaryTree.insertNode(rootNode,5);
        binaryTree.insertNode(rootNode,2);
        binaryTree.insertNode(rootNode,3);
        binaryTree.insertNode(rootNode,8);
        System.out.println(binaryTree);
    }
}
