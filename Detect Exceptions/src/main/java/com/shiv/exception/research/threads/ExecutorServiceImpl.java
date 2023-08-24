package com.shiv.exception.research.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class ExecutorServiceImpl {
    public static Character getCt(String inputLine){
//        inputLine=inputLine.replaceAll(" ","");
//        int index=-1;
//        for(char ch:inputLine.toCharArray()){
//            index++;
//            if(inputLine.lastIndexOf(ch)==-1){
//                System.out.println("char-"+ch);
//                return ch;
//            }
//        }
//        return '1';
        Map<String,Integer> map=new HashMap<>();
        for(String ch:inputLine.split("")){
            System.out.println("char-"+ch);
            if(map.get(ch)==null)
                map.put(ch,1);
            else
                map.put(ch,map.get(ch)+1);
        }
        System.out.println(map);
        AtomicReference<String> characterAtomicReference=new AtomicReference<>();
        map.keySet().forEach(character -> {
            if(map.get(character)==1 && characterAtomicReference.get()==null)
                characterAtomicReference.set(character);
        });
        return characterAtomicReference.get().toCharArray()[0];
    }
    public static void main(String[] args) {
//        ExecutorService executorService= Executors.newFixedThreadPool(10);
//        executorService.execute(() -> {
//            for(int i=0;i<100;i++){
//                System.out.println("Hell");
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
//        run1();
//        executorService.shutdown();
//        inputLine=inputLine.replaceAll(" ","");
//        for(int i=0;i<inputLine.length();i++){
//            char ch=inputLine.charAt(i);
//            boolean isMatched=false;
//            for(int j=i+1;j<inputLine.length();j++){
//                if(ch==inputLine.charAt(j)){
//                    isMatched=true;
//                    break;
//                }
//                else
//                    isMatched=false;
//            }
//            if(!isMatched)
//                for(int j=0;j<i;j++){
//                if(ch==inputLine.charAt(j)){
//                    isMatched=true;
//                    break;
//                }
//                else
//                    isMatched=false;
//            }
//            if(!isMatched){
//                System.out.println(""+ch);
//                break;
//            }
//        }
//        System.out.println(getCt("hello world hi hey"));
    }
    public static void run1(){
        for(int i=0;i<100;i++){
            System.out.println("outside");
            System.out.println(Thread.currentThread().getName());
        }
    }
}
