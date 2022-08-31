package com.wipro.gl.doconnect.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*
* Author : D. Surya Vamshi
* Modified date : 24-08-2022 
* Description : 
* 
*/

@NoArgsConstructor
@AllArgsConstructor
public class AlreadyExists extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private String errorMsg;

}
