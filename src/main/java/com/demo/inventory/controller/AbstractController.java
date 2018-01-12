/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 11, 2018 10:42:44 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.controller;

import com.demo.inventory.processor.CommandProcessor;

public class AbstractController<T> {	
	CommandProcessor<?> processor;
	
	public void setCommandProcessor(CommandProcessor<?> processor) {		
		this.processor = processor;
	}
	
	public CommandProcessor<?> getCommandProcessor() {
		return processor;
	}
}
