/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 8:35:40 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.command;

public interface Command<T> {	
	public void execute() throws Exception;		
}
