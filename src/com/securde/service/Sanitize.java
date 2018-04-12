package com.securde.service;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class Sanitize 
{
	
	/*
	 * Check input data through white listing
	 */
	public static boolean isCleanInput(String input)
	{
		
		//check that string only contains valid characters
		if(Pattern.matches("[a-zA-Z0-9\\s\\-]{1,50}", input))
			if(StringUtils.countMatches(input.replace(" ", ""), "--") == 0)
				return true;
		
		return false;
	}
}
