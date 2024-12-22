package newLearnings_2024;

public class SwitchCase_Java15 {

	public static void main(String[] args) {

		var n = 3;
		var m = switch (n) {
		case 1 -> "Mon";
		case 2 -> "Tue";
		case 3 -> "Wed";
		case 4 -> "Thu";
		case 5 -> "Fri";
		case 6 -> "Sat";
		case 7 -> "Sun";

		default -> "Not Found";
		};
		System.out.println(m);
	}

}
