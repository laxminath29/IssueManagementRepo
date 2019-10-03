package com.nxtlife.efkon.msil.issueManagement.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	@NotNull
	@Column(unique=true)
	private String username;
	
	@NotNull
	private String password;
	
	@OneToMany(cascade = CascadeType.MERGE , fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles ;
	
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public User() {
		super();
	}
	public User(User user) {
		this.id=user.getId();
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.roles=user.getRoles();
	}
	
	

	
}
