package com.awakeee.hodgepodge.designpattern.behavioral.command;

public class WriteFileCommand implements Command{

    private FileSystem fileSystem;

    public WriteFileCommand(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        fileSystem.writeFile();
    }
}
