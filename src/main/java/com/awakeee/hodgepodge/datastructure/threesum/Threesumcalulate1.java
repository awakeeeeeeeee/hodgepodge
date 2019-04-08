package com.awakeee.hodgepodge.datastructure.threesum;

import com.awakeee.hodgepodge.datastructure.stopwatch.Stopwatch;

public class Threesumcalulate1 {


    //计算出来的有重复 需要将num除3才能得到正确的组合数
    //最内层的for循环执行次数为:n*(n-1)*(n-2)/6
    //除6的原因是三个整数可以按照不同的顺序进行组合,组合的次数为3*2
    private int cal(int[] a){
        int num = 0;
        for(int i=0;i<a.length;i++){
            for(int k=i+1;k<a.length;k++){
                for(int j=0;j<a.length;j++){
                    if(a[i] + a[k] + a[j] == 0){
                        num+=1;
                        //System.out.println("a[i]="+a[i]+" a[k]="+a[k]+" a[j]="+a[j]);
                    }
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil(1000);
        int[] arr = fileUtil.getFile("/Users/cool/Downloads/algs4-data/1Kints.txt");
        Threesumcalulate1 cal = new Threesumcalulate1();
        Stopwatch stopwatch = new Stopwatch();
        int num = cal.cal(arr);
        double time = stopwatch.elapsedTime();
        System.out.println(num + " triples " + time + "seconds");

    }

}
