package com.shiv.exception.be;

import java.util.HashMap;
import java.util.Map;

public class TestIoT {

    public static void IoT83(String[] args) {
        String s ="My name is Shivmohan".toLowerCase();
        Map<Character,Integer> map=new HashMap<>();
        for(char ch:s.toCharArray()){
            if(map.get(ch)!=null)
                map.put(ch,map.get(ch)+1);
            else
                map.put(ch,1);
        }
        System.out.println(map);


        int num =961623;
        int numCopy=num;
        int digit=0;
        // reverse the num
        int rev=0;
        while (numCopy!=0){
            digit=numCopy%10;
            rev= rev*10+digit;
            numCopy/=10;
        }

// then print the digit with its length
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

    public static void main(String[] args) {
        System.out.println("Hi....");
    }
}
