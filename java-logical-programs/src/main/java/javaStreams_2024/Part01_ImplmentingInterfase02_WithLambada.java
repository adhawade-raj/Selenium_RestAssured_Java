package javaStreams_2024;

public class Part01_ImplmentingInterfase02_WithLambada {

	
	
	public static void main(String[] args) {
		
		Part01_WebPage w1 = (value) -> {System.out.println("Hi "+value) ; };
		w1.header("Implementing Functional Interface With Lambada expression");
		
		
		Part01_WebPage w2 = (e) -> {System.out.println("Hi "+ e) ; };
		w2.header("Implementing Functional Interface With Lambada expression");
		
		Part01_WebPage w3 = (e) -> {System.out.println("Hi "+ e.length()) ; };
		w3.header("Implementing Functional Interface With Lambada expression");

		}
}

