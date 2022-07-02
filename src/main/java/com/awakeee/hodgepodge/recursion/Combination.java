package com.awakeee.hodgepodge.recursion;

import java.util.*;

public class Combination {

    //组合[1,2,3,4,5]

    public static void combination(List<Integer> selected,List<Integer> datas, int n){

        if(n == 0){
           for(Integer i : selected){
               System.out.print(i);
               System.out.print(" ");
           }
            System.out.println("");
           return;
        }

        if(datas.isEmpty()){
            return;
        }

        //select element 0 选择0号元素
        selected.add(datas.get(0));
        combination(selected,datas.subList(1,datas.size()),n-1);

        //un-select element 0 不选择0号元素
//        selected.remove(selected.size()-1);
        //也可以
        selected.remove(datas.get(0));
        combination(selected,datas.subList(1,datas.size()),n);
    }

    //还未实现
    public static void combinationwithfor(List<Integer> selected,List<Integer> datas, int n){


        Integer sum =  getCombinationValue(datas.size(),n);


        int index = 0;
        int fromindex = 0;
        int temp = 0;
        Map<Integer,List<Integer>> map = new HashMap<>();


        while(index < sum){
            List<Integer> list = new ArrayList<>();

            for(int i=fromindex;i<datas.size();i++){

                if(list.size()<n){
                    temp = datas.get(i);
                    list.add(datas.get(i));
                    if(list.size() == n){
                        if(hasChoose(list,map)){
                            list.remove(datas.get(i));
                        }
                    }
                }
            }

            if(temp == datas.size()){
//                System.out.println("threadlocal:"+threadlocal.toString());
                fromindex++;
            }

            map.put(index,list);
            index ++;
        }

        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            List<Integer> list = entry.getValue();
            System.out.print(entry.getValue());
            System.out.print(" ");
            System.out.println("");
        }
    }

    public static List<String[]> combinationwithfor1(String[] a , int m){
        int n = a.length;
        if(m>n){
            return null;
        }
        if(m==n){
            ArrayList<String[]> ints = new ArrayList<>();
            ints.add(a);
            return ints;
        }

        List<String[]> result = new ArrayList<>();

        String[] bs = new String[n];
        for(int i=0;i<n;i++){
            bs[i]="0";
        }
        //初始化
        for(int i=0;i<m;i++){
            bs[i]="1";
        }
        boolean flag = true;
        boolean tempFlag = false;
        int pos ,sum;

        do{
            sum = 0;
            pos = 0;
            tempFlag = true;
            result.add(print(bs,a,m));

            for(int i=0;i<n-1;i++){
                if(Objects.equals(bs[i], "1") && Objects.equals(bs[i + 1], "0")){
                    bs[i]="0";
                    bs[i+1]="1";
                    pos = i;
                    break;
                }
            }
            //将左边的1全部移动到数组的最左边
            for(int i=0;i<pos;i++){
                if(Objects.equals(bs[i], "1")){
                    sum++;
                }
            }
            for(int i=0;i<pos;i++){
                if(i<sum){
                    bs[i]="1";
                }else{
                    bs[i]="0";
                }
            }

            //检查是否所有的1都移动到了最右边
            for(int i= n-m;i<n;i++){
                if(Objects.equals(bs[i], "0")){
                    tempFlag = false;
                    break;
                }
            }
            flag = !tempFlag;

        }while(flag);
        result.add(print(bs,a,m));

        return result;
    }

    private static String[] print(String[] bs,String[] a,int m){
        String [] result = new String[m];
        int pos= 0;
        for(int i=0;i<bs.length;i++){
            if(Objects.equals(bs[i], "1")){
                result[pos]=a[i];
                pos++;
            }
        }
        return result ;
    }


    private static Integer getFactorialValue(int num) {
        int res = 1;
        for (int i = 1; i <= num; i++) {
            res = res * i;
        }
        return res;
    }

    /**
     * 组合个数
     *
     * @param fromNum
     * @param getNum
     * @return
     */
    public static Integer getCombinationValue(int fromNum, int getNum) {
        return getFactorialValue(fromNum) / (getFactorialValue(getNum) * getFactorialValue(fromNum - getNum));
    }

    public static boolean hasChoose(List<Integer> choose,Map<Integer,List<Integer>> map){
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            List<Integer> list = entry.getValue();
            if(compare(list,choose)){
                return true;
            }
        }
        return false;
    }

    public static boolean compare(List<Integer> list,List<Integer> choose){
        Integer[] arr = list.toArray(new Integer[list.size()]);
        Integer[] arr1 = choose.toArray(new Integer[choose.size()]);
        boolean flag = false;
        if(arr.length != arr1.length){

            return false;
        }

        for(int i=0;i<arr.length;i++){
            if(arr[i].intValue() != arr1[i].intValue()){
                flag = false;
                break;
            }else {
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
//        Combination.combination(new ArrayList<>(),Arrays.asList(1,2,3,4,5),3);
        System.out.println("=========");
        Combination.combinationwithfor1(new String[]{"1","2","3","4","5"},3);
    }
}
