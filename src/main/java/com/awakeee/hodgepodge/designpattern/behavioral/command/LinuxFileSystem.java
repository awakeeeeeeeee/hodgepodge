package com.awakeee.hodgepodge.designpattern.behavioral.command;

public class LinuxFileSystem implements FileSystem {
    @Override
    public void openFile() {
        System.out.println("linux system open file ...");
    }

    @Override
    public void writeFile() {
        System.out.println("linux system write file ...");
    }

    @Override
    public void closeFile() {
        System.out.println("linux system close file ...");
    }
}
