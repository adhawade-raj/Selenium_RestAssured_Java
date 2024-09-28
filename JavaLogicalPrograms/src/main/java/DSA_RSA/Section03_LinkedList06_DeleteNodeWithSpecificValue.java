package DSA_RSA;

import DSA_RSA.Section03_LinkedList05_DeleteNodeAtFirst_End_SpecifiedIndex.Node;

public class Section03_LinkedList06_DeleteNodeWithSpecificValue {

class Node{
		
		int val;
		Node next;
		
		public Node(int val) {
			this.val=val;
		}
	}
	
	static Node head =null;
	Node tail = null;
	
	
public void addNodeAtEnd(int val) {
		
		Node newNode = new Node(val);
		if(head==null) {
			head = newNode;
			tail = newNode;
		}
		else {
			tail.next=newNode;
			newNode.next=null;
			tail = newNode;
		}
		}
	
	public void deleteNodeEnd()
	{
		
		Node current = head;
		while(current.next.next!=null)
		{
			current = current.next;
		}
		current.next = null;
		tail =current;
	}
	
	public void deleteNodeFirst() {
		head = head.next;
	}
	
	
	public void deleteNodeWithAGivenValue(int value) {
		 
	    //case when we need to remove the head node
	    if (head.val == value) {
	    	deleteNodeFirst();
	    } else if (tail.val == value) {
	        //case when we need to remove the tail node
	    	deleteNodeEnd();
	    } else {
	        Node current = head;
	        Node previous = null;
	        while (current != null && current.val != value) {
	            //store the value of current in previous
	            previous = current;
	            current = current.next;
	        }
	 
	        //case when the given value node  is not found
	        if (current == null) {
	            throw new RuntimeException("the node with the given value is not found!!");
	        }
	        //at this point we have the information of the previous node to the node with given value which we want to delete
	        //and current node is the node that we want to delete
	        previous.next = current.next;
	    }
	}
	 
	//Lecture #27 Assignment Part 2(using the deleteNodeWithAGivenValue() function here): remove all nodes with even values
	public void deleteAllNodesWithEvenValues() {
	 
	    Node current = head;
	    while (current != null) {
	 
	        if (current.val % 2 == 0) {
	            deleteNodeWithAGivenValue(current.val);
	        }
	        current = current.next;
	    }
	 
	}
	
	public void printLinkedList(Node headNode) {
		Node current = headNode;
		while(current!=null) {
			System.out.println(current.val);
			current = current.next;
		}
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Section03_LinkedList06_DeleteNodeWithSpecificValue ls = new Section03_LinkedList06_DeleteNodeWithSpecificValue();	
		  ls.addNodeAtEnd(10);
		  ls.addNodeAtEnd(20);  
		  ls.addNodeAtEnd(30);
		  ls.addNodeAtEnd(40);
		
		ls.deleteNodeWithAGivenValue(20);
		ls.printLinkedList(head);
	}

}
