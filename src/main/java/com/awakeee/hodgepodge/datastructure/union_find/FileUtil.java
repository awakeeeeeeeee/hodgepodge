package com.awakeee.hodgepodge.datastructure.union_find;

import java.io.*;
import java.util.Scanner;

//读取文件中的整数
public class FileUtil {

    public void getFile(String filePath){
        try {
            Scanner input = new Scanner(new File(filePath));
            int N=input.nextInt();
            while (input.hasNext()){
                int p = input.nextInt();
                int q = input.nextInt();
                System.out.println(p + " " + q);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        FileUtil fileUtil = new FileUtil();
        fileUtil.getFile("/Users/cool/Downloads/algs4-data/tinyUF.txt");


    }
}
