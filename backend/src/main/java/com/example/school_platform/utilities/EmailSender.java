package com.example.school_platform.utilities;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

	private Properties properties;

	private final String smtpServer;
	private final String port;
	private final String sender;
	private final InternetAddress[] emailRecipients;

	public EmailSender(String smtpServer, String port, String sender, String[] emailRecipients) {
		this.smtpServer = smtpServer;
		this.port = port;
		this.sender = sender;
		this.emailRecipients = setEmailRecipients(emailRecipients);
	}

	private InternetAddress[] setEmailRecipients(String[] emailRecipients) {
		InternetAddress[] emails = new InternetAddress[emailRecipients.length];

		for(int i = 0; i < emailRecipients.length; i++) {
			try {
				emails[i] = new InternetAddress(emailRecipients[i]);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		return emails;
	}

	public void setProperties(){
		properties = new Properties();
		properties.put("mail.smtp.host", smtpServer);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
	}

	public void composeAndSendMessage(String subject, String messageToSend, String password) throws MessagingException {
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		});
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(sender));
		message.setRecipients(Message.RecipientType.TO, emailRecipients);
		message.setSubject(subject);
		message.setText(messageToSend);
		message.setSentDate(new Date());

		Transport.send(message);
	}
}
