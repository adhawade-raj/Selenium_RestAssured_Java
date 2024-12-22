package int_JavaPrograms_2024;


public class String_CountCapitalWords {

	public static void main(String[] args) {
		
		String str ="RajADhawade";
		int count=0;
		
		//Approach 1 : Using A And Z Range 
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)>='A' && str.charAt(i)<='Z') {
				count++;
				System.out.println("Capital Letter is : "+str.charAt(i));
			}
		}
		System.out.println("Count of Capital Letter is :"+count);
		System.out.println();
		
		System.out.println("__Approach 2 : Using ASCII Value of A & Z__");
		int count1=0;
//		Approach 2 : Using ASCII Value of A & Z
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)>=65 && str.charAt(i)<=90) {
				count1++;
				System.out.println("Capital Letter is : "+str.charAt(i));
			}
		}
		System.out.println("Count of Capital Letter is :"+count1);
		System.out.println();
		
		System.out.println("__Approach 2 : Using Character class function of isUpperCase__");
		int count2=0;
//		Approach 2 : Using Character class function of isUpperCase
		for(int i=0; i<str.length(); i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				count2++;
				System.out.println("Capital Letter is : "+str.charAt(i));
			}
		}		
		System.out.println("Count of Capital Letter is :"+count2);

	}

}
