/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 10:42:55 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.processor;

import java.util.List;
import java.util.logging.Logger;

import com.demo.inventory.command.Command;
import com.demo.inventory.command.ItemCommand;
import com.demo.inventory.constants.CommandConstants;
import com.demo.inventory.enumerator.CommandEnum;
import com.demo.inventory.model.Item;
import com.demo.inventory.service.InventoryService;
import com.demo.inventory.service.impl.InventoryServiceImpl;

public class ItemCommandProcessor extends AbstractCommandProcessor<Item, InventoryService> implements CommandProcessor<Item> {
	private final static Logger logger = Logger.getLogger(ItemCommandProcessor.class.getName());
	private InventoryService itemService = null;
	
	public ItemCommandProcessor() {
		super(Item.class, InventoryService.class);
	}
	
	public void mapCommandArgumentList(String command, List<String> commandArgumentList) throws Exception {
		//logger.info("Entered into mapCommandArgumentList method");
		findEntityAndServiceBasedOnNoOfCommandArgs(command, commandArgumentList);
		//logger.info("Exited from mapCommandArgumentList method");
	}
	
	
	public void findEntityAndServiceBasedOnNoOfCommandArgs(String command, List<String> commandArgumentList) throws Exception {
		if(CommandEnum.CREATE.getCommand().equals(command) && 
				CommandEnum.CREATE.getNoOfCommandArguments() == commandArgumentList.size()) {
			processCommand(command, commandArgumentList, Item.class, InventoryService.class);
		}
		else if(CommandEnum.DELETE.getCommand().equals(command) && 
				CommandEnum.DELETE.getNoOfCommandArguments() == commandArgumentList.size()) {
			processCommand(command, commandArgumentList, Item.class, InventoryService.class);
		}
		else if(CommandEnum.UPDATE_BUY.getCommand().equals(command) && 
				CommandEnum.UPDATE_BUY.getNoOfCommandArguments() == commandArgumentList.size()) {
			processCommand(command, commandArgumentList, Item.class, InventoryService.class);
		}
		else if(CommandEnum.UPDATE_SELL.getCommand().equals(command) && 
				CommandEnum.UPDATE_SELL.getNoOfCommandArguments() == commandArgumentList.size()) {
			processCommand(command, commandArgumentList, Item.class, InventoryService.class);
		}
		else if(CommandEnum.REPORT.getCommand().equals(command) && 
				CommandEnum.REPORT.getNoOfCommandArguments() == commandArgumentList.size()) {
			processCommand(command, commandArgumentList, Item.class, InventoryService.class);
		}
	}
	
	
	@Override
	public void processCommand(String command, List<String> commandArgumentList, Class<Item> itemClass, Class<InventoryService> itemServiceClass) throws Exception {
		Item item = createAndPopulateEntity(command, itemClass, commandArgumentList);
		logger.info("Item is : "+item.toString());		
		itemService = getServiceInstance(itemServiceClass);
		Command<Item> itemCommand = new ItemCommand(command, item, itemService);
		itemCommand.execute();
	}		
	
	
	public Item createAndPopulateEntity(String command, Class<Item> entity, List<String> commandArgumentList) throws InstantiationException, IllegalAccessException {
		//logger.info("Entered into createAndPopulateEntity method");
		Item item = entity.newInstance();
				
		if(item instanceof Item) {				
			logger.info("Item is : "+item.toString());
			
			switch(command) {
				case CommandConstants.CREATE :	item.setItemName(commandArgumentList.get(0));
												item.setCostPrice(Double.parseDouble(commandArgumentList.get(1)));
												item.setSellingPrice(Double.parseDouble(commandArgumentList.get(2)));
												item.setQuantity(Double.parseDouble(commandArgumentList.get(3)));
												break;
				case CommandConstants.DELETE :	item.setItemName(commandArgumentList.get(0));
												break;
				case CommandConstants.UPDATE_BUY :	item.setItemName(commandArgumentList.get(0));
													item.setQuantity(Double.parseDouble(commandArgumentList.get(1)));
													break;
				case CommandConstants.UPDATE_SELL :	item.setItemName(commandArgumentList.get(0));
													item.setQuantity(Double.parseDouble(commandArgumentList.get(1)));
													break;														
			}													
		}
		
		//logger.info("Exited from createAndPopulateEntity method");
		return item;
	}


	public InventoryService getServiceInstance(Class<InventoryService> entityService) throws InstantiationException, IllegalAccessException {		
		//logger.info("Entity service is interface : "+entityService.isInterface());
		
		if(entityService.isInterface()) {
			if(itemService == null) {
				itemService = new InventoryServiceImpl();
			}
		}
		else {
			itemService = entityService.newInstance();
		}
		
		return itemService;
	}
}
