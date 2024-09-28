package DSA_RSA;

import java.util.Stack;

public class Stack04_SortedStack {

	private static Stack<Integer> SortStack(Stack<Integer> stack1) {

		Stack<Integer> tempstk = new Stack<>();
		while(!stack1.isEmpty())
		{
			int temp =stack1.pop();
				while(!tempstk.isEmpty() && tempstk.peek()>temp )//adjustment to have least num in bottom
				{
					int tt =tempstk.pop();
					stack1.push(tt);
				}
				tempstk.push(temp);
		}	
		return tempstk;
	}
	
	public static void main(String[] args) {
	
		Stack<Integer> stack1 = new Stack<Integer>();
		stack1.push(34);
		stack1.push(3);
		stack1.push(31);
		stack1.push(98);
		stack1.push(14);
		System.out.println("Original Stack = "+stack1);
		System.out.println("Sorted in ascending order ="+SortStack(stack1));
	}

}
