/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 11, 2018 9:42:12 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.dao;

import java.io.IOException;
import java.util.Map;

import com.demo.inventory.model.Item;

public interface InventoryDAO {
	public void createItem(Item item) throws IOException;
	public void deleteItem(Item item) throws IOException;
	public void updateBuy(Item item) throws IOException;
	public void updateSell(Item item) throws IOException;
	public Map<String, Item> getInventory() throws IOException;
}
