package dsa_RSA;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Section10_HashMap01_BasicHashMap {

	public static void main(String[] args) {
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(01, "Raj");
		map.put(02, "Sanket");
		map.put(03, "Nikhil");
		map.put(04, "Shruti");
		System.out.println("Value of Key "+map.get(01));
		
		Set<Entry<Integer, String>> entrySet = map.entrySet();
		for(Entry<Integer, String> entry : entrySet) {
			System.out.println(entry.getKey()+ " : "+entry.getValue());
		}
		

	}

}
