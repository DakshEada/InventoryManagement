/*
 * ***************************************************************************
 *
 * Author: Daksh Jan 10, 2018 8:16:55 PM
 * Description: 
 * Company: 
 * Copyright (c) 2018.  All rights reserved.
 *
 * ***************************************************************************
 */
package com.demo.inventory.model;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Serializable {		
	private static final long serialVersionUID = -8114550637673258421L;
	private String itemName;
	private double costPrice;
	private double sellingPrice;
	private double quantity;
	
	
	public String getItemName() {
		return itemName;
	}
	
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	public double getCostPrice() {
		return costPrice;
	}
	
	
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
	
	
	public double getSellingPrice() {
		return sellingPrice;
	}
	
	
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}


	public double getQuantity() {
		return quantity;
	}


	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	
	@Override
	public boolean equals(Object object)
	{
		if(object == this)
		{
			return true;
		}
		
		if(!(object instanceof Item))
		{
			return false;
		}
		
		Item item = (Item) object;		
		return Objects.equals(itemName, item.itemName);
	}
	
	
	@Override
    public int hashCode() {
        return Objects.hash(itemName);
    }


	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", costPrice=" + costPrice + ", sellingPrice=" + sellingPrice
				+ ", quantity=" + quantity + "]";
	}
}