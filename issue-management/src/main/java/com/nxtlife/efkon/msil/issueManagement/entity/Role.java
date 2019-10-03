package com.nxtlife.efkon.msil.issueManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Integer id ;
	
	@Column(unique=true)
	private String role;

	public Integer getId() {
		return id;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public Role() {
	
	}
	
	

}
