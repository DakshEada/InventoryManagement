/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 8:43:48 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.command;

import java.util.logging.Logger;

import com.demo.inventory.client.CommandClient;
import com.demo.inventory.constants.CommandConstants;
import com.demo.inventory.model.Item;
import com.demo.inventory.processor.ItemCommandProcessor;
import com.demo.inventory.service.InventoryService;

public class ItemCommand implements Command<Item> {
	private final static Logger logger = Logger.getLogger(ItemCommand.class.getName());
	ItemCommandProcessor commandProcessor;
	InventoryService itemService;
	Item item;
	String command;
	
	public ItemCommand(String command, Item item, InventoryService itemService) {		
		this.command = command;
		this.item = item;
		this.itemService = itemService;
	}		
	
	
	public void execute() throws Exception {
		// logger.info("Entered into execute method");
		
		switch(command) {
			case CommandConstants.CREATE : itemService.createItem(item);break;
			case CommandConstants.DELETE : itemService.deleteItem(item);break;
			case CommandConstants.UPDATE_BUY : itemService.updateBuy(item);break;
			case CommandConstants.UPDATE_SELL : itemService.updateSell(item);break;			
		}
		
		// logger.info("Exited from execute method");
	}	
}
