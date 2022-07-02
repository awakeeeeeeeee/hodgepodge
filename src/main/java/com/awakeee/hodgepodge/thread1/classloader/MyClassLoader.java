package com.awakeee.hodgepodge.thread1.classloader;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "/Users/cool/Downloads/myclassloadertest/Myclass.class";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int len=0;
            while ((len =fileInputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer,0,len);
            }
            byteArrayOutputStream.flush();
            byte[] result = byteArrayOutputStream.toByteArray();
            return defineClass(name,buffer,0,result.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

//        Myclass myclass = new Myclass();
//        Myclass myclass1 = new Myclass();
//        System.out.println(myclass.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
//        System.out.println(myclass.getClass().getClassLoader().getParent());//sun.misc.Launcher$ExtClassLoader@682a0b20
//        System.out.println(myclass.getClass().getClassLoader().getParent().getParent());//null
//
        //自定义classloader加载
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> clz = myClassLoader.loadClass("com.awakeee.hodgepodge.classloader.Myclass");//myclass.class中包路径加类名
        System.out.println(clz);
        System.out.println(clz.getClassLoader());

        Method method = clz.getMethod("say",new Class<?>[]{});
        method.invoke(clz.newInstance(),new Object[]{});


    }
}
