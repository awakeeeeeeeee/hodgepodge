package com.awakeee.hodgepodge.datastructure.stack;

public class App {

    public static void main(String[] args) {

        Stack stack = new Stack();


        for(int i=0;i<10;i++){
            Node node = new Node(i);;
            stack.push(node);
        }

        //stack.pop();

        //System.out.println(stack.size());

        Iterator it = stack.iterator();
        while(it.hasNext()){
            Node n = (Node) it.next();
            System.out.println(n.getItem());
        }

    }
}
