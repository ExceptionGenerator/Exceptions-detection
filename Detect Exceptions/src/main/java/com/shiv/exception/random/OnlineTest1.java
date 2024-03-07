package com.shiv.exception.random;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class OnlineTest1 {
    public static void main(String[] args) {
//        List<Integer> list= duplicate(List.of(5, 3, 4, 1, 3, 7, 2, 9, 9, 4));
//        System.out.println(list);
//        Set<Integer> set=new HashSet<>();
//        System.out.println(set.add(2));
//        System.out.println(set.add(2));
//        System.out.println(set.add(4));
        ConcurrentMap<String,Integer> concurrentMap=new ConcurrentHashMap<>();
//        Map<String,Integer> concurrentMap=new HashMap<>();
        concurrentMap.put("1",1);
        concurrentMap.put("2",2);
        concurrentMap.put("3",1);
        concurrentMap.values()
                        .parallelStream()
                                .forEach(data->{
                                    System.out.println("Data-"+data);
                                    if(data==2)
                                        concurrentMap.put("4",34);
                                });
        System.out.println(concurrentMap);
    }

    /**
     *
     * @param values 5, 3, 4, 1, 3, 7, 2, 9, 9, 4
     * @return in list 3,4,9
     * (duplicate values should be return)
     */
    public static List<Integer> duplicate(List<Integer> values){
        Set<Integer> set=new HashSet<>();
        return values.stream()
                .filter(num->!set.add(num))
                .toList();
    }
}
