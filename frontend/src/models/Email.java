package models;

public class Email {

	private String smtpServer;
	private String port;
	private String sender;
	private String password;
	private String[] emailRecipients;
	private String subject;
	private String message;

	public Email(String smtpServer, String port, String sender, String password, String[] emailRecipients, String subject, String message) {
		this.smtpServer = smtpServer;
		this.port = port;
		this.sender = sender;
		this.password = password;
		this.emailRecipients = emailRecipients;
		this.subject = subject;
		this.message = message;
	}


	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getEmailRecipients() {
		return emailRecipients;
	}

	public void setEmailRecipients(String[] emailRecipients) {
		this.emailRecipients = emailRecipients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
