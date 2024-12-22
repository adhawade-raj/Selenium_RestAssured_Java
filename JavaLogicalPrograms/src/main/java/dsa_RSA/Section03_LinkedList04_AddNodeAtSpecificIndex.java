package dsa_RSA;

import dsa_RSA.Section03_LinkedList03_AddNodeAtBeginning.Node;

public class Section03_LinkedList04_AddNodeAtSpecificIndex {

	
	class Node{
		
		int val;
		Node next;
		
		public Node(int val) {
			this.val=val;
		}
	}
	
	static Node head =null;
	Node tail = null;
	
	
	public void addNodeAtTheBeginning(int value) {
		 
	    Node newNode = new Node(value);
	    //case when there is no head
	    if (head == null) {
	        head = newNode;
	        tail = newNode;
	        //case when there happens to be a head already
	    } else {
	        newNode.next = head;
	        head = newNode;
	    }
	}
	
	public void addNodeAtIndex(int val, int index)// 30 29th O(n)
	{
		
		int count = 1;
		Node current = head;
		while(current!=null && count!= index-1)
		{
			current = current.next;
			count++;
		}
		Node newNode = new Node(val);
			Node temp=current.next;
			current.next=newNode;
			newNode.next = temp;
	
	}
	
	public void printLinkedList(Node headNode) {
		Node current = headNode;
		while(current!=null) {
			System.out.println(current.val);
			current = current.next;
		}
		}
	
	public static void main(String[] args) {
		
		Section03_LinkedList04_AddNodeAtSpecificIndex ls = new Section03_LinkedList04_AddNodeAtSpecificIndex();
		ls.addNodeAtTheBeginning(50);
		ls.addNodeAtTheBeginning(40);
		ls.addNodeAtTheBeginning(20);
		ls.addNodeAtTheBeginning(10);
//		ls.printLinkedList(head);
		ls.addNodeAtIndex(30, 3);
		ls.printLinkedList(head);

	}

}
