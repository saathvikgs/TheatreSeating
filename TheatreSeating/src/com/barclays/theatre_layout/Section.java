/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays.theatre_layout;

/*
 * Section class represents a section in theatre layout.
 * It has private fields for row number, section capacity and section number.
 * This class implements Comparable interface to order section objects based on their seating capacity.
 * It has getter and setter methods to interact with these fields
 * 
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class Section implements Comparable<Section>{
	private int sectionNumber;
	private int capacity;
	private int rowNumber;
	
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getSectionNumber() {
		return sectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
/*
 * This method compares seating capacity of section objects and orders them accordingly in ascending order.
 */
	@Override
	public int compareTo(Section sec) {
		if(this.getCapacity() < sec.getCapacity())
			return -1;
		else if(this.getCapacity() > sec.getCapacity())
			return 1;
		return -1;
	}
	}
