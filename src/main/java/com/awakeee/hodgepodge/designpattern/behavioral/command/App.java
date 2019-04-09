package com.awakeee.hodgepodge.designpattern.behavioral.command;

public class App {

    public static void main(String[] args) {

      int i = 1;
      FileSystem fileSystem;
      if(i == 0){
          fileSystem = new WindowsFileSystem();
      }else{
          fileSystem = new LinuxFileSystem();
      }

      OpenFileCommand openFileCommand = new OpenFileCommand(fileSystem);
      WriteFileCommand writeFileCommand = new WriteFileCommand(fileSystem);
      FileInvoker fileInvoker = new FileInvoker(openFileCommand);
      fileInvoker.execute();
      fileInvoker = new FileInvoker(writeFileCommand);
      fileInvoker.execute();




    }
}
