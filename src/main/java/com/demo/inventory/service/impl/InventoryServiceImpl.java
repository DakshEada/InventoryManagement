/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 9:06:33 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import com.demo.inventory.dao.InventoryDAO;
import com.demo.inventory.dao.impl.InventoryDAOImpl;
import com.demo.inventory.model.Item;
import com.demo.inventory.service.InventoryService;

public class InventoryServiceImpl implements InventoryService {
	private final static Logger logger = Logger.getLogger(InventoryServiceImpl.class.getName());	
	private final InventoryDAO inventoryDAO = new InventoryDAOImpl();
	
	@Override
	public void createItem(Item item) throws IOException {			
		//logger.info("Entered into createItem method");
		inventoryDAO.createItem(item);				
		//logger.info("Exited from createItem method");
	}

	
	@Override
	public void deleteItem(Item item) throws IOException {
		inventoryDAO.deleteItem(item);				
	}

	
	@Override
	public void updateBuy(Item item) throws IOException {
		inventoryDAO.updateBuy(item);		
	}

	
	@Override
	public void updateSell(Item item) throws IOException {
		inventoryDAO.updateSell(item);
	}

	
	@Override
	public Map<String, Item> getInventory() throws IOException {
		//logger.info("Inventory map is : "+inventoryDAO.getInventory());
		return inventoryDAO.getInventory();		
	}
}
