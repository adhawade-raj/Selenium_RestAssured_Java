package dsa_ScottBarret_2025;

public class Section4_LL03_RemoveLastNode {

	public static void main(String[] args) {
		Section4_LL01_Utilities ll = new Section4_LL01_Utilities(5);
		ll.append(6);
		ll.getHead();
		ll.getTail();
		ll.getLength();
		ll.printList();
		System.out.println("-----Removal Of Node----");
		ll.removeLst();
		ll.getLength();

	}

}
