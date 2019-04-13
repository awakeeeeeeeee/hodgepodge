package com.awakeee.hodgepodge.designpattern.behavioral.memento;

public class FileWriterUtil {

    private String fileName;

    private StringBuffer content;

    public FileWriterUtil(String fileName) {
        this.fileName = fileName;
        this.content = new StringBuffer();
    }

    @Override
    public String toString() {
        return this.content.toString();
    }

    public void write(String str){
        content.append(str);
    }

    public Memento save(){
        return new Memento(this.fileName,this.content);
    }

    public void undo(Object obj){
        Memento memento = (Memento) obj;
        this.fileName = memento.fileName;
        this.content = memento.content;
    }

    private class Memento{

        private String fileName;

        private StringBuffer content;

        public Memento(String fileName, StringBuffer content) {
            this.fileName = fileName;
            //notice the deep copy so that Memento and FileWriterUtil content variables don't refer to same object
            this.content= new StringBuffer(content);
            //this.content = content;
            //这里如果不new一个，那么会出现fileutil和memento中的两个content引用同一个对象，当第二次write写入时,memento
            //中的content也会被改变
        }
    }
}
