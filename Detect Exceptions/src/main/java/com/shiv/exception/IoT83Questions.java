package com.shiv.exception;

import java.util.HashMap;
import java.util.Map;

public class IoT83Questions {
    public static void main(String[] args) {
        // n=2
//        String s ="My name is Shivmohan".toLowerCase();
//        Map<Character,Integer> map=new HashMap<>();
//        for(char ch:s.toCharArray()){
//            if(map.get(ch)!=null)
//                map.put(ch,map.get(ch)+1);
//            else
//                map.put(ch,1);
//        }
//
//        System.out.println(map);


        int num =961623;
        int numCopy=num;
        int digit=0;
        // rev the num
        int rev=0;
        while (numCopy!=0){
            digit=numCopy%10;
            rev= rev*10+digit;
            numCopy/=10;
        }


        digit=0;
        num=rev;
        int length=0;
        while (num!=0){
            digit=num%10;
            num/=10;
            length++;
            System.out.println(digit);
        }
        System.out.println("Length-"+length); // 961623
    }
}
