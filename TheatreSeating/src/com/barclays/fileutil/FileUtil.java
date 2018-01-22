/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays.fileutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

import com.barclays.logutil.LogUtil;

/*
 * FileUtil class provides functionality to read layout and theatre request data from a selected text file.
 * This class has functionality to read from file and creates lists of theatre layout and ticket requests.
 * 
 * This class has two data members and setter-getter methods for those members
 * The data members are: theatreLayout and ticketRequest
 * 
 * There is one static data member logger
 * 
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class FileUtil {
	
	static Logger logger = null;
	private ArrayList<String> theatreLayout;      	//holds list of layout
	private ArrayList<String> ticketRequest;	  	//holds list of ticket requests
	
	public ArrayList<String> getTheatreLayout() {
		return theatreLayout;
	}

	public void setTheatreLayout(ArrayList<String> theatreLayout) {
		this.theatreLayout = theatreLayout;
	}

	public ArrayList<String> getTicketRequest() {
		return ticketRequest;
	}

	public void setTicketRequest(ArrayList<String> ticketRequest) {
		this.ticketRequest = ticketRequest;
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

	/*
	 * readFromFile() method reads string objects from a selected text file.
	 */
	public void readFromFile() {
		File file = null;
		try {
		file = new File(getFileLocation());
		}
		catch (NullPointerException ex) {
			getLogger().log(Level.INFO,  ex + " caught. File cannot be null. Choose a proper file ");
			System.exit(1);
		}
		readFromFileHelper(file);
	}
	
	/*
	 * This is a helper function to readFromFile.
	 * This function iterates through each line of the file and reads one string object at a time.
	 * Then populates the theatreLayout and ticketRequest list respectively.
	 * 
	 * @param file: File containing input data
	 */
	private void readFromFileHelper(File file) {
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		String line;
		String parseCurrentInput = "layout";
		ArrayList<String> theatreLayoutList = new ArrayList<String>();
		ArrayList<String> ticketRequestList = new ArrayList<String>();
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				if(line.trim().isEmpty()) {
					parseCurrentInput = "requests";
					continue;
				}
				if(parseCurrentInput.equals("layout")) {
					theatreLayoutList.add(line);
				} else {
					ticketRequestList.add(line);
				}
			}
			setTheatreLayout(theatreLayoutList);
			setTicketRequest(ticketRequestList);
		} catch (IOException ex) {
			getLogger().log(Level.INFO,  ex.getClass().getName() + " caught. " + ex);
		}
		finally {
			closeBufferedReader(bufferedReader);
		}
	}
	
	/*
	 * Closes buffered reader after the end of file is reached.
	 * 
	 * @param bufferedReader which represents buffered reader
	 */
	private void closeBufferedReader(BufferedReader bufferedReader) {
		if(bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException ex) {
				getLogger().log(Level.INFO,  ex.getClass().getName() + " caught. " + ex);
			}
		}
	}

	/*
     * This method provides functionality choose a file from file system.
     * It opens a dialogue box from where a user can select a file.
     * If the user chooses a file, it returns the string representation of file's location 
     * else it returns null
     */
	private static String getFileLocation() {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setCurrentDirectory(new java.io.File("."));
	    fileChooser.setDialogTitle("Select file");
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    fileChooser.setAcceptAllFileFilterUsed(false);
	
	    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	      return fileChooser.getSelectedFile().toString();
	    } else {
	      return null;
	    }
    }
}
