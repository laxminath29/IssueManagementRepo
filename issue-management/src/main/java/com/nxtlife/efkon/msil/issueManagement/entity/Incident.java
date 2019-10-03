package com.nxtlife.efkon.msil.issueManagement.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nxtlife.efkon.msil.issueManagement.utility.IssueType;

@Entity
@Table(name = "INCIDENT_TABLE")
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long incidentID;

	
	@Enumerated(EnumType.STRING)
	@Column(name = "ISSUE_TYPE")
	private IssueType issueType;

	@NotNull
	private String transporterName;

	private String username;

	@NotNull
	private String transporterID;

	@Email
	private String email;

	private String contactNumber;

	@NotNull
	private String remarks;
	
	private String supportRemark;

	private String location;

	private String vehicleNumber;

	private Boolean isClosed;
	
	
	private LocalDateTime reportDateTime;
	
	@Transient
	private String reportDateTimeStr;
	
	
	@NotNull
	@Transient
	private String issueTypeStr;
	
	

	
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public LocalDateTime getReportDateTime() {
		
		return reportDateTime;
	}

	public void setReportDateTime(LocalDateTime reportDateTime) {
		this.reportDateTime = reportDateTime;
		setReportDateTimeStr(this.reportDateTime==null?" ":this.reportDateTime.toString());
	}
	
	
	public String getReportDateTimeStr() {
		
		return reportDateTimeStr=reportDateTime==null?" ":reportDateTime.toString();
	}

	public void setReportDateTimeStr(String reportDateTimeStr) {
		this.reportDateTimeStr = reportDateTimeStr;
	}


	public Long getIncidentID() {
		return incidentID;
	}
	
	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	
	public String getIssueTypeStr() {
		return issueTypeStr;
	}

	public void setIssueTypeStr(String issueTypeStr) {
		this.issueTypeStr = issueTypeStr;
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

	public String getTransporterID() {
		return transporterID;
	}

	public void setTransporterID(String transporterID) {
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

	
	public String getSupportRemark() {
		return supportRemark;
	}

	public void setSupportRemark(String supportRemark) {
		this.supportRemark = supportRemark;
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
		if(this.issueType==null)
		  setIssueType(IssueType.OTHER);
		
	}

	public Incident() {
		super();
	}

	public Incident(@NotNull String issueTypeStr, @NotNull String transporterName, @Email String email,
			String contactNumber, @NotNull String remarks, String location, String vehicleNumber, Boolean isClosed,
			@NotNull String transporterID, String username , String supportRemark) {
		super();
		this.issueTypeStr = issueTypeStr;
		
		this.transporterName = transporterName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.remarks = remarks;
		this.location = location;
		this.vehicleNumber = vehicleNumber;
		this.isClosed = isClosed;
		this.transporterID = transporterID;
		this.username = username;
		this.supportRemark=supportRemark;
	}

	public Incident(@NotNull String issueTypeStr, @NotNull String transporterName, @NotNull String remarks,
			Boolean isClosed, @NotNull String transporterID, String username , String supportRemark) {
		super();
		this.issueTypeStr = issueTypeStr;
		this.supportRemark=supportRemark;
		this.transporterName = transporterName;
		this.remarks = remarks;
		this.isClosed = isClosed;
		this.transporterID = transporterID;
		this.username = username;
	}

	public Incident(@NotNull  String issueTypeStr, @NotNull String transporterName, @NotNull String remarks,
			@NotNull String transporterID) {
		super();
		this.issueTypeStr = issueTypeStr;
		
		this.transporterName = transporterName;
		this.remarks = remarks;
		this.transporterID = transporterID;
	}

}
