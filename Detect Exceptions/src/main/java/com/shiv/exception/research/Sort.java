package com.shiv.exception.research;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        List<Student> students=new ArrayList<>();
        students.add(new Student(1,18,"Shiv",new Date(1664447811832L)));
        students.add(new Student(2,17,"Ram",new Date(1664447811832L-(1000*56))));
        students.add(new Student(3,19,"Shyam",new Date(1664447811832L-(1000*30))));
        students.add(new Student(4,18,"Anker",new Date(1664447811832L-(1000*20))));
        students.add(new Student(5,20,"Quenter",new Date(1664447811832L-(1000*60))));
        students.add(new Student(6,22,"Anurag",new Date(1664447811832L-(1000*99))));
        students.add(new Student(7,22,"Delta",new Date(1664447811832L-(1000*9))));
        students.forEach(System.out::println);
        students.sort(Comparator.comparing(Student::getDate).reversed());
        System.out.println("After sort");
        students.forEach(System.out::println);
    }
}
