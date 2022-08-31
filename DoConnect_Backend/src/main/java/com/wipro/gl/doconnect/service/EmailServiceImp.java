package com.wipro.gl.doconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/*
* Author :B.surya mouli,B.krishna chaitanya
* Modified date : 28-08-2022 
* Description : EmailService implementation class 
* 
*/

@Service
public class EmailServiceImp implements IEmailService{

	 	@Autowired
	    private JavaMailSender javaMailSender;
	   

	 	/*
		* Author : B.surya mouli,B.krishna chaitanya
		* Modified date : 28-08-2022 
		* Description : send email  method 
		* parameters : Subject, massage
		* return type : void 
		*/
	    @Override
	    public void send(String subject, String message) {
	    		System.out.println("this admin");
	    		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
	            simpleMailMessage.setFrom("greatlearning29@gmail.com");
	            simpleMailMessage.setTo("ug.courseonline@gmail.com");
	            simpleMailMessage.setSubject(subject);
	            simpleMailMessage.setText(message);
	            this.javaMailSender.send(simpleMailMessage);
	            System.out.println("sent");
	    }
}
