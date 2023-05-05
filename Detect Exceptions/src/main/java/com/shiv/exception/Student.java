package com.shiv.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.rmi.Remote;
import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Student {
    private int id;
    private int age;
    private String name;
    private Author author;

    public static void main(String[] args) {
//        Date date=new Date();
//        long age=12;
//        long comp= date.getTime()-(365*age*24*60*60*1000);
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(new Date(comp));
//        System.out.println(simpleDateFormat.format(new Date(comp)));


//        List<Student> students=new ArrayList<>();
//        students.add(new Student(1,18,"Shiv",new Author(3,"Ebala",new Address(3,"Delhi"))));
//        students.add(new Student(2,13,"d",new Author(23,"E2bala",new Address(5,"Delhi3"))));
//        students.add(new Student(3,14,"Srhiv",new Author(33,"Ewbala",new Address(33,"Delwwwhi"))));
//        students.add(new Student(4,12,"Shtiv",new Author(32,"Ebtala",new Address(43,"Deleehi"))));
//        students.add(new Student(5,15,"Shiev",new Author(31,"Ebarla",new Address(34,"Delehi"))));
//        students.stream().forEach(System.out::println);
//        System.out.println("------------------");
        Set<String> data=new HashSet<>();
//        data.add(2);
//        data.add(1);
//        data.add(5);
//        data.add(4);
        data.add("ac");
        data.add("ab");
        data.add("ae");
        data.add("ad");
        System.out.println(data);
    }
}

class FileSystem implements Cloneable {
    @Override
    public FileSystem clone() {
        try {
            return (FileSystem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}




@Data
@AllArgsConstructor
@NoArgsConstructor
class Author{
    private int id;
    private String name;
    private Address address;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Address{
    private int id;
    private String address;
}

