/**
* @author  Marvin Saﬂe
* @version 0.1
* @since 15.05.2019 
*/

package smartMirror.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import smartMirror.DateAndTime.DateHandler;

public class LogHandler {

	static DateHandler dh;
	static String fileName;
	static File logFile;
	
	public static String CREATED = "CREATED";
	public static String REMOVED = "REMOVED";
	public static String CHANGED = "CHANGED";
	public static String STARTED = "STARTED";
	public static String ADDED = "ADDED";
	public static String WEATHERERROR = "WEATHERERROR";
	
	
	public static void iniLogHandler() throws IOException {
		dh = new DateHandler();
		createFolder();
		createLogFile();
	}

	
	public static void createLogFile() throws IOException {
		fileName = "log" + File.separator + "started_at_" + dh.getFullDate() +".log";
		logFile = new File(fileName);
		if(!logFile.exists()) {
			logFile.createNewFile();
		}		
	}
	
	public static void addTextToLogFile(String message, String text) throws IOException {
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8");
		BufferedWriter fw = new BufferedWriter(out);

		fw.write("[" + dh.getFullDate() + "\t");// write date and a tab
		fw.write(dh.getFullTime() + "]" + "\t");// write time and a tab
		fw.write("[" + message + "]" + "\t");// write a message and a tab
		fw.write("[" + text + "]" + "\n");// write the text and a new line
		fw.close();
		
	}
	
	private static void createFolder() {
		File logFolder = new File("log");
		
		logFolder.mkdir();
	}
	
	public static boolean fileExist() {
		boolean status = false;
		if (logFile.exists()) {
			System.out.print("gefunden");
			status = true;
		}
		
		return status;
	}
	
}
