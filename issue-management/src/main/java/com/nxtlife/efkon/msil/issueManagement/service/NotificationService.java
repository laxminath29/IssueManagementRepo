package com.nxtlife.efkon.msil.issueManagement.service;

import java.io.IOException;

import java.util.Date;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.nxtlife.efkon.msil.issueManagement.dto.MailRequest;
import com.nxtlife.efkon.msil.issueManagement.utility.EmailConstants;

@Service
public class NotificationService {

	public void sendEmail(MailRequest request) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailConstants.FROM_EMAIL, EmailConstants.FROM_PASSWORD);
			}
		});

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(EmailConstants.FROM_EMAIL, false));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EmailConstants.TO_EMAIL));
		msg.setSubject(request.getSubject());
		msg.setContent(request.getContent(), "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart msgBodyPart = new MimeBodyPart();
		msgBodyPart.setContent(request.getContent(), "text/html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(msgBodyPart);

		MimeBodyPart attachPart = new MimeBodyPart();
		attachPart.attachFile(request.getFilePath());
		multipart.addBodyPart(attachPart);
		msg.setContent(multipart);
		Transport.send(msg);

	}

}
