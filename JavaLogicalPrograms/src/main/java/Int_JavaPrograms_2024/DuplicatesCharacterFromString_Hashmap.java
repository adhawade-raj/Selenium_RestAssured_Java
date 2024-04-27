package Int_JavaPrograms_2024;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DuplicatesCharacterFromString_Hashmap {

	public static void main(String[] args) {
		
		printDuplicateCharacters("aa");
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
	
	//To print the Map
	Set<Map.Entry<Character, Integer>> entrySet = charMap.entrySet();
	for(Map.Entry<Character, Integer> entry : entrySet) {
		if(entry.getValue()>1) {
			System.out.println(entry.getKey()+ " : "+ entry.getValue());
		}
	}
	}
}
