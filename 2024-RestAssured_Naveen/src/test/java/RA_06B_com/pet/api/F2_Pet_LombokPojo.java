package RA_06B_com.pet.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)  //You can igonre the null values with this
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class F2_Pet_LombokPojo {

	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tags;
	private String Status;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Category{
		private Integer id;
		private String name;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Tag{
		private Integer id;
		private String name;
	}
	
	
}
