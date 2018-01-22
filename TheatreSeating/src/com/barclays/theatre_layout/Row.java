/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays.theatre_layout;

import java.util.ArrayList;

/*
 * Row class represents a row in theatre layout.
 * It has private fields for row number, list of section. Each row can contain one or more section.
 * It has getter and setter methods to interact with these fields
 * 
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class Row {
	private int rowNumber;
	private ArrayList<Section> section;
	
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public ArrayList<Section> getSection() {
		return section;
	}
	public void setSection(ArrayList<Section> section) {
		this.section = section;
	}
}
