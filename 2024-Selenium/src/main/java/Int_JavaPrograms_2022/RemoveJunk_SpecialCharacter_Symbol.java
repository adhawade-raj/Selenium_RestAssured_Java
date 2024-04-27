package Int_JavaPrograms_2022;

public class RemoveJunk_SpecialCharacter_Symbol {

	public static void main(String[] args) {

		String a = "!@#$%^&*()Raj21101997";
		System.out.println(a.replaceAll("[^a-zA-Z0-9]", ""));
		
        String b = "!@#$%^&*()Java!@#$%^Python";
		System.out.println(b.replaceAll("[^a-zA-Z0-9]", ""));
	}

}
