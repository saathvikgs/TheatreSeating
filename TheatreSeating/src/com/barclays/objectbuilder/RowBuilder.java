/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays.objectbuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.barclays.logutil.LogUtil;
import com.barclays.theatre_layout.Row;
import com.barclays.theatre_layout.Section;

/*
 * RowBuilder class contains functionality to construct row and section objects.
 * It has static private fields for queue consisting of sections
 *  and logger which handles logging when an error occurs 
 * 
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class RowBuilder {
	
	static PriorityQueue<Section> sectionQueue;		// Queue of section to get a section based on its capacity
	static Logger logger = null;
	static {
		sectionQueue = new PriorityQueue<Section>();
	}
	
	/*
	 * Anonymous class that implements Comparator interface to 
	 * obtain section objects in ascending order from the priority queue
	 */
	public static Comparator<Section> capacityComparator = new Comparator<Section>() {

		@Override
		public int compare(Section sec1, Section sec2) {
			return (sec1.getCapacity() - sec2.getCapacity());
		}
		
	};
	
	public PriorityQueue<Section> getSectionQueue() {
		return sectionQueue;
	}
	
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
	static int theatreCapacity = 0;
	
	/*
	 * This method constructs row objects and adds those rows into a list
	 * 
	 * @param layoutList: This list contains list of theatre layout in the form of string
	 * @return: list of row objects
	 */
	public ArrayList<Row> rowBuilder(ArrayList<String> layoutList) {
		ArrayList<Row> rows = new ArrayList<>();
		Row row;
		int rowNumber = 1;
		for(String layout:layoutList) {
			row = new Row();
			row.setRowNumber(rowNumber++);
			try {
				row.setSection(sectionBuilder(layout, row.getRowNumber()));
			} catch (SecurityException | IOException e) {
				System.exit(0);
			}
			rows.add(row);
		}
		return rows;
	}
	
	/*
	 * This method constructs section objects
	 * 
	 * @param layout: A string representation of layout
	 * @param rowNumber: This is the row number of a row to which the section belongs
	 * 
	 * @return ArrayList<Section>: Array list of section objects required for each row object
	 */
	public ArrayList<Section> sectionBuilder(String layout, int rowNumber) throws SecurityException, IOException {
		Section section;
		ArrayList<Section> sectionList = new ArrayList<Section>();
		String[] sections = layout.split(" ");
		for(int itr = 0; itr < sections.length; itr++) {
			section = new Section();
			try {
				if(sections[itr] == null || sections[itr].length() == 0)
					throw new NullPointerException();
				section.setSectionNumber(itr + 1);
				section.setCapacity(Integer.parseInt(sections[itr]));
				section.setRowNumber(rowNumber);
				theatreCapacity += Integer.parseInt(sections[itr]);
			} catch(NumberFormatException | NullPointerException ex) {
				getLogger().log(Level.INFO,  ex.getClass().getName() + " caught. " + ex);
			}
			sectionQueue.add(section);
			sectionList.add(section);
		}
		return sectionList;
	}
	public int getTheatreCapacity() {
		return theatreCapacity;
	}
}
