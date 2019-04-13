package com.awakeee.hodgepodge.designpattern.behavioral.memento;

public class FileWriteCaretaker {

    private Object object;

    public void save(FileWriterUtil fileWriterUtil){
        this.object = fileWriterUtil.save();
    }

    public void undo(FileWriterUtil fileWriterUtil){
        fileWriterUtil.undo(object);
    }
}
