package DSA_RSA;


public class Section03_LinkedList03_AddNodeAtBeginning {

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
	    //case when theres no head
	    if (head == null) {
	        head = newNode;
	        tail = newNode;
	        //case when there happens to be a head already
	    } else {
	        newNode.next = head;
	        head = newNode;
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
	
	Section03_LinkedList03_AddNodeAtBeginning ls = new Section03_LinkedList03_AddNodeAtBeginning();
	
	
	ls.addNodeAtTheBeginning(10);
	ls.addNodeAtTheBeginning(20);
	ls.printLinkedList(head);

	}
	
	
	

}
