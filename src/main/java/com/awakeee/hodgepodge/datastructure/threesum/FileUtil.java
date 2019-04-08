package com.awakeee.hodgepodge.datastructure.threesum;

import java.io.*;

//读取文件数据 保存到一个数组中
public class FileUtil {

    private int[] arr;

    public FileUtil(int size) {
        this.arr = new int[size];
    }

    public int[] getFile(String filePath){
            try {
                FileReader reader = new FileReader(filePath);
                BufferedReader br = new BufferedReader(reader);
                String line;
                int i=0;
                int k=0;
                while((line = br.readLine()) != null){
                    arr[i++] = Integer.valueOf(line.trim());
                    k++;
                }
                System.out.println("k:"+k);
                return arr;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }


    public static void main(String[] args) {

        FileUtil fileUtil = new FileUtil(8000);
        int[] arr  = fileUtil.getFile("/Users/cool/Downloads/algs4-data/8Kints.txt");
        System.out.println(arr.length);

    }
}
