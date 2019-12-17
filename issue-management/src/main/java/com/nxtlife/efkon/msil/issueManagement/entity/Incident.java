package com.nxtlife.efkon.msil.issueManagement.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nxtlife.efkon.msil.issueManagement.utility.IssueType;

@SuppressWarnings("serial")
@Entity
@Table(name = "incident_table")
public class Incident implements Serializable {
	@Id
//	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	private Long incidentID;

	@Enumerated(EnumType.STRING)
	@Column(name = "ISSUE_TYPE")
	private IssueType issueType;

	@NotNull
	private String transporterName;

	private String username;

	@NotNull
	private String transporterID;

	@NotNull
	private String remarks;

	private String supportRemark;

	@NotNull
	private String subjectLine;

	private Boolean isClosed;

	private LocalDateTime reportDateTime;

	private LocalDateTime resolvedDateTime;

	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "IST")
	private String reportDateTimeStr;

	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "IST")
	private String resolvedDateTimeStr;

	@NotNull
	@Transient
	private String issueTypeStr;

//	
//	@NotNull
//	private String email;
//	
	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public LocalDateTime getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(LocalDateTime reportDateTime) {
		this.reportDateTime = reportDateTime;
		setReportDateTimeStr(this.reportDateTime == null ? null : this.reportDateTime.toString());
	}

	@JsonIgnore
	public LocalDateTime getResolvedDateTime() {
		return resolvedDateTime;
	}

	public void setResolvedDateTime(LocalDateTime resolvedDateTime) {
		this.resolvedDateTime = resolvedDateTime;
		setResolvedDateTimeStr(this.resolvedDateTime == null ? null : this.resolvedDateTime.toString());
	}

	public String getReportDateTimeStr() {
		return reportDateTimeStr = reportDateTime == null ? null : reportDateTime.toString();
	}

	public void setReportDateTimeStr(String reportDateTimeStr) {
		this.reportDateTimeStr = reportDateTimeStr;
	}

	public String getResolvedDateTimeStr() {
		return resolvedDateTimeStr = resolvedDateTime == null ? null : resolvedDateTime.toString();
	}

	public void setResolvedDateTimeStr(String resolvedDateTimeStr) {
		this.resolvedDateTimeStr = resolvedDateTimeStr;
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

	public String getSubjectLine() {
		return subjectLine;
	}

	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
	// uncomment and make fresh Constructors accordingly
	@PrePersist
	private void prePersistMethod() {

		setIsClosed(false);
		if (this.issueType == null)
			setIssueType(IssueType.OTHER);
		if (this.reportDateTime == null)
			setReportDateTime(LocalDateTime.now());
	}

	public Incident() {

	}

	public Incident(@NotNull String issueTypeStr, @NotNull String transporterName, @NotNull String remarks,
			String subjectLine, Boolean isClosed, @NotNull String transporterID, String username,
			String supportRemark) {
		super();
		this.issueTypeStr = issueTypeStr;
		this.transporterName = transporterName;
		this.remarks = remarks;
		this.subjectLine = subjectLine;
		this.isClosed = isClosed;
		this.transporterID = transporterID;
		this.username = username;
		this.supportRemark = supportRemark;
	}

	public Incident(@NotNull String issueTypeStr, @NotNull String transporterName, @NotNull String remarks,
			Boolean isClosed, @NotNull String transporterID, String username, String supportRemark) {
		super();
		this.issueTypeStr = issueTypeStr;
		this.supportRemark = supportRemark;
		this.transporterName = transporterName;
		this.remarks = remarks;
		this.isClosed = isClosed;
		this.transporterID = transporterID;
		this.username = username;
	}

	public Incident(@NotNull String issueTypeStr, @NotNull String transporterName, @NotNull String remarks,
			@NotNull String transporterID) {
		super();
		this.issueTypeStr = issueTypeStr;
		this.transporterName = transporterName;
		this.remarks = remarks;
		this.transporterID = transporterID;
	}

}
