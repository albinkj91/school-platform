package com.example.school_platform.utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

	private Session session;
	private Message message;
	private Properties properties;

	private String smtpServer;
	private String port;
	private String sender;
	private final InternetAddress[] emailRecipients;

	public EmailSender(String smtpServer, String port, String sender, InternetAddress[] emailRecipients) {
		this.smtpServer = smtpServer;
		this.port = port;
		this.sender = sender;
		this.emailRecipients = emailRecipients;
	}

	public void setProperties(){
		properties = new Properties();
		properties.put("mail.smtp.host", smtpServer);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
	}

	public void composeAndSendMessage(String subject, String messageToSend, String password) throws MessagingException {
		session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});
		message = new MimeMessage(session);

		message.setFrom(new InternetAddress(sender));
		message.setRecipients(Message.RecipientType.TO, emailRecipients);
		message.setSubject(subject);
		message.setText(messageToSend);
		message.setSentDate(new Date());

		Transport.send(message);
	}
}
