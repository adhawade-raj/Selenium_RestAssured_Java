package com.gorest.in;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PUT_01_UpdateUser_Pojo {
	
	private Integer id;
	private String name;
	private String email;
	private String gender;
	private String status;
	
	
	public PUT_01_UpdateUser_Pojo(String name, String email, String gender, String status) {
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}

}
