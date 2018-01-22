/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays.process_request;

import java.util.ArrayList;

import java.util.PriorityQueue;

import com.barclays.objectbuilder.RowBuilder;
import com.barclays.theatre.Theatre;
import com.barclays.theatre_layout.*;
import com.barclays.theatre_request.TheatreRequest;

/*
 * ProcessRequest class contains functionality to process each theatre request.
 * 
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class ProcessRequest {
	
	ArrayList<String> result = new ArrayList<>();
	
	/*
	 * This method contains functionality to process theatre requests
	 * 
	 *  @param: theatre- object that reprensts the theatre layout
	 *  @param: requests: list of theatre requests 
	 */
	public void serviceRequests(Theatre theatre, ArrayList<TheatreRequest> requests) {
		for(int i = 0; i < requests.size(); i++) {
			result.add(null);
		}
		for(int i = 0; i < requests.size(); i++) {
			if(requests.get(i).isRequestProcessed())
				continue;
			serviceRequestsHelper(theatre, requests.get(i), requests, i);
		}
	}
	
	/*
	 * This method is a helper to serviceRequests method.
	 * It iterates through each request and for each request,
	 * if number of tickets required is equal to section capacity, then that section is assigned
	 * else if number of tickets required less that section capacity, 
	 * 		then if two complimentary requests fit into a section
	 * 				then assign requests to that section
	 * 			else
	 * 				find a section where number of tickets match perfectly with section capacity. 
	 * 				if found 
	 * 					assign
	 * 				else
	 * 					 find a section whose capacity is greater than number of tickets requested
	 * 
	 * if the whole operation above fails, then check 
	 * whether available theatre capacity is greater than the number of tickets requested
	 * 				
	 * @param: theatre-theatre object
	 * @param: reqObj- theatre request object
	 * @param: requests- list containing the request objects
	 * @param: index- to begin searching a compliment request in the lists if requests beginningfrom this index
	 */
	private void serviceRequestsHelper(Theatre theatre, TheatreRequest reqObj, ArrayList<TheatreRequest> requests, int index) {	
		boolean requestProcessed = false;
		String str = null;
		RowBuilder rb = new RowBuilder();
		PriorityQueue<Section> sectionQueue = rb.getSectionQueue();
		for(Row row:theatre.getRows()) {
			for(Section section: row.getSection()) {
				if(reqObj.getNumberOfRequest() == section.getCapacity()) {
					str = reqObj.getName() + " Row " + row.getRowNumber() + " Section " + section.getSectionNumber();
					section.setCapacity(section.getCapacity() - reqObj.getNumberOfRequest());
					theatre.setTotalAvailableSeats(theatre.getTotalAvailableSeats() - reqObj.getNumberOfRequest());
					requestProcessed = true;
					reqObj.setRequestProcessed(true);
					result.set(index, str);
					return;
				} 
				else if(reqObj.getNumberOfRequest() < section.getCapacity()) {
					int complementRequestNumber = getComplementRequest(requests, section.getCapacity() - reqObj.getNumberOfRequest(), index);
					if(complementRequestNumber != -1) {
						str = reqObj.getName() + " Row " + row.getRowNumber() + " Section " + section.getSectionNumber();
						section.setCapacity(section.getCapacity() - reqObj.getNumberOfRequest());
						theatre.setTotalAvailableSeats(theatre.getTotalAvailableSeats() - reqObj.getNumberOfRequest());
						requestProcessed = true;
						reqObj.setRequestProcessed(true);
						result.set(index, str);
						
						TheatreRequest complementRequest = requests.get(complementRequestNumber);
						
						str = complementRequest.getName() + " Row " + row.getRowNumber() + " Section " + section.getSectionNumber();
						section.setCapacity(section.getCapacity() - complementRequest.getNumberOfRequest());
						theatre.setTotalAvailableSeats(theatre.getTotalAvailableSeats() - complementRequest.getNumberOfRequest());
						requestProcessed = true;
						complementRequest.setRequestProcessed(true);
						result.set(complementRequestNumber, str);
						return;
					} 
					else {	
						Section perfectSection = getPerfectSection(sectionQueue, reqObj.getNumberOfRequest()); 
						if(perfectSection != null) {
							str = reqObj.getName() + " Row " + perfectSection.getRowNumber() + " Section " + perfectSection.getSectionNumber();
							perfectSection.setCapacity(perfectSection.getCapacity() - reqObj.getNumberOfRequest());
							theatre.setTotalAvailableSeats(theatre.getTotalAvailableSeats() - reqObj.getNumberOfRequest());
							requestProcessed = true;
							reqObj.setRequestProcessed(true);
							result.set(index, str);
							return;
						} else {
							str = reqObj.getName() + " Row " + row.getRowNumber() + " Section " + section.getSectionNumber();
							section.setCapacity(section.getCapacity() - reqObj.getNumberOfRequest());
							theatre.setTotalAvailableSeats(theatre.getTotalAvailableSeats() - reqObj.getNumberOfRequest());
							requestProcessed = true;
							reqObj.setRequestProcessed(true);
							result.set(index, str);
							return;
						}					
					}
				}        
			}
		}
		if(requestProcessed != true) {
			if(reqObj.getNumberOfRequest() <= theatre.getTotalAvailableSeats()){ 
				str = reqObj.getName() + " Call to split party.";
				result.set(index, str);
			}
			else {
				str = reqObj.getName() + " Sorry, we can't handle your party.";
				result.set(index, str);
			}
		}
	}
	/*
	 * This method return a section whose capacity perfectly matches with the number of tickets requested
	 */
	private Section getPerfectSection(PriorityQueue<Section> sectionQueue, int numberOfRequest) {
		
		Section foundSection = null;
		ArrayList<Section> list = new ArrayList<Section>();
		while(!sectionQueue.isEmpty()) {
			Section s = sectionQueue.poll();
			if(s.getCapacity() == numberOfRequest) {
				foundSection = s;
				break;
				//itr.remove();
			} else {
				if(s.getCapacity() < numberOfRequest)
				continue;
			else
				foundSection = null;
			list.add(s);
			}
		}
		
		for(Section sec: list) {
			if(sec!= null)
				sectionQueue.add(sec);
		}
		return foundSection;
	}
	
	/*
	 * This method finds the index of a request whose number of tickets required compliments
	 *  another request of seats so that it fits perfectly into a section without wasting the seats in that section
	 */
	private int getComplementRequest(ArrayList<TheatreRequest> requests, int seats, int index) {
		
		int requestNo = -1;

        for(int i=index+1 ; i<requests.size() ; i++){ 
            TheatreRequest request = requests.get(i);
            if(!request.isRequestProcessed() && request.getNumberOfRequest() == seats){
                requestNo = i;
                break;              
            }
        }
        return requestNo;
	}

	public ArrayList<String> getResult() {
		return result;
	}
}
