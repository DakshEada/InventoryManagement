/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 9:02:17 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.service;

import java.io.IOException;
import java.util.Map;

import com.demo.inventory.model.Item;

public interface InventoryService {
	public void createItem(Item item) throws IOException;
	public void deleteItem(Item item) throws IOException;
	public void updateBuy(Item item) throws IOException;
	public void updateSell(Item item) throws IOException;
	public Map<String, Item> getInventory() throws IOException;
}
