package org.szh.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Result<T> {
	
	private String code="200";
	
	private String msg="success";
	
	private T data;
	
	public Result(T data) {
		this.data = data;
	}

}
