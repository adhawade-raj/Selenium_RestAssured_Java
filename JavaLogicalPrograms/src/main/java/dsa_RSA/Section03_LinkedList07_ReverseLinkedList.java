package dsa_RSA;


public class Section03_LinkedList07_ReverseLinkedList {

	
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
	
	public void reverseLinkedList() //O(n)
	{
		Node next = null;
		Node prev = null;
		Node curr = head;
		while(curr!=null)
		{
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr =next;
		}
		
		printLinkedList(prev);
	}
	
	public static void main(String[] args) {
	
		Section03_LinkedList07_ReverseLinkedList ls = new Section03_LinkedList07_ReverseLinkedList();
		ls.addNodeAtEnd(10);
		ls.addNodeAtEnd(20);
		ls.addNodeAtEnd(30);
		ls.addNodeAtEnd(40);
		
		ls.printLinkedList(head);
		System.out.println("________After Reversing the linked List________");
		
		ls.reverseLinkedList();
		
		
	}

}
