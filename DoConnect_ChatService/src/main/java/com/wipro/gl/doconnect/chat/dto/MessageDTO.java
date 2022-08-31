package com.wipro.gl.doconnect.chat.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO
{
	private long userId;
	@NotBlank(message = "provide the user Details")
	private String fromUser;

	@NotBlank(message = "provide message")
	private String message;
}
