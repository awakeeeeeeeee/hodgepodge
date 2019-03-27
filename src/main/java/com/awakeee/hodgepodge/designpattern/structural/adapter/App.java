package com.awakeee.hodgepodge.designpattern.structural.adapter;

public class App {

    public static void main(String[] args) {

        TargetInterface targetInterface = new TargetInterfaceAdapter(new SourceInterface.SouceInterfaceImpl());
        targetInterface.requestTarget();

        //将sourceInterface适配成新的targerinterface



    }
}
