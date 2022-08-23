package com.shiv.exception.research;

import java.io.File;
import java.util.Random;

public class DriveSpace {
    public static void main(String[] args) {
        File file=new File("C://Repligen//KF Comm 2//bin//log");
        String email="";
        System.out.println(genOTP());
    }
    public static String genOTP(){
        String alphabet="qwertyuioplkjhgfdsazxcvbnm";
        alphabet+=alphabet.toUpperCase();
        alphabet+="123456789";
        StringBuilder stringBuilder=new StringBuilder();
        Random random=new Random();
        for(int i=0;i<20;i++)
            stringBuilder.append(alphabet.charAt(random.nextInt(alphabet.length())));
        return stringBuilder.toString();
    }
}
