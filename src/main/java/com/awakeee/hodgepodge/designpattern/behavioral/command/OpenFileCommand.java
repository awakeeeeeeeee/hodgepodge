package com.awakeee.hodgepodge.designpattern.behavioral.command;

public class OpenFileCommand implements Command {

    private FileSystem fileSystem;

    public OpenFileCommand(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        fileSystem.openFile();
    }
}
