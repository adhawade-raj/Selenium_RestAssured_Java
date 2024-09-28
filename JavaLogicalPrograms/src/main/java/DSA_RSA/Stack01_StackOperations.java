package DSA_RSA;

import java.util.Stack;

public class Stack01_StackOperations {

	public static void main(String[] args) {
	
		Stack<Integer> stack1 = new Stack<Integer>();
		System.out.println("Verify Stack before adding elements "+stack1.empty());
		stack1.push(34);
		stack1.push(3);
		stack1.push(31);
		stack1.push(98);
		stack1.push(14);
		System.out.println("Initial Stack = "+stack1);
		stack1.pop(); //LIFO
		System.out.println("Stack after removing top element with Pop Function  = "+stack1);
		int peekedValue = stack1.peek();
		System.out.println("Peeked Values = "+peekedValue);
		
		System.out.println("To check the value present in Stack = "+stack1.contains(31));
		stack1.insertElementAt(100, 2);
		System.out.println("After adding element at specific Index "+stack1);
		stack1.remove(0);
	}

}
