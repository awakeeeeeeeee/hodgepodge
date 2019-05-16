package com.awakeee.hodgepodge.datastructure.sort;

/**
 * 直接插入排序：
 *  6     直接插入排序就是从待排序列中选出一个元素，插入到已经有序的元素之中，直到所有的元素都插入到有序序列中所有的元素就全部
 *  7 有序了。
 *  8     通常的做法就是将第一个元素看做是有序的元素（即待排序列的第一个元素看做是有序序列），然后我们将第二个元素和有序序列（即
 *  9 第一个元素）作比较，按正确的序列插入到序列中去。然后在将第三个元素和前面有序序列（即整个待排序列的前两个元素）作比较，将第
 * 10 三个插入到前两个元素中去，使得前三个元素有序。以此类推，直到所有的元素都有序。
 */

public class InsertionSort extends SortUtil{

    public static void sort(Comparable[] a){
        for(int i=1;i<a.length;i++){
//            for(int j=i;j>0 && less(a[j],a[j-1]);j--){
//                Comparable m = a[j];
//                Comparable n = a[j-1];
//                exch(a,j,j-1);
//            }
            for(int j=i;j>0;j--){   //a[i]左边的部分已经是有序的，将右边的每一个依次同左边的进行比较，并按照顺序插入其中,即交换位置达到目的
                Comparable m = a[j];
                Comparable n = a[j-1];
                if(less(a[j],a[j-1])){
                    exch(a,j-1,j);
                }
            }
        }

    }

    public static void main(String[] args) {

        Comparable[] a = new Comparable[]{3,8,9,6,5,4,2,0,1,7};
        sort(a);


        for(Comparable i : a){
            System.out.println(i);
        }
    }

}
