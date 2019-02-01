package com.awakeee.hodgepodge.thread.communication;

import org.junit.Test;

public class ShareData {

    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }


    @Test
    public void testShare() {

        ShareData shareData = new ShareData();
        Processer p = new Processer(shareData);
        for(int i=0;i<3;i++){
            Thread t = new Thread(p);
            t.start();
        }
    }












    class Processer implements Runnable{

        private ShareData shareData;

        public Processer(ShareData shareData){
            this.shareData = shareData;
        }

        @Override
        public void run() {
            shareData.setFlag(false);
        }
    }
}
