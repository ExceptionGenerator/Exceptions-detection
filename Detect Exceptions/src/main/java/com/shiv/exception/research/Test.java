package com.shiv.exception.research;

import java.rmi.Remote;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

interface All extends Remote{

}
public class Test {
    // 1,1,2,2,3,4,2,2,2,2,2,2,7,8,9,10
    static int max=0;
    public static void main(String[] args) {
        Integer value1=59;
        Optional<Integer> value=Optional.ofNullable(value1);
        System.out.println(value.orElse(5));
        int[] array={1,1,2,2,3,3,3,3,4,2,2,2,7,8,9,10};
//        List<Integer> values=List.of()
//        Map<Integer,Integer> map=new HashMap<>();
//        max=array[0];
//        int counter=1;
//        int startIndex=0;
//        int endIndex=0;
//        for(int i=0;i<array.length;i++){
//            if(max==array[i]){
//                counter++;
//            }else{
//                map.put(max,counter);
//                max=array[i];
//                counter=1;
//            }
//        }
//        max=array[0];
//        map.values().stream().forEach(value->{
//            if(value>max){
//                max=value;
//            }
//        });
//        counter=0;
//        for(int i=0;i<array.length;i++){
//            if(i!=array.length-1 && array[i]==array[i+1]){
//                counter++;
//                endIndex=i+2;
//            }else {
//                startIndex=i+1;
//            }
//            if(counter==max)
//                break;
//        }
//        System.out.println(startIndex);
//        System.out.println(endIndex);
//        System.out.println(max);
//        System.out.println(map);
    }
}
