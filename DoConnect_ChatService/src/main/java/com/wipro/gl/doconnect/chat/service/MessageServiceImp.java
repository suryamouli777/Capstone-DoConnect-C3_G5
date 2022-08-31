package com.wipro.gl.doconnect.chat.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.gl.doconnect.chat.dto.MessageDTO;
import com.wipro.gl.doconnect.chat.entity.Message;
import com.wipro.gl.doconnect.chat.repository.MessageRepository;

@Service
public class MessageServiceImp implements IMessageService {
	@Autowired
	private MessageRepository messageRepository;

	@Override
	public Message sendMessage(MessageDTO messageDTO) {
		
		Message message = new Message();
		message.setMessage(messageDTO.getMessage());
		message.setFromUser(messageDTO.getFromUser());
		message.setUserId(messageDTO.getUserId());
		message = messageRepository.save(message);
		return message;
	}
	
	@Override
	public List<MessageDTO> getMessage() {
		List<MessageDTO> data = new ArrayList<MessageDTO>();

		List<Message> messages = messageRepository.findAll();
		for(Message message : messages) {

			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setFromUser(message.getFromUser());
			messageDTO.setMessage(message.getMessage());
			messageDTO.setUserId(message.getUserId());
			data.add(messageDTO);

		}

		return data;
	}
}
