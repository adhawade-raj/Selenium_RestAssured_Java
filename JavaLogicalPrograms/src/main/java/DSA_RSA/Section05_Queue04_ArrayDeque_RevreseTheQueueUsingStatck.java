package DSA_RSA;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Section05_Queue04_ArrayDeque_RevreseTheQueueUsingStatck {

	public static void main(String[] args) {
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.add(15);
		queue.add(12);
		queue.add(9);
		queue.add(7);
		queue.add(20);
		queue.remove();//FIFO
		queue.remove(20); //pop
		System.out.println("peek value is "+ queue.peek());
		
		for(int ele : queue)
		{
			System.out.println(ele);
		}
		
		Stack<Integer> stack1 = new Stack<Integer>();
		while(!queue.isEmpty())
		{
		stack1.push(queue.remove());
		}
		while(!stack1.isEmpty())
		{
			queue.add(stack1.pop());
		}
		System.out.println("content after reversing");
		for(int ele : queue)
		{
			System.out.println(ele);
		}
	}

}
