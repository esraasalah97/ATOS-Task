package validation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
public class ValidateField {
	
	public static boolean validateUrl(WebDriver driver,String url) {
		return driver.getCurrentUrl().equals(url)? true:false;
	}
    public static boolean validateFirstLetterUpperCase(String str) {
    	return Character.isUpperCase(str.charAt(0))?true:false;
    }
    public static boolean validatePassword(String password) {
    	return checkString(password);
    }
    private static boolean checkString(String string) {
        char ch;
        boolean containsUpperCase = false;
        boolean containsLowerCase = false;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(string);
        boolean containsSpecialCharacter= matcher.find();
        for(int i=0;i < string.length();i++) {
            ch = string.charAt(i);
            if (Character.isUpperCase(ch)) {
                containsUpperCase= true;
            } else if (Character.isLowerCase(ch)) {
                containsLowerCase = true;
            }
            if(containsSpecialCharacter && containsUpperCase&& containsLowerCase)
                return true;
        }
        return false;
    }
}
