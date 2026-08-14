package dsa_ScottBarret_2025;

public class Section4_LL01_Utilities {

	private Node head;
	private Node tail;
	private int length;

	public class Node {
		private int value;
		private Node next;

		public Node(int value) {
			this.value = value;
		}

	}

	public Section4_LL01_Utilities(int value) {
		Node newNode = new Node(value);
		head = newNode;
		tail = newNode;
		length = 1;

	}

	public void printList() {

		Node temp = head;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}

	}

	public void getHead() {
		if (head == null)
			System.out.println("Head : " + null);
		System.out.println("Head : " + head.value);
	}

	public void getTail() {
		if (head == null)
			System.out.println("Tail : " + null);
		System.out.println("Tail : " + tail.value);
	}

	public void getLength() {
		System.out.println("Length : " + length);
	}

	public void append(int value) {

		Node newNode = new Node(value);
		if (length == 0) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		length++;
	}

	public Node removeLst() {
		if (length == 0)
			return null;

		Node temp = head;
		Node pre = head;
		while (temp.next != null) {
			pre = temp;
			temp = temp.next;
		}
		tail = pre;
		tail.next = null;
		length--;
		if (length == 0) {
			head = null;
			tail = null;
		}
		System.out.println("Tail Node : " + temp.value);
		return temp;

	}
}
