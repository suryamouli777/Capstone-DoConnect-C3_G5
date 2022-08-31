package com.wipro.gl.doconnect.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String type;
	private String name;
	private byte[] imgByte;
	
	public Image(String name, String type, byte[] picByte)
	{
		this.name = name;
		this.type = type;
		this.imgByte = picByte;
	}
}
