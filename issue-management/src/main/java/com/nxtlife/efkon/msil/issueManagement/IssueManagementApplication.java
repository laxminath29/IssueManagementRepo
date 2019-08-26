package com.nxtlife.efkon.msil.issueManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages="com.nxtlife.efkon.msil.issueManagement")
public class IssueManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueManagementApplication.class, "--debug");
	}

}
