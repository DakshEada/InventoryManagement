/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 11, 2018 10:05:51 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.processor;

import java.util.List;

public interface CommandProcessor<T> {
	public void mapCommandArgumentList(String command, List<String> commandArgumentList) throws Exception;
	public void findEntityAndServiceBasedOnNoOfCommandArgs(String command, List<String> commandArgumentList) throws Exception;
	public T createAndPopulateEntity(String command, Class<T> entity, List<String> commandArgumentList) throws InstantiationException, IllegalAccessException;
}
