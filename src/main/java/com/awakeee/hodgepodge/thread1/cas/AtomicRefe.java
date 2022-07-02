package com.awakeee.hodgepodge.thread1.cas;


import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefe {

    public static void main(String[] args) {

        AtomicReference<Simple> reference = new AtomicReference<>(new Simple("aa",2));
        boolean result = reference.compareAndSet(new Simple("aa",2),new Simple("bb",3));
        System.out.println(result);

        boolean result1 = reference.compareAndSet(reference.get(),new Simple("bb",3));
        System.out.println(result1);
    }

}

class Simple{
    String name;
    int age;

    public Simple(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
