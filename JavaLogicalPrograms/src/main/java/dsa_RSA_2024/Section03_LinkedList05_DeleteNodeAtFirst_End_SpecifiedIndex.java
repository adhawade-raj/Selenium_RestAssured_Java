package dsa_RSA_2024;


public class Section03_LinkedList05_DeleteNodeAtFirst_End_SpecifiedIndex {
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
	
	public void removeNodeAtAGivenIndex(int index) {
		 
	    //case when we need to remove the head node
	    if (index == 1) {
	    	deleteNodeFirst();
	    } 
//	    else if (index == getLinkedListSize(head)) {
//	        //case when we need to remove the tail node
//	    	deleteNodeEnd();
//	    } 
	    else {
	        int count = 1;
	        Node current = head;
	        while (current != null && count != index - 1) {
	            current = current.next;
	            count++;
	        }
	        //at this point we are at the previous index of the index where we want to delete node
	        // point the next of current to the next of next to remove the connection with the next node
	        current.next = current.next.next;
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
		
		Section03_LinkedList05_DeleteNodeAtFirst_End_SpecifiedIndex ls = new Section03_LinkedList05_DeleteNodeAtFirst_End_SpecifiedIndex();
		  
		  ls.addNodeAtEnd(10);
		  ls.addNodeAtEnd(20);  
		  ls.addNodeAtEnd(30);
		  ls.addNodeAtEnd(40);
		  ls.addNodeAtEnd(50);
		  ls.addNodeAtEnd(60);
		  
		  
		  ls.deleteNodeEnd();
		  ls.deleteNodeFirst();
		  ls.removeNodeAtAGivenIndex(3);
		  ls.printLinkedList(head);
		  
	}

}
