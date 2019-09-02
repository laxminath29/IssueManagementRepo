package com.nxtlife.efkon.msil.issueManagement.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nxtlife.efkon.msil.issueManagement.entity.Incident;
import com.nxtlife.efkon.msil.issueManagement.repository.IncidentRepository;
import com.nxtlife.efkon.msil.issueManagement.utility.CustomException;
import com.nxtlife.efkon.msil.issueManagement.utility.IssueType;

@Service
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	@Override
	public List<Incident> getAllIncidents() {
		List<Incident> incidentList = new ArrayList<>();

		incidentRepository.findAll().forEach(incidentList::add);
		for(Incident incident : incidentList) {
			incident.setIssueTypeStr(IssueType.revMapping.get(incident.getIssueType()));
		}
		
		return incidentList;

	}

	@Override
	public Incident getIncidentByID(Long incidentID) throws CustomException, IllegalArgumentException {
		if (incidentID == null) {
			throw new IllegalArgumentException("Incident Id can not be Empty/null ..");
		}
		Incident incident = incidentRepository.findById(incidentID).orElse(null);
		

		if (incident == null) {
			throw new CustomException("Incident having ID : " + incidentID + " does not exist..");
		}
		incident.setIssueTypeStr(IssueType.revMapping.get(incident.getIssueType()));
		return incident;
	}

	@Override
	public Incident saveIncident(Incident incident) throws IllegalArgumentException {
		if (incident == null) {
			throw new IllegalArgumentException("Incident record passed can not be empty/null .");
		}
        incident.setReportDateTime( new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        incident.setIssueType(IssueType.mapping.get(incident.getIssueTypeStr().toString()));
       
		return incidentRepository.save(incident);
	}

	@Override
	public Incident updateIncident(Long incidentID, Incident incident)
			throws CustomException, IllegalArgumentException {
		if (incident == null) {
			throw new IllegalArgumentException("Incident record passed can not be empty/null .");
		}

		Incident currentIncident;

		currentIncident = getIncidentByID(incidentID);
		if (currentIncident == null) {
			throw new CustomException("Can not update record as it does not exist..");

		}
		currentIncident.setContactNumber(incident.getContactNumber());
		currentIncident.setEmail(incident.getEmail());
		currentIncident.setIsClosed(incident.getIsClosed());
		currentIncident.setIssueType(IssueType.mapping.get(incident.getIssueTypeStr().toString()));
		currentIncident.setLocation(incident.getLocation());
		currentIncident.setRemarks(incident.getRemarks());
		currentIncident.setTransporterName(incident.getTransporterName());
		currentIncident.setVehicleNumber(incident.getVehicleNumber());
		currentIncident.setUserName(incident.getUsername());
		currentIncident.setTransporterID(incident.getTransporterID());
		currentIncident.setReportDateTime( new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
		

		return incidentRepository.save(currentIncident);

	}

	@Override
	public void deleteIncidentByID(Long incidentId) throws CustomException, IllegalArgumentException {
		if (incidentId == null) {
			throw new IllegalArgumentException("Incident Id can not be Empty/null ..");
		}

		if (incidentRepository.existsById(incidentId)) {
			incidentRepository.deleteById(incidentId);
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
		for(Incident incident : incidentList) {
			incident.setIssueTypeStr(IssueType.revMapping.get(incident.getIssueType()));
		}

		return incidentList;
	}

}
