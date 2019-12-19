package com.awakeee.hodgepodge.recursion;

public class Node {

    public Node next;

    public final Integer val;

    public Node(Integer val) {
        this.val = val;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Integer getVal() {
        return val;
    }

}
