package javaStreams_2024;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Part04_LambadaExp_MethodReferences {

	public static void main(String[] args) {

		ArrayList<String> tvSeries = new ArrayList<String>();
		tvSeries.add("Game of Thrones");
		tvSeries.add("Breaking Bad");
		tvSeries.add("The Walking Dead");
		tvSeries.add("Friends");
		tvSeries.add("Big Bang Theory");
		
		
		System.out.println("----------1.Annonymous Class----------");
		tvSeries.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
			System.out.println(t);
				
			}
		});

		
		System.out.println("----------2.Lambada Expression----------");
		tvSeries.forEach(t -> System.out.println(t));
		
		
		System.out.println("----------3.Method Reference----------");
		tvSeries.forEach(System.out::println);
		
	}

}
