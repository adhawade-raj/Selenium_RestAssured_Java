package Practise_May;

import java.util.Scanner;


public class Solution extends Student {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student std[] = new Student[n];
        
        for(int i=0; i<std.length; i++){
           int id =sc.nextInt();
           sc.nextLine();
           String name = sc.nextLine();
           int marks = sc.nextInt();
           int age = sc.nextInt(); 
           
           std[i] = new Student(id,name,marks,age);
        }
        
        Student ans1 = findStudentWithMaximumAge(std);
        if(ans1!=null){
            System.out.println("id-"+ans1.getId());
            System.out.println("name-"+ans1.getName());
            System.out.println("marks-"+ans1.getMarks());
            System.out.println("age-"+ans1.getAge());
        }
        else{
            System.out.println("No Student found with mentioned attribute.");
        }
        int input1=sc.nextInt();
         Student ans2 = findStudentWithMaximumAge(std, input1);
        if(ans2!=null){
            System.out.println("id-"+ans2.getId());
            System.out.println("name-"+ans2.getName());
            System.out.println("marks-"+ans2.getMarks());
            System.out.println("age-"+ans2.getAge());
        }
        else{
            System.out.println("No Student found with mentioned attribute.");
        }
        
    }
    
    //code for First method
    public static Student findStudentWithMaximumAge(Student std[]){
        int max=0;
        Student res1 = null;
        
        for(int i=0;i<std.length; i++){
            if(std[i].getAge()>max){
                max = std[i].getAge();
                res1= std[i];
            }
        }
        return res1;
    }
    
    
    public static Student searchStudentById(Student std[], int id){
        Student res2=null;
        if(std[i].getId()==id){
            res2=std[i];
        }
    }
   return res2;
    
    // code for Second method
}