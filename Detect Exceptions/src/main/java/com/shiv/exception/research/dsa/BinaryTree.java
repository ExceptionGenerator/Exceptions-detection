package com.shiv.exception.research.dsa;

class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>>{
    TreeNode<T> leftNode;
    TreeNode<T> rightNode;
    T data;

    public TreeNode(T data){
        this.data=data;
    }


    @Override
    public int compareTo(TreeNode<T> o) {
        return data.compareTo(o.data);
    }
}
public class BinaryTree<T extends Comparable<T>> {
    public void addNode(TreeNode rootNode,T data){
        if(rootNode.data.compareTo(data)>0){
            if(rootNode.leftNode==null){
                System.out.println(data+" inserted left at "+rootNode.data);
                rootNode.leftNode=new TreeNode<>(data);
            }
            else
                addNode(rootNode.leftNode,data);
        }else if(rootNode.data.compareTo(data)<0){
            if(rootNode.rightNode==null){
                System.out.println(data+" inserted right at "+rootNode.data);
                rootNode.rightNode=new TreeNode<>(data);
            }
            else
                addNode(rootNode.rightNode,data);
        }
    }

    /**
     * in-order traversal in binary tree
     * @param rootNode
     */
    public void inOrderTraversal(TreeNode rootNode){
        if(rootNode!=null){
            inOrderTraversal(rootNode.leftNode);
            System.out.print(rootNode.data+" ");
            inOrderTraversal(rootNode.rightNode);
        }
    }

    public void preOrderTraversal(TreeNode rootNode){
        if(rootNode!=null){
            System.out.print(rootNode.data+" ");
            preOrderTraversal(rootNode.leftNode);
            preOrderTraversal(rootNode.rightNode);
        }
    }

    public void postOrderTraversal(TreeNode rootNode){
        if(rootNode!=null){
            postOrderTraversal(rootNode.leftNode);
            postOrderTraversal(rootNode.rightNode);
            System.out.print(rootNode.data+" ");
        }
    }

    public TreeNode<T> deleteNode(TreeNode<T> rootNode,T key){
        if(rootNode==null){
            return rootNode;
        }
        /* If key data greater than tree data */
        if(key.compareTo(rootNode.data)>0)
            rootNode.rightNode=deleteNode(rootNode.rightNode,key);
            /* If key data less than tree data */
        else if(key.compareTo(rootNode.data)<0)
            rootNode.leftNode=deleteNode(rootNode.leftNode,key);
        else{ /* If key data equal to tree data */
            if(rootNode.leftNode==null)
                return rootNode.rightNode;
            else if(rootNode.rightNode==null)
                return rootNode.leftNode;
            /* If key data node have two child */
            rootNode.data=getMin(rootNode.rightNode);
            rootNode.rightNode=deleteNode(rootNode.rightNode, rootNode.data);
        }
        return rootNode;
    }

    private T getMin(TreeNode<T> rootNode){
        T data=rootNode.data;
        while (rootNode.leftNode!=null){
            data= rootNode.data;
            rootNode=rootNode.leftNode;
        }
        return data;
    }

    public static void main(String[] args) {
        TreeNode rootNode=new TreeNode<>(12);
        BinaryTree<Integer> binaryTree=new BinaryTree<>();
        binaryTree.addNode(rootNode,10);
        binaryTree.addNode(rootNode,11);
        binaryTree.addNode(rootNode,11);
        binaryTree.addNode(rootNode,13);
        binaryTree.addNode(rootNode,14);
        binaryTree.addNode(rootNode,5);
        binaryTree.addNode(rootNode,4);
        binaryTree.addNode(rootNode,3);
        binaryTree.addNode(rootNode,20);
        binaryTree.inOrderTraversal(rootNode);
        binaryTree.deleteNode(rootNode,20);
        System.out.println();
        binaryTree.inOrderTraversal(rootNode);
        System.out.println();
        binaryTree.preOrderTraversal(rootNode);
        System.out.println();
        binaryTree.postOrderTraversal(rootNode);
    }
}
