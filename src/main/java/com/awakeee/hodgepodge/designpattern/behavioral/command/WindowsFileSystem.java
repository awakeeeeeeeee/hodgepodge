package com.awakeee.hodgepodge.designpattern.behavioral.command;

public class WindowsFileSystem implements FileSystem {
    @Override
    public void openFile() {
        System.out.println("windows system open file ...");
    }

    @Override
    public void writeFile() {
        System.out.println("windows system write file ...");
    }

    @Override
    public void closeFile() {
        System.out.println("windows system close file ...");
    }
}
