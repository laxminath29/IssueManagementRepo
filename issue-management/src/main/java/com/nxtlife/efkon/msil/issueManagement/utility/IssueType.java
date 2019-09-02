package com.nxtlife.efkon.msil.issueManagement.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum IssueType {

	NRD ("NRD") , ID_RELATED("Id Related"), OTHER("Other");
	
	private String value;
	
	public static  Map<String,IssueType> mapping = new HashMap<>();
	public static  Map<IssueType,String> revMapping = new HashMap<>();
	
	static {
		mapping.put(IssueType.NRD.value,IssueType.NRD);
		mapping.put(IssueType.ID_RELATED.value,IssueType.ID_RELATED);
		mapping.put(IssueType.OTHER.value,IssueType.OTHER);
		revMapping.put(IssueType.NRD,IssueType.NRD.value );
		revMapping.put(IssueType.ID_RELATED,IssueType.ID_RELATED.value );
		revMapping.put(IssueType.OTHER,IssueType.OTHER.value );
		
		
	}
	
	private IssueType(String value) {
		this.value= value;
	}
	
	public static List<String> getIssueTypes(){
		List<String> issueTypes = new ArrayList<>();
		for(IssueType itype : IssueType.values()) {
			issueTypes.add(itype.value);
		}
		return issueTypes;
	}
}
