//package com.shiv.exception;
//
//import java.util.*;
//
//interface A{
//    default void test() {
//        System.out.println("inside test");
//    }
//}
//enum Role{
//    USER,GENDER
//}
//public class Main1 implements A{
//    public static void main(String[] args) {
//        if()
//        System.out.println(Arrays.toString(Role.values()));
//    }
//    public static void main1(String[] args) {
//        int a=122333;
//        int b=333221; //123321
//        int c=46340; //123321
////        System.out.print(a +""+ b);
////        System.out.println(gcdOfStrings("ABCABC","ABCD"));
////        System.out.println(mySqrt(9)); // 46340
////        System.out.println(c*c);
////        System.out.println((c+1)*(c+1));
//    }
//
//    public static String gcdOfStrings(String str1, String str2) {
//        StringBuilder stringBuilder1=new StringBuilder();
//        StringBuilder stringBuilder2=new StringBuilder();
//        for(char ch:str1.toCharArray())
//            if(!stringBuilder1.toString().contains(""+ch))
//                stringBuilder1.append(ch);
//        for(char ch:str2.toCharArray())
//            if(!stringBuilder2.toString().contains(""+ch))
//                stringBuilder2.append(ch);
//        return !stringBuilder1.toString().equalsIgnoreCase(stringBuilder2.toString())?"":stringBuilder1.toString();
//    }
//
//
//}
//class User{
//    private int id;
//    //
//}
//class BusinessDetails{
//    @ManyToOne
//    private User user;
//}