package com.nxtlife.efkon.msil.issueManagement.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nxtlife.efkon.msil.issueManagement.dto.MailRequest;
import com.nxtlife.efkon.msil.issueManagement.dto.MailResponse;
import com.nxtlife.efkon.msil.issueManagement.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@PreAuthorize("hasAnyRole('SUPPORT')")
	@PostMapping("/support/sendingEmail")
	public MailResponse sendEmail(@RequestBody MailRequest request , String fromEmail ,  String toEmail)
			throws AddressException, MessagingException, IOException, GeneralSecurityException {
		notificationService.sendEmail(request, fromEmail , toEmail);
		return new MailResponse("Mail sent successfully", true);

	}

}
