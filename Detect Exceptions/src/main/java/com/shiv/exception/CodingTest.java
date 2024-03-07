package com.shiv.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CodingTest {
    public static void main (String[] args) {
        System.out.println(findDuplicateCharacters("shmohaantrrds"));
//        Node root=new Node(5);
//        BinaryTree binaryTree=new BinaryTree();
//        binaryTree.addNode(root,3);
//        binaryTree.addNode(root,2);
//        binaryTree.addNode(root,6);
//        binaryTree.addNode(root,1);
//        binaryTree.addNode(root,0);
//        binaryTree.addNode(root,-2);
//        System.out.println("Depth-"+binaryTree.maxDepth(root));
//        System.out.println("Node-"+binaryTree.getNode(root,23));
    }

    public static List<Character> findDuplicateCharacters(String text){
        List<Character> characters=new ArrayList<>();
        for(Character character:text.toCharArray()){
            characters.add(character);
        }
        Set<Character> characters1=new HashSet<>();
        return characters.stream()
                .filter(character -> !characters1.add(character))
                .toList();
    }
}

class BinaryTree{
    /**
     * 5 3 2 6
     * @param root
     * @param value
     * @return
     */
    public Node addNode(Node root,int value){
        if(root==null){
            root=new Node(value);
            return root;
        }else if(value>root.value)
            root.right=addNode(root.right,value);
        else if(value<root.value)
            root.left=addNode(root.left,value);
        return root;
    }

    public Node getNode(Node root,int value){
        if(root!=null && root.value==value)
            return root;
        else if(value>root.value)
            return getNode(root.right,value);
        else
            return getNode(root.left,value);
    }

    public int maxDepth(Node root){
        if(root==null){
            return -1;
        }
        int leftDepth=maxDepth(root.left);
        int rightDepth=maxDepth(root.right);
        if(leftDepth>rightDepth)
            return leftDepth+1;
        else
            return rightDepth+1;
    }
}

class Node{
    int value;
    Node left,right;
    public Node(int value){
        this.value=value;
    }
}