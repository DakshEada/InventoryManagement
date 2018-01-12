/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 11:20:57 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.processor;

import java.util.List;

public abstract class AbstractCommandProcessor<E, S> {
	Class<E> entity;
	Class<S> service;
	
	protected AbstractCommandProcessor(Class<E> clazz, Class<S> service) {
		this.entity = clazz;
		this.service = service;
	}
	
	public abstract void processCommand(String command, List<String> commandArgumentList, Class<E> entity, Class<S> service) throws Exception;
}
