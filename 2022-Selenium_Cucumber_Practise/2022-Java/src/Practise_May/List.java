package Practise_May;
import java.util.*;
public class List {

	public static void main(String[] args) {
	
		
		ArrayList al =new ArrayList();
al.add("A");
al.add("B");
al.add("C");
al.add("d");
System.out.println(al);

al.add(2,"X");
System.out.println(al);
System.out.println(al.set(3, "X"));
System.out.println(al);
System.out.println("--------------");
System.out.println(al.indexOf("X"));
System.out.println(al.lastIndexOf("X"));

	}

}
