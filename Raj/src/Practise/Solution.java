package Practise;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		Student std[] = new Student[n];
		for(int i=0; i<std.length; i++)
		{
		int id = sc.nextInt();
		sc.nextLine();
		String name = sc.nextLine();
		double marks = sc.nextDouble();
		int age = sc.nextInt();

		std[i] =new Student(id,name,marks,age);
		int input1 = sc.nextInt();
		sc.nextLine();

		Student ans1 = findStudentWithMaximumAge(std);
		System.out.println("id-"+ans1.getId());
		System.out.println("id-"+ans1.getName());
		System.out.println("id-"+ans1.getMarks());
		System.out.println("id-"+ans1.getAge());


		Student ans2 = searchStudentById(std, input1);
		if(ans2==null){
		System.out.println("No Student found with mentioned attribute.");
		}
		else{
		System.out.println("id-"+ans2.getId());
		System.out.println("id-"+ans2.getName());
		System.out.println("id-"+ans2.getMarks());
		System.out.println("id-"+ans2.getAge());
		}
		}
		}
	
public static Student findStudentWithMaximumAge(Student[] std){

int max = std[0].age;
for(int i=1; i<std.length; i++){
if(std[i].age > max)
{
max= std[i].age;
}
}

for(int i=0; i<std.length; i++){
if(std[i].age==max)
{
return std[i];
}
}
return null;
}

public static Student searchStudentById(Student[] std, int input1){

for(int i=1; i<std.length; i++){
if(std[i].id==input1)
{
return std[i];
}
}
return null;
}
}


