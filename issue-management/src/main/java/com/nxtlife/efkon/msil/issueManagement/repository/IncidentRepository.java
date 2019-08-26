package com.nxtlife.efkon.msil.issueManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nxtlife.efkon.msil.issueManagement.entity.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

	public List<Incident> findAllByTransporterID(Integer transporterID);
}
