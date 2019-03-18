package com.awakeee.hodgepodge.designpattern.adapter;

public interface SourceInterface {

    void requestSource();



    class SouceInterfaceImpl implements SourceInterface{

        @Override
        public void requestSource() {
            System.out.println("source do something!!!");
        }
    }
}
