/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 11:36:41 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommandConstants {
	public static final String CREATE = "create";
	public static final String DELETE = "delete";
	public static final String UPDATE_BUY = "updateBuy";
	public static final String UPDATE_SELL = "updateSell";
	public static final String REPORT = "report";
	public static final String[] headers = {"Item Name", "Bought At", "Sold At", "Total Quantity", "Value"};
	public static final String TOTAL_VALUE = "Total value";
	public static final String PROFIT_SINCE_PREVIOUS_YEAR = "Profit since previous year";
	public static final String INVENTORY_SERIALIZED_FILE = "InventorySerializationFile";
	public static final Map<String, String> commandEntityMap = new LinkedHashMap<>();
	
	static {
		commandEntityMap.put("create", "Item-4");
		commandEntityMap.put("delete", "Item-1");
		commandEntityMap.put("updateBuy", "Item-2");
		commandEntityMap.put("updateSell", "Item-2");
		commandEntityMap.put("report", "Inventory-0");
	}
	
	
	private CommandConstants() {
		
	}
	
	
	public static Map<String, String> getCommandEntityMap() {
		return commandEntityMap;
	}
			
}
