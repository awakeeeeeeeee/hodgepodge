package com.awakeee.hodgepodge.datastructure.union_find;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//union-find quick-find
// find时间复杂度O（1）
public class QF {

    private int[] arr;

    private int num;//总的连通分量

    public QF(int N) {
        num = N;
        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = i;
        }
    }

    public void union(int p,int q){
        int pid = find(p);//访问频率一次
        int qid = find(q);//访问频率一次
        if(pid == qid){
            return;
        }
        for(int i=0;i<arr.length;i++){//q连q 和q连p效果一样
//            if(arr[i] == pid){
//                arr[i] = qid;
//            }
            if(arr[i] == qid){
                arr[i] = pid;
            }
        }
        num--;//每连通一次，总的连通分量-1
    }

    public int find(int p){
        return arr[p];//访问频率一次
    }

    public boolean connected(int p,int q){
        return find(p) == find(q);
    }

    public int count(){
        return num;
    }


    public static void main(String[] args) {

        Scanner input = null;
        try {
            input = new Scanner(new File("/Users/cool/Downloads/algs4-data/tinyUF.txt"));
            int N=input.nextInt();
            System.out.println(N);
            QF uf = new QF(N);
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
