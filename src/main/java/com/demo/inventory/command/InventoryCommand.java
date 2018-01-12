/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 11, 2018 11:34:11 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.command;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.demo.inventory.constants.CommandConstants;
import com.demo.inventory.model.Inventory;
import com.demo.inventory.model.Item;
import com.demo.inventory.processor.ItemCommandProcessor;
import com.demo.inventory.service.InventoryService;

public class InventoryCommand implements Command<Inventory> {
	private final static Logger logger = Logger.getLogger(InventoryCommand.class.getName());
	ItemCommandProcessor commandProcessor;
	InventoryService inventoryService;
	Inventory inventory;
	String command;
	private static String newLine = System.lineSeparator();
	private StringBuffer strBuffer = new StringBuffer();
	private Double totalValue = 0.0; 
	
	private int rowHeaderLength = 0;
	
	public InventoryCommand(String command, Inventory inventory, InventoryService inventoryService) {		
		this.command = command;
		this.inventory = inventory;
		this.inventoryService = inventoryService;
	}		
	
	
	public void execute() throws Exception {
		if(command.equals(CommandConstants.REPORT)) {
			//Map<String, Item> inventoryMap = inventoryService.getInventory();
			Map<String, Item> inventoryMap = new TreeMap<String, Item>( 
					new Comparator<String>() { 
					@Override 
					public int compare(String item1, String item2) { 
					return item1.compareTo(item2); 
					} 
					}); 

					inventoryMap.putAll(inventoryService.getInventory());
			
			if(inventoryMap != null) {
				prepareHeaderForReport(CommandConstants.headers, strBuffer);
				prepareFieldSeparator(CommandConstants.headers, strBuffer);
				
				if(inventoryMap.size() != 0) {										
					iterateAndPrepareDataForReport(inventoryMap, strBuffer);
					prepareRowSeparator(CommandConstants.headers, strBuffer);
				}
				
				displayTotalValue();
				displayProfitSinceLastYear();								
			}
		}
	}
	
	
	public void iterateAndPrepareDataForReport(Map<String, Item> inventoryMap, StringBuffer strBuffer) {
		Iterator<Item> itemIterator = inventoryMap.values().iterator();
		Double valueForEachItem = 0.0;
		
		while(itemIterator.hasNext()) {
			Item item = itemIterator.next();
			valueForEachItem = item.getCostPrice() * item.getQuantity();			
			prepareDataForReport(item, strBuffer, valueForEachItem);
			calculateTotalValue(valueForEachItem);
			valueForEachItem = 0.0;
		}
	}
	
	
	public void prepareHeaderForReport(String[] headers, StringBuffer strBuffer) {
		// logger.info("Entered into prepareHeaderForReport method");
		
		if(headers != null) {
			for(String header : headers) {
				strBuffer.append(header).append("\t");
			}
			
			rowHeaderLength = strBuffer.toString().length();
			strBuffer.append(newLine);
		}
		
		//System.out.println(strBuffer.toString());
		// logger.info("Exited from prepareHeaderForReport method");
	}
	
	
	public void prepareFieldSeparator(String[] headers, StringBuffer strBuffer) {
		if(headers != null) {
			for(String header : headers) {
				strBuffer.append(printString(header, "-")).append("\t");
			}
			
			strBuffer.append(newLine);
		}
		
		//System.out.println(strBuffer.toString());
	}			
	
	
	public void prepareDataForReport(Item item, StringBuffer strBuffer, Double valueForEachItem) {
		// logger.info("Entered into prepareDataForReport method");
		
		if(item != null) {
			strBuffer.append(item.getItemName()).append("\t")
					 .append(item.getCostPrice()).append("\t")
					 .append(item.getSellingPrice()).append("\t")
					 .append(item.getQuantity()).append("\t")
					 .append(valueForEachItem).append(newLine);						
		}
		
		//System.out.println(strBuffer.toString());
		// logger.info("Exited from prepareDataForReport method");
	}
	
	
	public void prepareRowSeparator(String[] headers, StringBuffer strBuffer) {
		if(headers != null) {
			for(int i=0;i<rowHeaderLength;i++) {
				strBuffer.append("-");
			}
															
			strBuffer.append(newLine);
		}
		
		System.out.println(strBuffer.toString());
	}
	
	
	public StringBuffer printString(String string, String strToBePrinted) {
		StringBuffer strBuffer = new StringBuffer();
		
		for(int i=0;i<string.length();i++) {
			strBuffer.append(strToBePrinted);
		}
		
		return strBuffer;
	}
	
	
	public void displayTotalValue() {
		System.out.println(CommandConstants.TOTAL_VALUE+fillSpace().append(getTotalValueOfItems()));
	}			
	
	
	public void displayProfitSinceLastYear() {
		System.out.println(CommandConstants.PROFIT_SINCE_PREVIOUS_YEAR+fillSpace().append(calculateProfitSinceLastYear()));
	}
	
	
	public StringBuffer fillSpace() {
		StringBuffer strBuffer = new StringBuffer();
		
		for(int i=0;i<CommandConstants.headers.length;i++) {
			strBuffer.append(printString(CommandConstants.headers[i], " "));
		}
		
		return strBuffer;
	}
	
	
	public Double calculateTotalValue(Double valueForEachItem) {		
		totalValue += valueForEachItem;		
		return totalValue;
	}
	
	
	public Double getTotalValueOfItems() {
		return totalValue;
	}
	
	
	public Double calculateProfitSinceLastYear() {
		Double profit = 0.0;
		return profit;
	}
}
