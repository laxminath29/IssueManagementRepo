package com.nxtlife.efkon.msil.issueManagement.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum IssueType {

	HARDWARE ("Hardware") , SOFTWARE("Software"), OTHER("Other");
	
	private String value;
	
	public static  Map<String,IssueType> mapping = new HashMap<String,IssueType>();
	public static  Map<IssueType,String> revMapping = new HashMap<IssueType,String>();
	
	static {
		mapping.put(IssueType.HARDWARE.value,IssueType.HARDWARE);
		mapping.put(IssueType.SOFTWARE.value,IssueType.SOFTWARE);
		mapping.put(IssueType.OTHER.value,IssueType.OTHER);
		revMapping.put(IssueType.HARDWARE,IssueType.HARDWARE.value );
		revMapping.put(IssueType.SOFTWARE,IssueType.SOFTWARE.value );
		revMapping.put(IssueType.OTHER,IssueType.OTHER.value );
		
		
	}
	
	private IssueType(String value) {
		this.value= value;
	}
	
	public static List<String> getIssueTypes(){
		List<String> issueTypes = new ArrayList<String>();
		for(IssueType itype : IssueType.values()) {
			issueTypes.add(itype.value);
		}
		return issueTypes;
	}
}
