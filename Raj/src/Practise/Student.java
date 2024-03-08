package Practise;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Student{
    int id;
    String name;
    int marks;
    int age;
    
    public Student(int id, String name, int marks, int age){
        this.id=id;
        this.name=name; 
        this.marks=marks;
        this.age=age;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getMarks(){
        return marks;
    }
    public int getAge(){
        return age;
    }
}