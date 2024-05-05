package JavaPrograms;

import java.util.HashMap;

public class CountOccurancesOfCharacterUsingHashmap {

	public static void main(String[] args) {
	String str = "adhawade";
	
	HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
	char strArray[] = str.toCharArray();
	
	for(char c:strArray ) {
		
		if(charCountMap.containsKey(c)) {
			charCountMap.put(c, charCountMap.get(c)+1);
		}else{
			charCountMap.put(c, 1);
		}
	}
	System.out.println("Input String : "+str+" "+charCountMap);

	}

}
