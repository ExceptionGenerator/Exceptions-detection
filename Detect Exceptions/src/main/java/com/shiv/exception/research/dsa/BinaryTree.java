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

    public TreeNode<T> findByKeyInPostOrder(TreeNode<T> rootNode,T key){
        if(rootNode!=null){
            if(rootNode.data.compareTo(key)==0)
                return rootNode;
            else{
                return findByKeyInPostOrder(rootNode.leftNode,key);
            }
        }
        else
            return null;
//        return rootNode;
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

    public TreeNode<T> delete(TreeNode<T> rootNode,T key){
        if(rootNode==null)
            return rootNode;
        if(key.compareTo(rootNode.data)>0)
            rootNode.rightNode=deleteNode(rootNode.rightNode,key);
        else if(key.compareTo(rootNode.data)<0)
            rootNode.leftNode=deleteNode(rootNode.leftNode,key);
        else {
            if(rootNode.leftNode==null)
                return rootNode.rightNode;
            else if(rootNode.rightNode==null)
                return rootNode.leftNode;
            rootNode.data=getMin(rootNode.rightNode);
            rootNode.rightNode=deleteNode(rootNode.rightNode,rootNode.data);
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

    public int maxDepth(TreeNode<T> rootNode){
        if(rootNode==null)
            return 0;
        else {
            int lDepth=maxDepth(rootNode.leftNode);
            int rDepth=maxDepth(rootNode.rightNode);
            System.out.println("data-"+rootNode.data);
            if(lDepth>rDepth)
                return lDepth+1;
            else
                return rDepth+1;
        }
    }

    public static void main(String[] args) {
        TreeNode rootNode=new TreeNode<>(100);
        BinaryTree<Integer> binaryTree=new BinaryTree<>();
        binaryTree.addNode(rootNode,90);
        binaryTree.addNode(rootNode,80);
        binaryTree.addNode(rootNode,120);
        binaryTree.addNode(rootNode,128);
        binaryTree.addNode(rootNode,119);
        binaryTree.addNode(rootNode,91);
        binaryTree.preOrderTraversal(rootNode);
        System.out.println();
        TreeNode data= binaryTree.deleteNode(rootNode,80);
        System.out.println(data.data);
        binaryTree.preOrderTraversal(rootNode);
//        Integer a=5;
//        Integer b=2;
//        System.out.println(a.compareTo(b));
    }
}
