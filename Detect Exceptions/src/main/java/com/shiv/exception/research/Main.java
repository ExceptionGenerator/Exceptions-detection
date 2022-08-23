package com.shiv.exception.research;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        new Main().read1();
    }
    public void read() throws IOException {
        File file=new File("ser.txt");
        System.out.println(file.length());
        FileInputStream fileInputStream=new FileInputStream("ser.txt");
        int avail;
        while((avail= fileInputStream.read())!=-1)
            System.out.println(avail);
        fileInputStream.close();
    }

    public void read1(){
        File file=new File("C:\\Users\\Dell\\Downloads\\log7z\\log");
//        Arrays.stream(file.listFiles()).forEach(System.out::println);
        Arrays.stream(file.listFiles()).forEach((file1 -> {
            try {
                Scanner scanner=new Scanner(file1);
                while (scanner.hasNext()){
                    String data=scanner.nextLine();
                    if(data.contains("Exception"))
                        System.out.println(data+"\t"+file1.getName());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
            }
        }));
    }
}
