package com.nxtlife.efkon.msil.issueManagement.dto;

public class MailRequest {

	private String subject;
	private String content;
	private String filePath;

	public MailRequest() {
		super();
	}

	public MailRequest(String content, String subject, String filePath) {
		super();

		this.content = content;
		this.subject = subject;
		this.filePath = filePath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
