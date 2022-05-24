package com.shiv.exception.detection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExceptionDetection {
    private File rootReadDirPath;
    private File rootWritePath;
    private PrintWriter printWriter;

    /**
     * initialize all objects
     * @param rootReadDir read only files whose are present in this dir
     * @throws FileNotFoundException
     */
    public ExceptionDetection(String rootReadDir) throws FileNotFoundException {
        rootReadDirPath=new File(rootReadDir);
        if(!rootReadDirPath.isDirectory()){
            System.out.println("Sorry ! please provide only root directory path");
            System.exit(0);
        }
        rootWritePath=new File(rootReadDirPath.getAbsolutePath()+"//output");
        rootWritePath.mkdirs();
        rootWritePath=new File(rootWritePath.getAbsolutePath()+"//exceptions.log");
        printWriter=new PrintWriter(rootWritePath);
        readAllFiles(rootReadDirPath);
        printWriter.flush();
        printWriter.close();
        System.out.println("Done all operations output file path-"+rootWritePath.getAbsolutePath());
    }

    /**
     * read all files in given directory
     * @param file
     * @throws FileNotFoundException
     */
    private void readAllFiles(File file) throws FileNotFoundException {
        File[] files=file.listFiles();
        if(files!=null)
            for(File file1:files)
                if(file1.isFile() && file1.length()>0)
                    writeExceptions(file1);
    }

    /**
     * check if any exception contain then picked this line and write it into a file
     * @param file
     * @throws FileNotFoundException
     */
    private void writeExceptions(File file) throws FileNotFoundException {
        long exceptionCounter=0;
        System.out.println(file.getName());
        Scanner scanner=new Scanner(new FileInputStream(file));
        while(scanner.hasNextLine()){
            String readLine=scanner.nextLine();
            if(readLine.contains("Exception")){
                exceptionCounter++;
                String output="[File name-"+file.getName()+", Exception name-"+readLine+", In File Exception counter-"+exceptionCounter+"]\n";
                printWriter.append(output);
            }
        }
    }
}