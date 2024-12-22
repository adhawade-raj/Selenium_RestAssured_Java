package dsa_RSA;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Section05_Queue05_PriorityQueue {

	public static void main(String[] args) {
		Queue<Integer> queue = new PriorityQueue<>();
		queue.add(4);
		queue.add(2);
		queue.add(9);
		queue.add(3);
		queue.add(12);
		System.out.println("-----PriorityQueue = Sorting order followed----");
		for(int ele : queue)
		{
			System.out.println(ele);
		}
		
		Queue<Integer> queue2 = new ArrayDeque<>();
		queue2.add(4);
		queue2.add(2);
		queue2.add(9);
		queue2.add(3);
		queue2.add(12);
		System.out.println("-----Array Deque = Sorting order not followed----");
		for(int ele : queue2)
		{
			System.out.println(ele);
		}
	}

	}

