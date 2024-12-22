package javaStreams_2024;

public class Part01_ImplmentingInterface01_WithoutLambada {

	
	
	public static void main(String[] args) {
		
		Part01_WebPage w1 = new Part01_WebPage() {
			
			@Override
			public void header(String value) {
				System.out.println("Hi "+value);
				
			}
		};
		w1.header("Implementing Functional Interface without Lambada expression");
		
		
	Part01_WebPage w2 = new Part01_WebPage() {
			
			@Override
			public void header(String value) {
				System.out.println("Hello "+value);
				
			}
		};
		w2.header("Facebook");
		
	}
	
}
