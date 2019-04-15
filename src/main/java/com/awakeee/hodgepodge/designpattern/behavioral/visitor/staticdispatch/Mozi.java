package com.awakeee.hodgepodge.designpattern.behavioral.visitor.staticdispatch;

public class Mozi {

    //Java通过方法重载支持静态分派。用墨子骑马的故事作为例子，墨子可以骑白马或者黑马。墨子与白马、黑马和马的类图如下所示：
    //静态分配发生在编译时期，两次对ride()方法的调用传入的是不同的参数，也就是wh和bh。它们虽然具有不同的真实类型，但是它们的静态类型都是一样的，均是Horse类型。

    public void ride(Horse h){
        System.out.println("骑马");
    }

    public void ride(WhiteHorse wh){
        System.out.println("骑白马");
    }

    public void ride(BlackHorse bh){
        System.out.println("骑黑马");
    }

    public static void main(String[] args) {
        Horse wh = new WhiteHorse();
        Horse bh = new BlackHorse();
        Mozi mozi = new Mozi();
        mozi.ride(wh);
        mozi.ride(bh);
    }

    static class Horse{

    }

    static class BlackHorse extends Horse{
        public BlackHorse() {
        }

    }

    static class WhiteHorse extends Horse{
        public WhiteHorse() {
        }
    }

}