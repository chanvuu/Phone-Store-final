package service;

import java.security.SecureRandom;

public class Captcha {
	  public static String generateRandomString() {
	        int length = 7;
	        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	        StringBuilder randomString = new StringBuilder();

	        SecureRandom secureRandom = new SecureRandom();
	        for (int i = 0; i < length; i++) {
	            int randomIndex = secureRandom.nextInt(characters.length());
	            randomString.append(characters.charAt(randomIndex));
	        }

	        return randomString.toString();
	    }
	  
	  public static boolean isValid(String input) {
	        // Perform your validation logic here
	        // For example, check if the string has a length of 7

	        return input != null && input.length() == 7;
	    }
}
