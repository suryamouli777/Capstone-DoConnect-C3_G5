package com.wipro.gl.doconnect.chat.service;

import java.util.List;

import com.wipro.gl.doconnect.chat.dto.MessageDTO;
import com.wipro.gl.doconnect.chat.entity.Message;

public interface IMessageService
{
	public Message sendMessage(MessageDTO messageDTO);
	public List<MessageDTO> getMessage();
}
