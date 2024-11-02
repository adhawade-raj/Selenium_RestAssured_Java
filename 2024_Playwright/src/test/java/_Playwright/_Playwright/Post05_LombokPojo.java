package _Playwright._Playwright;


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

public class Post05_LombokPojo {
	private int id;
	private String name;
	private String email;
	private String gender;
	private String status;

	
	public Post05_LombokPojo(String name, String email, String gender, String status){
		this.name=name;
		this.email=email;
		this.gender=gender;
		this.status=status;
	}
}
