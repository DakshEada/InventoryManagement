/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 11, 2018 10:47:05 AM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.client;

import java.util.Scanner;
import java.util.logging.Logger;

import com.demo.inventory.controller.CommandController;

public class CommandClient {
	private final static Logger logger = Logger.getLogger(CommandClient.class.getName());		
	Scanner inputSourceScanner = null;
	private final CommandController controller = new CommandController();
	
	public String processInputCommand() {
		String inputCommand = "";
		System.out.print("Please enter a command : ");
														
		if(inputSourceScanner == null) {
			inputSourceScanner = new Scanner(System.in);
		}
		
		if(!inputSourceScanner.nextLine().isEmpty()) {													
			inputCommand = inputSourceScanner.nextLine();								
		}
		else {
			processInputCommand();
		}
						
		return inputCommand;
	}			
	
	
	public void promptForCommand() {
		try
		{
			String command = processInputCommand();		
			controller.extractCommand(command);
			System.out.println("Do you want to continue (Y/N) > ");
			
			if("Y".equalsIgnoreCase(inputSourceScanner.nextLine())) {
				promptForCommand();
			}
		}
		catch(Exception e) {
			logger.info("Exception caught while prompting for command "+e);
		}
		finally {
			if(inputSourceScanner != null) {
				inputSourceScanner.close();
				inputSourceScanner = null;
			}						
		}
	}
	
	

	/**
	 * Author : Daksh Jan 11, 2018 10:47:06 AM
	 *
	 * Description : 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {        		
		
		CommandClient mobileClient = new CommandClient();
		mobileClient.promptForCommand();		
	}

}
