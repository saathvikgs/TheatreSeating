/*
 * Copyright (c) 2018, Saathvik Shashidhar Gowrapura
 * 
 * All rights reserved
 * 
 */

package com.barclays.logutil;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/*
 * LogUtil class has functionality to handle logging when an exception is thrown.
 *  
 * @version 1.0  
 * @author Saathvik Shashidhar Gowrapura
 */
public class LogUtil {
	public static Logger getLogger() throws SecurityException, IOException {
		String logfile = "./theatreseating_log.txt";
		Logger logger = Logger.getLogger(logfile);
		FileHandler fh = new FileHandler(logfile);
		logger.addHandler(fh);
		logger.setUseParentHandlers(false);
		return logger;
	}
}
