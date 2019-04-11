package com.awakeee.hodgepodge.datastructure.union_find;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//quick-find 进阶 union-find
//虚拟树形结构实现 数组的值对应的是同一个树中的另外一个节点的名字
//
//          2
//         / \
//        1   3
//       /
//      4    id[id[4]]=2
// id[1]=2 就表示1和2是一棵树中的不同节点，并且2是1的根节点
public class UF {

    private int[] id;
    private int count;

    public UF(int count) {
        this.count = count;
        this.id = new int[count];
        for(int i=0;i<count;i++){
            id[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public int find(int p){
        while(p != id[p]){
            p = id[p];
        }

        return p;
    }

    public void union(int p,int q){
        int proot = find(p);
        int qroot = find(q);
        if(proot == qroot){
            return;
        }
        id[proot] = qroot;
        count--;
    }

    public boolean connected(int p,int q){
        return find(p) == find(q);
    }

    public static void main(String[] args) {

        Scanner input = null;
        try {
            input = new Scanner(new File("/Users/cool/Downloads/algs4-data/tinyUF.txt"));
            int N=input.nextInt();
            System.out.println(N);
            UF uf = new UF(N);
            while (input.hasNext()){
                int p = input.nextInt();
                int q = input.nextInt();
                if(uf.connected(p,q)){
                    System.out.println(p + " connnected " + q);
                    continue;
                }else {
                    uf.union(p,q);
                    System.out.println(p +" " + q);
                }
            }
            System.out.println(uf.count() + " components");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
