package com.awakeee.hodgepodge.datastructure.sort;

//希尔排序
public class ShellSort extends SortUtil{

    public static void sort(Comparable[] a){
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) {
            h = 3*h + 1;
        }

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {

        Comparable[]a = new Comparable[]{3,8,9,6,5,4,2,0,1,7,20};
//        sort(a);
//        show(a);


        int n = a.length;
        System.out.println(n);
        int i = 0;
        while(i<n/3){
            System.out.println("111");
            i++;
        }

    }
}
