package com.wipro.gl.doconnect.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class NotFound extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private String errorMsg;
}