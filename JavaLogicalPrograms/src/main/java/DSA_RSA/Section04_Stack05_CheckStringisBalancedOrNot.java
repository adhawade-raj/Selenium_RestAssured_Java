package DSA_RSA;

import java.util.Stack;

public class Section04_Stack05_CheckStringisBalancedOrNot {

	public static void main(String[] args) {
		String s = "(2+)[{4}]";
		
		if(isBalanced(s)) {
			System.out.println("String is balanced");
		}
		else {
			System.out.println("String is not balanced");
		}
	}
	
	
		private static boolean isBalanced(String s)  
		{
		 Stack<Character> stack = new Stack<Character>();
		for (char c: s.toCharArray())
		 {
		if (c == '(' || c == '{' || c == '[') 
		 stack.push(c);
		 
		 if (c == ')' || c == '}' || c == ']')
		 {
		 Character top = (char) stack.pop();
	   if ((top == '(' && c != ')') 
		|| (top == '{' && c != '}') 
		|| (top == '[' && c != ']')) 
	   { return false; }
		}
		 
		}
		return stack.isEmpty();
		}


}
