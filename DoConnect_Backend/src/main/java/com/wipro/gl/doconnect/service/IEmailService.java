package com.wipro.gl.doconnect.service;

import org.springframework.context.annotation.Bean;

/*
* Author : B.surya mouli,
* Modified date : 28-08-2022 
* Description :interface for email service
* 
*/
public interface IEmailService {

	void send(String subject,String message);
   
}
