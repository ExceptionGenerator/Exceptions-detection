package com.shiv.exception.rd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private final PrintWriter printWriter;

    public Main() throws FileNotFoundException {
        printWriter  =new PrintWriter("C://Users//Dell//Desktop//detaur//Local-Activator_Files.xlsx");
    }

    /**
     * Input: n = 3
     * Output: ["((()))","(()())","(())()","()(())","()()()"]
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        final String openedParenthesis="(";
        final String closedParenthesis=")";
        final List<String> generatedParenthesis=new ArrayList<>();
        final String inputParenthesis=addAsInputParenthesis(n);
        System.out.println(inputParenthesis);
        return generatedParenthesis;
    }

    private static String addAsInputParenthesis(int n) {
        final String openedParenthesis = "(";
        final String closedParenthesis = ")";
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n * 2; i++) {
            if (i < n)
                stringBuilder.append(openedParenthesis);
            else
                stringBuilder.append(closedParenthesis);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(generateParenthesis(8));
//        new ExceptionDetection("C://Users//Dell//Desktop//Logs//logs//logs//");
//        Main main1= new Main();
//        main1.test1(new File("C://Users//Dell//Desktop//detaur//java"));
//        main1.printWriter.close();
//        System.out.println("Done");
//        String fileName=System.getProperty("user.dir")+"//testdir";
//        File file=new File(fileName);
//        System.out.println("File created status-"+file.mkdirs());

    }
    public void test1(File file) throws IOException {
        File[] files=file.listFiles();
        if(files!=null) for(File file1:files)
            if(file1.isFile() && file1.getName().endsWith(".java")){
                String fileName= file1.getName();
                fileName=fileName.substring(0,fileName.length()-5);
                String packageName=file1.getParent();
                if(packageName.contains("com"))
                    packageName=packageName.substring(packageName.indexOf("com"));
                else if(packageName.contains("org"))
                    packageName=packageName.substring(packageName.indexOf("org"));
                packageName= packageName.replace(File.separator,".");
                System.out.println(packageName+" \t "+fileName);
                writeInToFile(packageName+" \t "+fileName+"\n");
            }
        else
            test1(file1);
    }

    /**
     * writing into a file
     * @param fileName
     */
    public void writeInToFile(String fileName){
        printWriter.append(fileName);
        printWriter.flush();
    }
}