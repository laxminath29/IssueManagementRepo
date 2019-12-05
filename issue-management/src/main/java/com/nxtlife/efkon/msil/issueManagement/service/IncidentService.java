package com.nxtlife.efkon.msil.issueManagement.service;

import java.util.List;

import com.nxtlife.efkon.msil.issueManagement.entity.Incident;
import com.nxtlife.efkon.msil.issueManagement.utility.CustomException;

public interface IncidentService {

	public List<Incident> getAllIncidents();

	public Incident getIncidentByID(Long incidentID) throws CustomException, IllegalArgumentException;

	public List<Incident> getAllIncidentsByTransporterID(String transporterID)
			throws CustomException, IllegalArgumentException;

	public Incident saveIncident(Incident incident) throws IllegalArgumentException;

	public Incident updateIncident(Long incidentID, Incident incident) throws CustomException, IllegalArgumentException;

	public void deleteIncidentByID(Long incidentId) throws CustomException, IllegalArgumentException;

	public void deleteAllIncidents();

}
