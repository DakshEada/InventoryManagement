/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 11, 2018 9:42:54 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.logging.Logger;

import com.demo.inventory.constants.CommandConstants;
import com.demo.inventory.dao.InventoryDAO;
import com.demo.inventory.model.Inventory;
import com.demo.inventory.model.Item;

public class InventoryDAOImpl implements InventoryDAO {
	private final static Logger logger = Logger.getLogger(InventoryDAOImpl.class.getName());		
	
	
	@Override
	public void createItem(Item item) throws IOException {		
		// logger.info("Entered into createItem method");
		Inventory deserializedInventory = deserializeInventory();
		
		if(deserializedInventory != null) {
			if(deserializedInventory.getInventory() != null) {								
				deserializedInventory.getInventory().put(item.getItemName(), item);
				logger.info("Inventory size is now : "+deserializedInventory.getInventory().size());
				serializeInventory(deserializedInventory);
			}
		}
		else {
			Inventory inventory = new Inventory();
			inventory.addItem(item);
			serializeInventory(inventory);
		}
		
		// logger.info("Exited from createItem method");
	}

	
	@Override
	public void deleteItem(Item item) throws IOException {								
		Inventory deserializedInventory = deserializeInventory();
			
		if(deserializedInventory != null) {
			if(deserializedInventory.getInventory() != null && deserializedInventory.getInventory().size() != 0) {
				if(deserializedInventory.getInventory().containsKey(item.getItemName())) {
					deserializedInventory.getInventory().remove(item.getItemName());
					serializeInventory(deserializedInventory);
				}
			}
		}									
	}

	
	@Override
	public void updateBuy(Item item) throws IOException {		
		Inventory deserializedInventory = deserializeInventory();
		
		if(deserializedInventory != null) {
			if(deserializedInventory.getInventory() != null && deserializedInventory.getInventory().size() != 0) {
				if(deserializedInventory.getInventory().containsKey(item.getItemName())) {					
					Item oldItem = deserializedInventory.getInventory().get(item.getItemName());
					item.setSellingPrice(oldItem.getSellingPrice());
					item.setCostPrice(oldItem.getCostPrice());
					double quantity = oldItem.getQuantity() + item.getQuantity();
					item.setQuantity(quantity);					
					deserializedInventory.getInventory().put(item.getItemName(), item);
					serializeInventory(deserializedInventory);
				}
			}
		}		
	}

	
	@Override
	public void updateSell(Item item) throws IOException {		
		Inventory deserializedInventory = deserializeInventory();
		
		if(deserializedInventory != null) {
			if(deserializedInventory.getInventory() != null && deserializedInventory.getInventory().size() != 0) {
				if(deserializedInventory.getInventory().containsKey(item.getItemName())) {					
					Item oldItem = deserializedInventory.getInventory().get(item.getItemName());
					item.setSellingPrice(oldItem.getSellingPrice());
					item.setCostPrice(oldItem.getCostPrice());
					double quantity = oldItem.getQuantity() - item.getQuantity();
					item.setQuantity(quantity);
					deserializedInventory.getInventory().put(item.getItemName(), item);
					serializeInventory(deserializedInventory);
				}
			}
		}
	}

	
	@Override
	public Map<String, Item> getInventory() throws IOException {
		Map<String, Item> inventoryMap = null;		
		Inventory deserializedInventory = deserializeInventory();
		
		if(deserializedInventory != null) {
			if(deserializedInventory.getInventory() != null) {
				inventoryMap = deserializedInventory.getInventory();
			}
		}
		
		return inventoryMap;		
	}	
	
	
	public void serializeInventory(Inventory inventory) throws IOException {		
		boolean isSerializedInventoryFileExist = false;
		FileOutputStream fos = null;		
		ObjectOutputStream oos = null;
		
		try {
			isSerializedInventoryFileExist = checkFile(CommandConstants.INVENTORY_SERIALIZED_FILE);
			//logger.info("isSerializedInventoryFileExist : "+isSerializedInventoryFileExist);
			
			if(isSerializedInventoryFileExist == true) {
				Inventory deserializeInventory = deserializeInventory();
				deserializeInventory = inventory;
				fos = new FileOutputStream("InventorySerializationFile");
		        oos = new ObjectOutputStream(fos);
		        oos.writeObject(deserializeInventory);
			}
			else if(isSerializedInventoryFileExist == false) {
				fos = new FileOutputStream("InventorySerializationFile");
		        oos = new ObjectOutputStream(fos);
		        oos.writeObject(inventory);
			}
	    }
		catch(IOException ioe) {
	        ioe.printStackTrace();
	    }
		finally {
			oos.close();
	        fos.close();
		}
	}
	
	
	public Inventory deserializeInventory() throws IOException {		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Inventory inventory = new Inventory();
		
		try {			
            fis = new FileInputStream("InventorySerializationFile");
            ois = new ObjectInputStream(fis);
            inventory = (Inventory) ois.readObject();            
        }
		catch(IOException ioe) {
            ioe.printStackTrace();             
        }
		catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();            
        }
		finally {
			ois.close();
            fis.close();
		}
		
		//logger.info("Deserialized inventory object size is : "+inventory.getInventoryMap().size());
		return inventory;
	}
	
	
	public boolean checkFile(String fileName) {
		File file = new File(fileName);
		
		return file.exists();
	}
}
