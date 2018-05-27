package com.luyh.application.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {

	public static void main(String[] args) {
		SecureRandom random = null;
		String clientId = "android-app";
		
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
				 e.printStackTrace();
		}
		//String pass = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+-*/.;";
        PasswordEncoder encoder = new BCryptPasswordEncoder(5, random);
        
        //for (int i = 0; i < 3; i++)
        	System.out.println("Hash {} --> " + encoder.encode(clientId));
	}
	
}