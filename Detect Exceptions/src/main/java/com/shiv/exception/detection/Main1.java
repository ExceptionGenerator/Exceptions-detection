package com.shiv.exception.detection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main1 {
    private PrintWriter printWriter;

    public Main1() throws FileNotFoundException {
        new File("C://Users//Dell//Desktop//detaur").mkdirs();
        new File("C://Users//Dell//Desktop//detaur//Spectrum_backend_methodNames.txt").delete();
        printWriter  =new PrintWriter("C://Users//Dell//Desktop//detaur//Spectrum_backend_methodNames.txt");
    }

    public static void main(String[] args) throws IOException {
//        new ExceptionDetection("C://Users//Dell//Desktop//Logs//logs//logs//");
        Main1 main1= new Main1();
        main1.test1(new File(System.getProperty("user.dir")+"//src//main//java"));
        main1.printWriter.close();
//        main1.readClasses("com.lattice.spectrum");
        System.out.println("Done");
    }
    public void test1(File file) throws IOException {
        File[] files=file.listFiles();
        if(files!=null) for(File file1:files)
            if(file1.isFile() && file1.getName().endsWith(".java")){
                String fileName= file1.getName();
                fileName=fileName.substring(0,fileName.length()-5);
                String packageName=file1.getParent();
                if(packageName.indexOf("com")!=-1)
                    packageName=packageName.substring(packageName.indexOf("com"));
                else if(packageName.indexOf("org")!=-1)
                    packageName=packageName.substring(packageName.indexOf("org"));
                packageName= packageName.replace(File.separator,".");
                String methodNames=getMethodsName(packageName+"."+fileName).toString();
                System.out.println(packageName+" \t "+fileName+" \t "+methodNames+"\n");
                writeInToFile("{ "+packageName+" \t "+fileName+" \t "+methodNames+" }\n");
            }
            else
                test1(file1);
    }

    public void writeInToFile(String fileName){
        printWriter.append(fileName);
        printWriter.flush();
    }
    public List<String> getMethodsName(String className){
        List<String> methodNamesList=new ArrayList<>();
        try {
            Class<?> classNames= Class.forName(className);
            Method[] methods= classNames.getDeclaredMethods();
            for(Method method:methods)
                methodNamesList.add(method.getName()+"()");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return methodNamesList;
    }
}