/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 8:37:49 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Inventory implements Serializable {	
	private static final long serialVersionUID = -2736714225641710022L;	
	private final static Logger logger = Logger.getLogger(Inventory.class.getName());	
	private final Set<Item> inventorySet = new HashSet<>();
	private final Map<String, Item> inventoryMap = new ConcurrentHashMap<>();
	private Item item;
	private Double value;
			
	
	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public Set<Item> getInventorySet() {
		return inventorySet;
	}
	
	/*
	public void setInventorySet(Set<Item> inventorySet) {
		this.inventorySet = inventorySet;
	}*/


	public Map<String, Item> getInventoryMap() {
		return inventoryMap;
	}
	
	/*
	public void setInventoryMap(Map<String, Item> inventoryMap) {
		this.inventoryMap = inventoryMap;
	}*/


	public Item getItem(String itemName) {
		Item item = null;
		
		if(inventoryMap != null && inventoryMap.size() != 0) {
			item = inventoryMap.get(itemName);
		}
		
		return item;
	}
	
	
	public void addItem(Item item) {
		logger.info("Entered into addItem method");
		this.item = item;
		//inventorySet.add(item);
		inventoryMap.put(item.getItemName(), item);
		logger.info("Inventory map size is : "+inventoryMap.size());
		logger.info("Exited from addItem method");
	}
	
	
	public void deleteItem(Item item) {				
		if(inventoryMap != null && inventoryMap.size() != 0) {
			inventoryMap.remove(item.getItemName());
		}
	}			
	
	
	public Map<String, Item> getInventory() {
		return inventoryMap; 
	}
	
	
	public Double getValue() {
		return value;
	}
	
	
	public void setValue(Double value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "Inventory [inventorySet=" + inventorySet + ", inventoryMap=" + inventoryMap + ", value=" + value + "]";
	}
}
