package dsa_RSA_2024;

import java.util.Stack;

public class Section04_Stack02_ReverseStack {

	public static void main(String[] args) {
		Stack<Integer> stack1 = new Stack<Integer>();
		System.out.println(stack1.empty());
		stack1.push(34);
		stack1.push(3);
		stack1.push(31);
		stack1.push(98);
		stack1.push(14);
		System.out.println("Initial Stack  = "+stack1);
		
		Stack<Integer> stack2 = new Stack<Integer>();
		Stack<Integer> stack3 = new Stack<Integer>();
		moveStack(stack1,stack2);
		moveStack(stack2,stack3);
		moveStack(stack3,stack1);
		System.out.println("Reversed Stack = "+stack1);

	}
	
private static void moveStack(Stack<Integer> source, Stack<Integer> dest) {
		
		while(!source.empty())
		{
		int item = source.peek();
		dest.push(item);
		source.pop();
		}
		
	}

}
