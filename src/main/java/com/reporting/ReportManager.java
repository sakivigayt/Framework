package com.reporting;

import java.io.File;

import com.log.LoggerFactory;
import com.log.MyLogger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class ReportManager {

	/**
	 * The Logger logger
	 */
	final static MyLogger logger = LoggerFactory.getLogger(ReportManager.class);

	/**
	 * The ExtentReports extent
	 */
	private static ExtentReports extent;

	/**
	 * This method gets the reporter instance setting the filepath and network
	 * mode
	 * 
	 * @param filePath
	 *            the filepath to store report
	 * @return
	 */
	public synchronized static ExtentReports getReporter(String filePath) {
		logger.info("Get the reporter");
		if (extent == null) {	
			extent = new ExtentReports(filePath, true, NetworkMode.OFFLINE);
			extent.loadConfig(new File(System.getProperty("user.dir")+File.separator+ "src\\main\\resources\\Reporting.xml"));
		}
		return extent;
	}

	public synchronized static ExtentReports getReporter() {
		return extent;
	}
}