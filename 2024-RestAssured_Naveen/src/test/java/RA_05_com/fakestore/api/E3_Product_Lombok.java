package RA_05_com.fakestore.api;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class E3_Product_Lombok {
	
	private int id;
	private String title;
	 private float price;
	private String description;
	private String category;
	private String image;
	 private Rating rating;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class Rating{
		private double rate;
		private int count;
		
}
}
