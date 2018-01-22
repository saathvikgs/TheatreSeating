/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays;

import java.util.ArrayList;

import com.barclays.fileutil.FileUtil;
import com.barclays.objectbuilder.RowBuilder;
import com.barclays.objectbuilder.TheatreRequestBuilder;
import com.barclays.process_request.ProcessRequest;
import com.barclays.theatre.Theatre;
import com.barclays.theatre_layout.Row;
import com.barclays.theatre_request.TheatreRequest;

/*
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class TheatreSeating {

	public static void main(String[] args) {
		FileUtil fileUtil = new FileUtil();
		
		fileUtil.readFromFile();
		
		ArrayList<String> theatreLayout = fileUtil.getTheatreLayout();
		ArrayList<String> ticketRequest = fileUtil.getTicketRequest();
		
		ArrayList<Row> rows = new ArrayList<>();
		RowBuilder rowBuilder = new RowBuilder();
		rows = rowBuilder.rowBuilder(theatreLayout);
		
		TheatreRequestBuilder requestBuilder = new TheatreRequestBuilder();
		ArrayList<TheatreRequest> requests = new ArrayList<TheatreRequest>();
		requests = requestBuilder.requestBuilder(ticketRequest);
		
		Theatre theatre = new Theatre();
		theatre.setRows(rows);
		theatre.setTotalCapacity(rowBuilder.getTheatreCapacity());
		theatre.setTotalAvailableSeats(rowBuilder.getTheatreCapacity());
		
		ProcessRequest processRequest = new ProcessRequest();
		processRequest.serviceRequests(theatre, requests);
		
		ArrayList<String> resList = processRequest.getResult();
		for(String s: resList) {
			System.out.println(s);
		}
		
	}

}
