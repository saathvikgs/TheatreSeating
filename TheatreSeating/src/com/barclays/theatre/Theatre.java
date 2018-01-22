/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays.theatre;

import java.util.ArrayList;

import com.barclays.theatre_layout.Row;

/*
 * Theatre class represents theatre entity.
 * It has private fields for list of rows, total capacity of the theatre and 
 * total available seats that remain after a request is processed.
 * 	
 * It has getter and setter methods to interact with these fields
 * 
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class Theatre {
	private ArrayList<Row> rows;
	private int totalCapacity;
	private int totalAvailableSeats;
	
	public int getTotalAvailableSeats() {
		return totalAvailableSeats;
	}
	public void setTotalAvailableSeats(int totalAvailableSeats) {
		this.totalAvailableSeats = totalAvailableSeats;
	}
	public ArrayList<Row> getRows() {
		return rows;
	}
	public void setRows(ArrayList<Row> rows) {
		this.rows = rows;
	}
	public int getTotalCapacity() {
		return totalCapacity;
	}
	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}
}
