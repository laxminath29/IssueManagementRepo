package com.nxtlife.efkon.msil.issueManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.nxtlife.efkon.msil.issueManagement.utility.IssueType;

@Entity
@Table(name = "INCIDENT_TABLE")
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long incidentID;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "ISSUE_TYPE")
	private IssueType issueType;

	@NotNull
	private String transporterName;

	private String username;

	@NotNull
	private Integer transporterID;

	@Email
	private String email;

	private String contactNumber;

	@NotNull
	private String remarks;

	private String location;

	private String vehicleNumber;

	private Boolean isClosed;

	public Long getIncidentID() {
		return incidentID;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public String getTransporterName() {
		return transporterName;
	}

	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public Integer getTransporterID() {
		return transporterID;
	}

	public void setTransporterID(Integer transporterID) {
		this.transporterID = transporterID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	@PrePersist
	private void isClosedIntially() {
		setIsClosed(false);
		setIssueType(IssueType.OTHER);
	}

	public Incident() {
		super();
	}

	public Incident(@NotNull IssueType issueType, @NotNull String transporterName, @Email String email,
			String contactNumber, @NotNull String remarks, String location, String vehicleNumber, Boolean isClosed,
			@NotNull Integer transporterID, String username) {
		super();
		this.issueType = issueType;
		this.transporterName = transporterName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.remarks = remarks;
		this.location = location;
		this.vehicleNumber = vehicleNumber;
		this.isClosed = isClosed;
		this.transporterID = transporterID;
		this.username = username;
	}

	public Incident(@NotNull IssueType issueType, @NotNull String transporterName, @NotNull String remarks,
			Boolean isClosed, @NotNull Integer transporterID, String username) {
		super();
		this.issueType = issueType;
		this.transporterName = transporterName;
		this.remarks = remarks;
		this.isClosed = isClosed;
		this.transporterID = transporterID;
		this.username = username;
	}

	public Incident(@NotNull IssueType issueType, @NotNull String transporterName, @NotNull String remarks,
			@NotNull Integer transporterID) {
		super();
		this.issueType = issueType;
		this.transporterName = transporterName;
		this.remarks = remarks;
		this.transporterID = transporterID;
	}

}
