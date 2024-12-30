package dsa_RSA_2024;


public class Section03_LinkedList02_AddNodeAtEnd {

	
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
	
	public static void main(String[] args) {
		
		Section03_LinkedList01_PrintLinkedList ls = new Section03_LinkedList01_PrintLinkedList();
		  
		  ls.addNodeAtEnd(10);
		  ls.addNodeAtEnd(20);  
		  ls.addNodeAtEnd(30);
		  ls.addNodeAtEnd(40);
		  
//		  if u print this linked list then o/p will be
//		  10
//		  20
//		  30
//		  40

	}

}
