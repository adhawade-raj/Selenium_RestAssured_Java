package int_JavaPrograms_2024;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class String_DuplicatesCharacterUsingHashmap {

	public static void main(String[] args) {
		
		printDuplicateCharacters("aa");
		printDuplicateCharacters("adhawade");
	}
	
	public static void printDuplicateCharacters(String str) {
	char words[] = str.toCharArray();
	
	Map<Character, Integer> charMap = new HashMap<Character, Integer>();
	for(Character ch :words) {
		if(charMap.containsKey(ch)) {
		charMap.put(ch, charMap.get(ch)+1);
		}
		else {
			charMap.put(ch, 1);
		}
	}
	//Approach 1 of printing hashmap
	System.out.println("______________________________");
	System.out.println(charMap);
	System.out.println("-------------------------------");
	
	
	//To print the Map in key value format
	Set<Map.Entry<Character, Integer>> entrySet = charMap.entrySet();
	for(Map.Entry<Character, Integer> entry : entrySet) {
		if(entry.getValue()>1) {
			System.out.println(entry.getKey()+ " : "+ entry.getValue());
		}
	  }
	}
}
