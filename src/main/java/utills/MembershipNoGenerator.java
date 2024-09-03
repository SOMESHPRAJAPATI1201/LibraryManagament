package utills;

import java.time.Year;

public class MembershipNoGenerator {
	
	public static String getMembershipNo(String role) {
	    Year thisYear = Year.now();
        int randomIntInRange = (int)(Math.random() * 9000);
	    String str = "";
	    if (role.equalsIgnoreCase("admin")) {
	    	str = "VLMS"+String.valueOf(thisYear)+"ADMIN"+(int)randomIntInRange*1000;
		}else if (role.equalsIgnoreCase("student")) {
			str = "VLMS"+String.valueOf(thisYear)+"STUDENT"+(int)randomIntInRange*1000;
		}
		return str;
	}
}
