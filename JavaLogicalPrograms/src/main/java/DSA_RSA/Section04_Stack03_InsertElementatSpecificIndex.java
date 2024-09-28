package DSA_RSA;

import java.util.Stack;

public class Section04_Stack03_InsertElementatSpecificIndex {

public static void addElementatIndex(Stack<Integer> source, Stack<Integer> dest, int item, int index) {
		
		for(int i=1; i<=source.size()-index; i++) {
			
			if(source.get(index)!=index)
//			while(!source.empty())
			{
			int item2 = source.peek();
			dest.push(item2);
			source.pop();
			}
			dest.insertElementAt(item, index);
			
		}
	}
	

private static void addElementatIndex(Stack<Integer> source, int value, int index) {
	source.insertElementAt(value, index);
}
	
	public static void main(String[] args) {
	
		Stack<Integer> stack1 = new Stack<Integer>();
		stack1.push(34);
		stack1.push(3);
		stack1.push(31);
		stack1.push(98);
		stack1.push(14);
		System.out.println("Initial Stack1 = "+stack1);

		/**Approach 1*/
		addElementatIndex(stack1, 100, 2);
		System.out.println("Stack1 after adding Values at index = "+stack1);
		
		/**Approach 2*/
		//Not working
//		Stack<Integer> stack2 = new Stack<Integer>();
//		addElementatIndex(stack1, stack2, 100, 2);
//		System.out.println("Stack2 after adding Values at index = "+stack2);
		
		
	}
	}

		
		
	
	


