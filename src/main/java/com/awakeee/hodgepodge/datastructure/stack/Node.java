package com.awakeee.hodgepodge.datastructure.stack;

public class Node<Item> {

    private Item item;

    private Node<Item> node;

    public Node(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Node<Item> getNode() {
        return node;
    }

    public void setNode(Node<Item> node) {
        this.node = node;
    }
}
