package com.example.school_platform.controllers;

import com.example.school_platform.models.dto.EmailDTO;
import com.example.school_platform.utilities.EmailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {

	@PostMapping("/send")
	public String sendEmail(@RequestBody EmailDTO emailDTO){
		EmailSender emailSender = new EmailSender(
				emailDTO.getSmtpServer(),
				emailDTO.getPort(),
				emailDTO.getSender(),
				emailDTO.getEmailRecipients());

		emailSender.setProperties();
		try {
			emailSender.composeAndSendMessage(
					emailDTO.getSubject(),
					emailDTO.getMessage(),
					emailDTO.getPassword());
			return "SUCCESS";
		} catch (MessagingException e) {
			return "FAILED";
		}
	}
}
