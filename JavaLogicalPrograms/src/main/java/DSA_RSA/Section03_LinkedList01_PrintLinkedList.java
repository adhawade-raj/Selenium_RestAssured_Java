package DSA_RSA;

public class Section03_LinkedList01_PrintLinkedList {

	
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


		public void printLinkedList(Node headNode) {
		Node current = headNode;
		while(current!=null) {
			System.out.println(current.val);
			current = current.next;
		}
	}
	
	public boolean checkIfThereIsANodeWithAGivenValue(int value) {
		 
	    Node current = head;
	    while (current != null) {
	        if (current.val == value) {
	            return true;
	        }
	        current = current.next;
	    }
	 
	    //if we end up reaching here , it means there is no node in the linked-list with the given value
	    return false;
	 
	}
	
public static void main(String[] args) {
		
  Section03_LinkedList01_PrintLinkedList ls = new Section03_LinkedList01_PrintLinkedList();
  
  ls.addNodeAtEnd(10);
  ls.addNodeAtEnd(20);  
  ls.addNodeAtEnd(30);
  ls.addNodeAtEnd(40);
  
  ls.printLinkedList(head);
  
  System.out.println(ls.checkIfThereIsANodeWithAGivenValue(30));
	}

}
