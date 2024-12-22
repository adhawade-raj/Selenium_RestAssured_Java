package Java_NewLearnings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PrintCharactersWithHighestFrequency {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "apdpjpjpqsj";
//		o/p= ppppjjjaqsd

		char ch[] = str.toCharArray();
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (char c : ch) {

			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);

			} else {
				map.put(c, 1);

			}
		}

		/** Add Map Entries to List */
		List<Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

		/** Sort entries by value descending */
		list.sort(Entry.comparingByValue(Comparator.reverseOrder()));

		for (Entry<Character, Integer> entry : list) {
			for (int i = 0; i < entry.getValue(); i++) {
				System.out.print(entry.getKey());
			}
//			System.out.print(entry.getValue());
		}

	}

}
