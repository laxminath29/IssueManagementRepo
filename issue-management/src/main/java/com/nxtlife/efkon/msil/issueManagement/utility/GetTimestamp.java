package com.nxtlife.efkon.msil.issueManagement.utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.util.StringUtils;





public class GetTimestamp {

	public static Timestamp getTimestamp(String timestampInString) {
        if (!StringUtils.isEmpty(timestampInString) && timestampInString != null) {
            Date date = new Date(Long.parseLong(timestampInString));
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            String formatted = format.format(date);
            Timestamp timeStamp = Timestamp.valueOf(formatted);
            return timeStamp;
        } else {
            return null;
        }
    }
	
}
