package com.shiv.exception.research.dsa;

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

    /**
     * deletion of a node of the given key [if key matched with node data]
     */
    public boolean remove(T key){
        LinkNode<T> tempNode=head;
        if(head!=null){
            while (tempNode!=null){
                if(tempNode.data.compareTo(key)==0){
                    if(tempNode.prevNode==null)
                        head=tempNode.nextNode;
                    else
                        tempNode.prevNode.nextNode=tempNode.nextNode;
                    if(tempNode.nextNode==null)
                        tail=tempNode.prevNode;
                    else
                        tempNode.nextNode.prevNode=tempNode.prevNode;
                    length--;
                    return true;
                }
                tempNode=tempNode.nextNode;
            }
        }
        return false;
    }

    public int length(){
        return length;
    }

    /**
     * back traversal
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder("{");
        LinkNode tempNode=head;
        while (tempNode!=null){
            stringBuilder.append(tempNode.data);
            tempNode=tempNode.nextNode;
            if(tempNode!=null)
                stringBuilder.append(",");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
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

    public LinkNode<T> getTailNode(){
        return tail;
    }

    public LinkNode<T> getHeadNode(){
        return head;
    }

}
