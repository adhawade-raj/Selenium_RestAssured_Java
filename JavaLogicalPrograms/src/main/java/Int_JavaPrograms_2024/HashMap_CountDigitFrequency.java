package Int_JavaPrograms_2024;

import java.util.HashMap;
import java.util.Set;

public class HashMap_CountDigitFrequency {
	
	public static void frequencyCount(long number) {
		
	HashMap<Long, Integer> map = new HashMap<Long, Integer>();
	while(number!=0) {
		long lastDigit = number%10;
		if(map.containsKey(lastDigit)) {
			map.put(lastDigit, map.get(lastDigit)+1);
		}
		else {
			map.put(lastDigit, 1);
		}
		number = number/10;
	}
	
	System.out.println("====Printing Map Approach 1=====");
	System.out.println(map);
	
	System.out.println("====Printing Map Approach 2=====");
	Set<Long> keys = map.keySet();
	for(Long key : keys) {
		System.out.println(key+ " : "+map.get(key));
	}
		
	}
	public static void main(String[] args) {
	
		frequencyCount(121);

	}

}
