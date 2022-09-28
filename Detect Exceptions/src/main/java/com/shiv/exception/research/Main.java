package com.shiv.exception.research;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
//        new Ma
//        Main.getTopicCount("Afghanistan");
        read1();
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
    static int getTopicCount(String topic) {
        int count=0;
        try{
            URLConnection urlConnection=new URL("https://jsonmock.hackerrank.com/api/countries?name="+topic).openConnection();
            Scanner scanner=new Scanner(urlConnection.getInputStream());
            while(scanner.hasNext()){
                String data=scanner.next();
                System.out.println(data);
            }
            scanner.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public static void read1(){
        File file=new File("C:\\Users\\Dell\\Downloads\\shivlog\\log");
//        Arrays.stream(file.listFiles()).forEach(System.out::println);
        Arrays.stream(file.listFiles()).forEach((file1 -> {
            try {
                Scanner scanner=new Scanner(file1);
                while (scanner.hasNext()){
                    String data=scanner.nextLine();
                    if(data.contains("UUID"))
                        System.out.println(data+"\t"+file1.getName());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
            }
        }));
    }
}
