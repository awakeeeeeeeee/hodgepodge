package com.awakeee.hodgepodge.datastructure.array;

//固定大小容量的栈 数组实现
public class FixedCapacityStack<Item> {

    private Item[] items;

    private int size;

    public FixedCapacityStack(int size) {
        items = (Item[]) new Object[size];
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void push(Item item){
        items[size] = item;
        size++;

        // item[size++] = item;
    }

    public void pop(Item item){//删除最近添加的元素
        items[--size] = item;
    }


    public static void main(String[] args) {
        FixedCapacityStack<Integer> fixedCapacityStack = new FixedCapacityStack<>(10);
        for(int i=0;i<10;i++){
            fixedCapacityStack.push(i);
        }
        System.out.println(fixedCapacityStack.size);
        fixedCapacityStack.pop(9);

    }
}
