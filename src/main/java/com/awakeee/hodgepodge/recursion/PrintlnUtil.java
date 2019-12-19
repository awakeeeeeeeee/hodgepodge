package com.awakeee.hodgepodge.recursion;

public class PrintlnUtil {

    public static void printNode(Node node){
        while(node != null){
            System.out.print(node.getVal());
            System.out.print(" ");
            node = node.getNext();
        }
        System.out.println("");
    }

    public static void size(Node node){
        int i=0;
        while (node != null){
            node = node.getNext();
            i++;
        }
        System.out.println("size:"+i);
    }
}
