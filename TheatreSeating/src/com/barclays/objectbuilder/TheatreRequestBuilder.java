/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays.objectbuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.barclays.logutil.LogUtil;
import com.barclays.theatre_request.TheatreRequest;

/*
 * TheatreRequestBuilder class contains functionality to construct theatre request objects.
 * It has static private field logger which handles logging when an error occurs 
 * 
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class TheatreRequestBuilder {
	
	static Logger logger = null;
	
	/*
	 * logger to take care of logging duties when an exception arises
	 */
	private Logger getLogger() {
		if(logger == null) {
			try {
				logger = LogUtil.getLogger();
			} catch (SecurityException | IOException e) {
				System.exit(1);
			}
		}
		return logger;
	}
	
	/*
	 * This method constructs theaatre request objects and adds those rquest objects into a list
	 * 
	 * @param requestList: This list contains list of theatre requests in the form of string
	 * @return: list of theatre request objects
	 */
	public ArrayList<TheatreRequest> requestBuilder(ArrayList<String> requestList) {
		ArrayList<TheatreRequest> requests = new ArrayList<TheatreRequest>();
		TheatreRequest requestObj;
		for(String request: requestList) {
			requestObj = new TheatreRequest();
			String[] requestInfo = request.split(" ");
			try {
				if(requestInfo.length != 2) {
					throw new IllegalArgumentException();
				}
				requestObj.setName(requestInfo[0]);
				requestObj.setNumberOfRequest(Integer.parseInt(requestInfo[1]));
				requestObj.setRequestProcessed(false);
				requests.add(requestObj);
			} catch(IllegalArgumentException | NullPointerException ex) {
				getLogger().log(Level.INFO,  ex.getClass().getName() + " caught. " + ex);
			} 
		}
		return requests;
	}
}
