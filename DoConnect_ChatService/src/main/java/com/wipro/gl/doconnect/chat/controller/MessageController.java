package com.wipro.gl.doconnect.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.gl.doconnect.chat.dto.MessageDTO;
import com.wipro.gl.doconnect.chat.entity.Message;
import com.wipro.gl.doconnect.chat.service.IMessageService;
@CrossOrigin("*")
@RestController
@RequestMapping("/chat")

public class MessageController
{
	@Autowired
	private IMessageService iMessageService;

	@PostMapping("/sendMessage")
	public Message sendMessage(@Valid @RequestBody MessageDTO messageDTO) {
		System.out.println(messageDTO.getMessage());
		System.out.println("hello");
		return iMessageService.sendMessage(messageDTO);
	}

	@GetMapping("/getMessage")
	public List<MessageDTO> getMessage() {
		return iMessageService.getMessage();
	}
}