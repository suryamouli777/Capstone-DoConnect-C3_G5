package com.wipro.gl.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageDTO {

	private long id;
	private String name;
	private String type;
	private byte[] imgByte;
}
