package com.wipro.gl.doconnect.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

	private String msg;
	
	private HttpStatus status;
	private long imgid;
}
