package com.nxtlife.efkon.msil.issueManagement.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nxtlife.efkon.msil.issueManagement.dto.MailRequest;
import com.nxtlife.efkon.msil.issueManagement.entity.Incident;
import com.nxtlife.efkon.msil.issueManagement.repository.IncidentRepository;
import com.nxtlife.efkon.msil.issueManagement.utility.CustomException;
import com.nxtlife.efkon.msil.issueManagement.utility.EmailConstants;
import com.nxtlife.efkon.msil.issueManagement.utility.IssueType;

@Service
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	@Override
	public List<Incident> getAllIncidents() {
		List<Incident> incidentList = new ArrayList<Incident>();

		incidentList.addAll(incidentRepository.findAll());

		for (Incident incident : incidentList) {
			incident.setIssueTypeStr(IssueType.revMapping.get(incident.getIssueType()));
			// incident.setReportDateTime( Timestamp.valueOf(
			// incident.getReportDateTime().toString()));
			// incident.setReportDateTime(new
			// Timestamp(incident.getReportDateTime().getTime()));
		}

		return incidentList;

	}

	@Override
	public Incident getIncidentByID(Long incidentID) throws CustomException, IllegalArgumentException {
		if (incidentID == null) {
			throw new IllegalArgumentException("Incident Id can not be Empty/null ..");
		}
		Incident incident = incidentRepository.findOne(incidentID);
		if (incident == null) {
			throw new CustomException("Incident having ID : " + incidentID + " does not exist..");
		}
		incident.setIssueTypeStr(IssueType.revMapping.get(incident.getIssueType()));
		// incident.setReportDateTime(new
		// Timestamp(incident.getReportDateTime().getTime()));
		return incident;
	}

	@Override
	public Incident saveIncident(Incident incident) throws IllegalArgumentException {
		if (incident == null) {
			throw new IllegalArgumentException("Incident record passed can not be empty/null .");
		}
		incident.setReportDateTime(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).toLocalDateTime());
        incident.setSupportRemark(null);
		incident.setResolvedDateTime(null);
		incident.setIssueType(IssueType.mapping.get(incident.getIssueTypeStr()));
		// incident.setIssueTypeStr(incident.getIssueTypeStr());
		Incident savedIncident = incidentRepository.save(incident);
		savedIncident.setIssueTypeStr(IssueType.revMapping.get(savedIncident.getIssueType()));
		// savedIncident.setReportDateTime(new
		// Timestamp(savedIncident.getReportDateTime().getTime()));
		return savedIncident;
	}

	@Override
	public Incident updateIncident(Long incidentID, Incident incident)
			throws CustomException, IllegalArgumentException {
		if (incident == null) {
			throw new IllegalArgumentException("Incident record passed can not be empty/null .");
		}
		Incident currentIncident = getIncidentByID(incidentID);
		if (currentIncident == null) {
			throw new CustomException("Can not update record as it does not exist..");
          }
		// currentIncident.setIssueTypeStr(incident.getIssueTypeStr());
		currentIncident.setIssueType(incident.getIssueTypeStr() != null ? IssueType.mapping.get(incident.getIssueTypeStr()) : currentIncident.getIssueType());
		if(incident.getSupportRemark()!=null) {
			//currentIncident.setResolvedDateTimeStr(LocalDateTime.now().toString());
			currentIncident.setSupportRemark(incident.getSupportRemark());
			currentIncident.setResolvedDateTime(ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).toLocalDateTime());
		}
		currentIncident.setRemarks(incident.getRemarks() != null ? incident.getRemarks() : currentIncident.getRemarks());
		currentIncident.setTransporterName(incident.getTransporterName() != null ? incident.getTransporterName() : currentIncident.getTransporterName());
		currentIncident.setSubjectLine(incident.getSubjectLine() != null ? incident.getSubjectLine() : currentIncident.getSubjectLine());
		currentIncident.setUserName(incident.getUsername() != null ? incident.getUsername() : currentIncident.getUsername());
		currentIncident.setTransporterID(incident.getTransporterID() != null ? incident.getTransporterID() : currentIncident.getTransporterID());
		//currentIncident.setEmail(incident.getEmail() != null ? incident.getEmail() : currentIncident.getEmail());
		 if (incident.getIsClosed()==true)
			  currentIncident.setIsClosed(true);
		 currentIncident.setIsClosed(incident.getIsClosed());
		  /*
		  MailRequest mailRequest = new MailRequest(
				"<b> Subject Line :</b>" + currentIncident.getSubjectLine() + "<br> <b> Incident ID :</b>"
						+ currentIncident.getIncidentID() + "<br> <b>Issue Type: </b>"
						+ currentIncident.getIssueTypeStr() + "<br> <b>Transporter Name : </b>"
						+ currentIncident.getTransporterName() + "<br><b> Transporter ID :</b>"
						+ currentIncident.getTransporterID() + "<br> <b>Remarks : </b>" + currentIncident.getRemarks()
						+ "<br> <b>Report Time : </b>" + currentIncident.getReportDateTime(),
				"Complaint lodged by  " + currentIncident.getUsername());
					mailRequest.setSubject("Incident Closure Notification");
		try {
			notificationController.sendEmail(mailRequest , EmailConstants.EMAIL_VTS, EmailConstants.PASSWORD_VTS , currentIncident.getEmail());
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}
		   */
		Incident savedIncident = incidentRepository.save(currentIncident);
		savedIncident.setIssueTypeStr(IssueType.revMapping.get(savedIncident.getIssueType()));
		// savedIncident.setReportDateTime(new
		// Timestamp(savedIncident.getReportDateTime().getTime()));
		return savedIncident;
	}

	@Override
	public void deleteIncidentByID(Long incidentId) throws CustomException, IllegalArgumentException {
		if (incidentId == null) {
			throw new IllegalArgumentException("Incident Id can not be Empty/null ..");
		}

		if (incidentRepository.exists(incidentId)) {
			incidentRepository.delete(incidentId);
		} else {
			throw new CustomException("Can not delete as the specified record is not present..");
		}

	}

	@Override
	public void deleteAllIncidents() {
		incidentRepository.deleteAll();

	}

	@Override
	public List<Incident> getAllIncidentsByTransporterID(String transporterID)
			throws CustomException, IllegalArgumentException {
		if (transporterID == null) {
			throw new IllegalArgumentException("Transporter Id can not be Empty/null ..");
		}
		List<Incident> incidentList = incidentRepository.findAllByTransporterID(transporterID);

		if (incidentList.isEmpty()) {
			throw new CustomException("Incidents for Transporter having ID : " + transporterID + " does not exist..");
		}

		for (Incident incident : incidentList) {
			incident.setIssueTypeStr(IssueType.revMapping.get(incident.getIssueType()));
		}

		return incidentList;
	}

}
