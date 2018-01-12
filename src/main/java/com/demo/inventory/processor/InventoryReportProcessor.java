/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 11, 2018 11:28:19 PM
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
import com.demo.inventory.command.InventoryCommand;
import com.demo.inventory.enumerator.CommandEnum;
import com.demo.inventory.model.Inventory;
import com.demo.inventory.service.InventoryService;
import com.demo.inventory.service.impl.InventoryServiceImpl;

public class InventoryReportProcessor extends AbstractCommandProcessor<Inventory, InventoryService> implements CommandProcessor<Inventory> {
	private final static Logger logger = Logger.getLogger(InventoryReportProcessor.class.getName());
	private InventoryService inventoryService = null;
	
	public InventoryReportProcessor() {
		super(Inventory.class, InventoryService.class);
	}
	
	public void mapCommandArgumentList(String command, List<String> commandArgumentList) throws Exception {
		// logger.info("Entered into mapCommandArgumentList method");
		findEntityAndServiceBasedOnNoOfCommandArgs(command, commandArgumentList);
		// logger.info("Exited from mapCommandArgumentList method");
	}
	
	
	public void findEntityAndServiceBasedOnNoOfCommandArgs(String command, List<String> commandArgumentList) throws Exception {
		if(CommandEnum.REPORT.getCommand().equals(command) && 
				CommandEnum.REPORT.getNoOfCommandArguments() == commandArgumentList.size()) {
			processCommand(command, commandArgumentList, Inventory.class, InventoryService.class);
		}		
	}
	
	
	@Override
	public void processCommand(String command, List<String> commandArgumentList, Class<Inventory> inventoryClass, Class<InventoryService> inventoryServiceClass) throws Exception {
		Inventory inventory = createAndPopulateEntity(command, inventoryClass, commandArgumentList);
		//logger.info("Item is : "+inventory.toString());		
		inventoryService = getServiceInstance(inventoryServiceClass);
		Command<Inventory> itemCommand = new InventoryCommand(command, inventory, inventoryService);
		itemCommand.execute();
	}		
	
	
	public Inventory createAndPopulateEntity(String command, Class<Inventory> entity, List<String> commandArgumentList) throws InstantiationException, IllegalAccessException {
		//logger.info("Entered into createAndPopulateEntity method");
		Inventory inventory = null; //entity.newInstance();
				
		if(inventory instanceof Inventory) {				
			logger.info("Inventory is : "+inventory.toString());																		
		}
		
		//logger.info("Exited from createAndPopulateEntity method");
		return inventory;
	}


	public InventoryService getServiceInstance(Class<InventoryService> entityService) throws InstantiationException, IllegalAccessException {		
		//logger.info("Entity service is interface : "+entityService.isInterface());
		
		if(entityService.isInterface()) {
			if(inventoryService == null) {
				inventoryService = new InventoryServiceImpl();
			}
		}
		else {
			inventoryService = entityService.newInstance();
		}
		
		return inventoryService;
	}
}
