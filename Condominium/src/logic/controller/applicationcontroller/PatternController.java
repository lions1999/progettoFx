package logic.controller.applicationcontroller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternController {
	
	public static final Pattern VALID_NAME_REGEX = 
		    Pattern.compile("^[A-Z][a-z\\s]{2,15}$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_PASSWORD_REGEX = 
		    Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,15}$", Pattern.CASE_INSENSITIVE);

	public boolean isName(String name) {
		Matcher matcher = VALID_NAME_REGEX.matcher(name);
	    return !matcher.find();
	}
	    
	public  boolean isEmail(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
	    return matcher.find();
	}
	
	public  boolean isPassword(String password) {
		Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
	    return matcher.find();
	}	
}
