package javaStreams_2024;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Part03_ArrayListIteration {

	public static void main(String[] args) {
		
		ArrayList<String> tvSeries = new ArrayList<String>();
		tvSeries.add("Game of Thrones");
		tvSeries.add("Breaking Bad");
		tvSeries.add("The Walking Dead");
		tvSeries.add("Friends");
		tvSeries.add("Big Bang Theory");
		
		System.out.println("-----1.Using Java8 with forEach & Lambada expression-----");
		tvSeries.forEach(shows -> {
			System.out.println(shows);
		});
		
		
		System.out.println("---------------2.Using Iterator---------------");
		Iterator<String> it = tvSeries.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	
		System.out.println("----------3.Using Iterator and forEachRemaining() ----------");
		it = tvSeries.iterator();
		it.forEachRemaining(shows -> {
			System.out.println(shows);
		});
	
		System.out.println("----------4.Using ForEach Loop----------");
		for(String shows : tvSeries) {
			System.out.println(shows);
		}
		
		
		System.out.println("----------5.Using ForLoop with index/order----------");
		for(int i=0; i<tvSeries.size(); i++) {
			System.out.println(tvSeries.get(i));
		}
		
		System.out.println("----------6.Using ListIterator----------");
		
		ListIterator<String> tvSeriesIterator = tvSeries.listIterator(tvSeries.size());
		while(tvSeriesIterator.hasPrevious()) {
			String show = tvSeriesIterator.previous();
			System.out.println(show);
		}
		
		
		
	}
	
	

}
