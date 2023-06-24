package com.qalegendbilling.utilities;

import java.util.Random;

import com.github.javafaker.Faker;

public class RandomUtility {
	static Faker faker;
	static String fName;
	static String lName;
	
	
	public static String getfName(){
	    faker= new Faker();
	    fName = faker.name().lastName();
	    return fName;
	}
	public static String getlName(){
	    faker= new Faker();
	    lName = faker.name().lastName();
	    return lName;
	}
	public static String getRandomEmail() {
	    String alphabet = "abcdefghijklmnopqrstuvwxyz";
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    int length = 5;
	    for (int i = 0; i < length; i++) {
	        int index = random.nextInt(alphabet.length());
	        char randomChar = alphabet.charAt(index);
	        sb.append(randomChar);
	    }
	    String randomStringEmail = sb.toString() + "@gmail.com";
	    return randomStringEmail;
	}
	
	public static String getUsername() {
		faker=new Faker();
		String uName=faker.name().username();
		return uName; 
	}
	
	public static String getPassword() {
		faker=new Faker();
		String password=faker.internet().password();
		return password;
	}
	public String prefix(){
        String pre=faker.options().option("Mr","Miss","Mrs");
        return pre;
    }
	public String jobTitle(){
        String role=faker.job().position();
        return role;
    }
	 public String decimalValue(){
	        double num=faker.number().randomDouble(2,10,75);
	        String value= String.valueOf(num);
	        if(value.length()==4)
	        {
	         value.concat("0");
	        }
	        return value;
	    }
}
