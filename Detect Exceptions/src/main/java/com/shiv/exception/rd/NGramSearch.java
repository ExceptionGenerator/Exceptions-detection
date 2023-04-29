package com.shiv.exception.rd;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Student{
    private int id;
    private String name;
    private String address;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class FilterStudents{
    private Student student;
    private int priority;
}

public class NGramSearch{

    private List<Student> students=new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(re("fun&!! time"));
        System.out.println(re("wwwyytt"));
    }

    public static String re(String sen){
        String[] arr=sen.split(" ");
        String finalString="";
        String token="5nyk7xf3";
        int length=0;
        char ch1='3';
        return new StringBuilder(finalString).reverse().toString().concat(":"+ new StringBuilder(token).reverse());
    }

    /**
     *
     * @param tokenSize n-gram(where n=1,2,3,....n)
     * @param keyword any search keyword
     * @return
     */
    public Set<String> nGramTokens(int tokenSize,String keyword){
        Set<String> ngrams=new HashSet<>();
        for(int i=0;i<=keyword.length()-tokenSize;i++)
            ngrams.add(keyword.substring(i,i+tokenSize));
        return ngrams;
    }

    public List<FilterStudents> filterStudents(String address){
        var tokens=nGramTokens(2,address);
        List<FilterStudents> filterStudents=new ArrayList<>();
        students.parallelStream().forEach((student -> {
            var filteredStudent=new FilterStudents(student,0);
            tokens.parallelStream().forEach((token->{
                if(student.getName().toLowerCase().contains(token.toLowerCase()))
                    filteredStudent.setPriority(filteredStudent.getPriority()+1);
            }));
            filterStudents.add(filteredStudent);
        }));
        filterStudents.sort(Comparator.comparing(FilterStudents::getPriority).reversed());
        return filterStudents;
    }

    public NGramSearch(){
        students.add(new Student(1,"Shivmohan","Siddharth nagar"));
        students.add(new Student(2,"Vipul","Varansi"));
        students.add(new Student(3,"Ram kumar","Sinch in india"));
        students.add(new Student(4,"Deepak","Lalpur naugarh"));
        students.add(new Student(5,"Raman","Buddh nagar india"));
        students.add(new Student(6,"Shivmohan","Kushi nagar"));
        students.add(new Student(7,"d","Nakha jungle"));
        students.add(new Student(8,"fr","Kushi nagar"));
        students.add(new Student(9,"se","Dift nagar"));
        students.add(new Student(10,"sddsd","fatehpur"));
    }
}