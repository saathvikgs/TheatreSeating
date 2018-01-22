/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays.theatre_request;

/*
 * TheatreRequest class represents a request for tickets.
 * It has private fields for person name requesting for ticket, number of ticket requests 
 * and to check whether ticket request is processed.
 * It has getter and setter methods to interact with these fields.
 * 
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class TheatreRequest {
	private String name;
	private int numberOfRequest;
	private boolean isRequestProcessed;
	
	public boolean isRequestProcessed() {
		return isRequestProcessed;
	}
	public void setRequestProcessed(boolean isRequestProcessed) {
		this.isRequestProcessed = isRequestProcessed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfRequest() {
		return numberOfRequest;
	}
	public void setNumberOfRequest(int numberOfRequest) {
		this.numberOfRequest = numberOfRequest;
	}
}
