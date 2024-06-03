package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class Util {
    
    public String GeneratePass() {
	        String passChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder passBuild = new StringBuilder();
	        Random rnd = new Random();
	        while (passBuild.length() < 6) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * passChar.length());
	            passBuild.append(passChar.charAt(index));
	        }
	        String pass = passBuild.toString();
	        return pass;

	    }
}
