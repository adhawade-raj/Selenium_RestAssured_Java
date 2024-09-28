package DSA_RSA;

import java.util.Stack;

public class Stack03_MinimumElement {

	static Stack<Integer> mainStack = new Stack<Integer>();
	static Stack<Integer> temp = new Stack<Integer>();
	
	public static void main(String[] args) {
		customPush(12);
		customPush(6);
		System.out.println("Main stack = "+mainStack);
		System.out.println("Minimun value before removing some value from stack = "+temp.peek());
		customPop();
		customPush(20);
		
		customPush(17);
		customPush(3);
		customPop();
		System.out.println("Main stack = "+mainStack);
		System.out.println("Minimun value after removing some value from stack = "+temp.peek());

	}
	
	private static void customPush(int i) {
		// TODO Auto-generated method stub
		
		mainStack.push(i);
		if(temp.isEmpty())
			temp.push(i);
		
		else if(temp.peek()>i)
			temp.push(i);
				
	}
	
	private static void customPop() {
		int item =mainStack.peek();
		mainStack.pop();
		if(temp.peek() ==item)
		{
			temp.pop();
		}
		
		
	}	

}
