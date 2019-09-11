package com.nxtlife.efkon.msil.issueManagement.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.nxtlife.efkon.msil.issueManagement.entity.Incident;
import com.nxtlife.efkon.msil.issueManagement.service.IncidentServiceImpl;
import com.nxtlife.efkon.msil.issueManagement.utility.CustomException;
import com.nxtlife.efkon.msil.issueManagement.utility.IssueType;
import com.nxtlife.efkon.msil.issueManagement.dto.HttpResponseDto;
import com.nxtlife.efkon.msil.issueManagement.dto.MailRequest;
import com.nxtlife.efkon.msil.issueManagement.dto.MailResponse;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/incidents")
public class IncidentController {

	@Autowired
	private IncidentServiceImpl incidentServiceImpl;
	@Autowired
	private NotificationController notificationController;
	/*
	 * @Autowired ResourceLoader resourceLoader;
	 * 
	 * Resource resource = resourceLoader.getResource("classpath:efkonLogo.png");
	 * 
	 */

	@GetMapping(value = "/issueType")
	public ResponseEntity<?> getIssueType() {

		return new ResponseEntity<List<String>>(IssueType.getIssueTypes(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getAllIncidents() {
		List<Incident> incidentsList = incidentServiceImpl.getAllIncidents();
		if (incidentsList.isEmpty()) {
			return new ResponseEntity<HttpResponseDto>(
					new HttpResponseDto("There are no Incidents in the record ..", HttpStatus.NO_CONTENT),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Incident>>(incidentsList, HttpStatus.OK);
	}

	@GetMapping(value = "/{incidentID}")
	public ResponseEntity<?> getIncidentById(@PathVariable Long incidentID) {
		Incident incident;
		try {
			incident = incidentServiceImpl.getIncidentByID(incidentID);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<HttpResponseDto>(new HttpResponseDto(iae.getMessage(), HttpStatus.BAD_REQUEST),
					HttpStatus.BAD_REQUEST);

		} catch (CustomException ce) {
			return new ResponseEntity<HttpResponseDto>(new HttpResponseDto(ce.getMessage(), HttpStatus.NO_CONTENT),
					HttpStatus.OK);
		}

		return new ResponseEntity<Incident>(incident, HttpStatus.OK);
	}

	@GetMapping(value = "transporter/{transporterID}")

	public ResponseEntity<?> getIncidentsByTransporterID(@PathVariable String transporterID) {
		List<Incident> incident;
		try {
			incident = incidentServiceImpl.getAllIncidentsByTransporterID(transporterID);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<HttpResponseDto>(new HttpResponseDto(iae.getMessage(), HttpStatus.BAD_REQUEST),
					HttpStatus.BAD_REQUEST);

		} catch (CustomException ce) {
			return new ResponseEntity<HttpResponseDto>(new HttpResponseDto(ce.getMessage(), HttpStatus.NO_CONTENT),
					HttpStatus.OK);
		}

		return new ResponseEntity<List<Incident>>(incident, HttpStatus.OK);

	}

	@PutMapping(value = "/{incidentID}")
	public ResponseEntity<?> updateIncident(@PathVariable Long incidentID, @RequestBody Incident incident) {
		Incident currentIncident;
		try {
			currentIncident = incidentServiceImpl.updateIncident(incidentID, incident);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<HttpResponseDto>(new HttpResponseDto(iae.getMessage(), HttpStatus.BAD_REQUEST),
					HttpStatus.BAD_REQUEST);
		} catch (CustomException ce) {
			return new ResponseEntity<HttpResponseDto>(new HttpResponseDto(ce.getMessage(), HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Incident>(currentIncident, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> saveIncident(@RequestBody Incident incident) {

		Incident currentIncident;
		try {
			currentIncident = incidentServiceImpl.saveIncident(incident);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<HttpResponseDto>(new HttpResponseDto(iae.getMessage(), HttpStatus.BAD_REQUEST),
					HttpStatus.BAD_REQUEST);
		}

		MailRequest mailRequest = new MailRequest(
				"<b> Incident ID :</b>" + currentIncident.getIncidentID() + "<br> <b>Issue Type: </b>"
						+ currentIncident.getIssueType() + "<br> <b>Transporter Name : </b>"
						+ currentIncident.getTransporterName() + "<br><b> Transporter ID :</b>"
						+ currentIncident.getTransporterID() + "<br> <b>Remarks : </b>" + currentIncident.getRemarks()
						+ "<br> <b>Report Time : </b>" + currentIncident.getReportDateTime(),
				"Complaint lodged by  " + currentIncident.getUsername());
		try {
			notificationController.sendEmail(mailRequest);
		} catch ( IOException e) {

			e.printStackTrace();
		}
		catch(MessagingException me ) {
			me.printStackTrace();
		}
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("Incident", currentIncident);
		response.put("MailResponse", new MailResponse("Email sent successfully !! ", true));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@DeleteMapping(value = "/{incidentID}")
	public ResponseEntity<?> deleteIncidentByID(@PathVariable Long incidentID) {

		try {
			incidentServiceImpl.deleteIncidentByID(incidentID);
		} catch (IllegalArgumentException iae) {
			return new ResponseEntity<HttpResponseDto>(new HttpResponseDto(iae.getMessage(), HttpStatus.BAD_REQUEST),
					HttpStatus.BAD_REQUEST);
		} catch (CustomException ce) {
			return new ResponseEntity<HttpResponseDto>(new HttpResponseDto(ce.getMessage(), HttpStatus.NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<HttpResponseDto>(new HttpResponseDto("Deleted Successfully..", HttpStatus.NO_CONTENT),
				HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAllIncidents() {
		incidentServiceImpl.deleteAllIncidents();
		return new ResponseEntity<HttpResponseDto>(new HttpResponseDto("Deleted Successfully..", HttpStatus.NO_CONTENT),
				HttpStatus.NO_CONTENT);
	}
}
