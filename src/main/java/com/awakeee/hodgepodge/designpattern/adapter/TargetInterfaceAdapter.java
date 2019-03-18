package com.awakeee.hodgepodge.designpattern.adapter;

public class TargetInterfaceAdapter extends Adapter implements TargetInterface{

    private SourceInterface sourceInterface;

    @Override
    void adapter() {
        //可以扩展一些其他的功能
    }

    public TargetInterfaceAdapter(SourceInterface sourceInterface) {
        this.sourceInterface = new SourceInterface.SouceInterfaceImpl();
    }

    @Override
    public void requestTarget() {
        //adapter();
        sourceInterface.requestSource();
    }
}
