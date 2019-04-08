package com.awakeee.hodgepodge.datastructure.stack;

//栈 入栈和出栈都是在顶端 ,后进先出
public class Stack<Item> {

    private Node first;

    private int size;

    public void push(Node node){
        if(isEmpty()){
            first = node;
            size++;
            return;
        }
        Node oldfirst = first;
        first = node;
        first.setNode(oldfirst);
        size++;
    }

    public void pop(){
        if(isEmpty()){
            throw new IndexOutOfBoundsException("stack size is 0");
        }
        Node oldfirst = first;
        first = oldfirst.getNode();
        oldfirst.setNode(null);
        size--;
    }

    public boolean isEmpty(){
        return size == 0?true:false;
    }

    public int size(){
        return size;
    }

    public Iterator iterator(){
        return new IteratorImpl();
    }


    class IteratorImpl implements Iterator{

        private Node current = first;


        @Override
        public Object next() {  //next方法返回当前节点元素
            Node current1 = current;
            current = current.getNode();
            return current1;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public void remove() {

        }
    }

}
