/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 8:28:34 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.demo.inventory.constants.CommandConstants;
import com.demo.inventory.model.Inventory;
import com.demo.inventory.model.Item;
import com.demo.inventory.processor.CommandProcessor;
import com.demo.inventory.processor.InventoryReportProcessor;
import com.demo.inventory.processor.ItemCommandProcessor;

public class CommandController {
	private final static Logger logger = Logger.getLogger(CommandController.class.getName()); 	 		
	private CommandProcessor<?> commandProcessor; 		
	private final CommandProcessor<Item> itemCommandProcessor = new ItemCommandProcessor();
	private final CommandProcessor<Inventory> inventoryReportProcessor = new InventoryReportProcessor();
	
	public void extractCommand(String inputCommandString) {
		// logger.info("Entered into extractCommand method");				
		
		try {
			if(inputCommandString != null && !inputCommandString.isEmpty()) {
				//logger.info("Input command is : "+inputCommandString+" :: token length is : "+inputCommandString.split(" ").length);								
				validateCommandAndDelegateToProcessor(inputCommandString, commandProcessor);
			}
		}
		catch(Exception e) {
			logger.info("Unable to extract command from input string ");
			e.printStackTrace();
		}
		
		// logger.info("Exited from extractCommand method");
	}			
	
	
	public void validateCommandAndDelegateToProcessor(String inputCommandString, CommandProcessor<?> commandProcessor) throws Exception {
		if(inputCommandString.split(" ").length >= 1) {
			String command = inputCommandString.split(" ")[0];
			List<String> commandArgumentList = new ArrayList<>();
			
			if(CommandConstants.getCommandEntityMap().containsKey(command)) {
				int actualCommandArgumentLength = Integer.parseInt(CommandConstants.getCommandEntityMap().get(command).split("-")[1]);				
				
				if(inputCommandString.split(" ").length > 1 && !inputCommandString.split(" ")[0].equals(CommandConstants.REPORT)) {									
					if(actualCommandArgumentLength == (inputCommandString.split(" ").length - 1)) {
						if(inputCommandString.split(" ").length != 0) {
							for(int i=1;i<inputCommandString.split(" ").length;i++) {
								commandArgumentList.add(inputCommandString.split(" ")[i]);
							}
						}
					}
				}
				else if(inputCommandString.split(" ").length == 1 && !inputCommandString.split(" ")[0].equals(CommandConstants.REPORT)) {									
					throw new Exception("Please provide arguments for the command "+command+" entered");
				}
				
				//logger.info("commandArgumentList size is : "+commandArgumentList.size());			
				commandProcessor = this.getProcessorBasedOnCommand(command);
				commandProcessor.mapCommandArgumentList(command, commandArgumentList);
			}
			else {
				throw new Exception("Unknown command. Please enter a valid command.");
			}
		}
		else {
			throw new Exception("Please enter a valid command.");
		}
	}
	
	
	public Class<?> getEnityClassBasedOnCommand(String command) {
		Class<?> clazz = null;
		
		if(CommandConstants.getCommandEntityMap().get(command).split("-")[0].equals("Item")) {
			clazz = Item.class;
		}
		else if(CommandConstants.getCommandEntityMap().get(command).split("-")[0].equals("Inventory")) {
			clazz = Inventory.class;
		}
		
		return clazz;
	}
	
	
	public CommandProcessor<?> getProcessorBasedOnCommand(String command) {				
		if(CommandConstants.getCommandEntityMap().get(command).split("-")[0].equals("Item")) {			
			commandProcessor = itemCommandProcessor;
			//logger.info("Command processor is now set to itemCommandProcessor");
		}
		else if(CommandConstants.getCommandEntityMap().get(command).split("-")[0].equals("Inventory")) {
			commandProcessor = inventoryReportProcessor;
			//logger.info("Command processor is now set to InventoryReportProcessor");
		}
		
		return commandProcessor;
	}
}
