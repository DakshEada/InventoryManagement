/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 10:20:25 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.enumerator;

public enum CommandEnum {
	CREATE("create", 4), 
	DELETE("delete", 1),
	UPDATE_BUY("updateBuy", 2), 
	UPDATE_SELL("updateSell", 2),
	REPORT("report", 0);
	
	public String command;
	public int noOfCommandArguments;
	
	
	CommandEnum(String command, int noOfCommandArguments) {
		this.command = command;
		this.noOfCommandArguments = noOfCommandArguments;
	}
	
	
	public String getCommand() {
		return command;
	}
	
	
	public int getNoOfCommandArguments() {
		return noOfCommandArguments;
	}			
}
