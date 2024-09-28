package DSA_RSA;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Queue03_QueueClasseFunctions {

	public static void main(String[] args) {
		Queue<Integer> queue = new PriorityQueue<>();
		
		queue.add(4);
		queue.add(2);
		queue.remove();
		queue.add(9);
		queue.add(3);
		queue.add(12);
		queue.remove();
		queue.remove();
		System.out.println("----Various operations of Queue----");
		for(int ele : queue)
		{
			System.out.println(ele);
		}
		
	}

}
