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
	
	public static String CREATED = "CREATED";
	public static String REMOVED = "REMOVED";
	public static String CHANGED = "CHANGED";
	
	
	public LogHandler() {
		dh = new DateHandler();
		createFolder();
	}

	
	public static void createLogFile() throws IOException {
		fileName = "log" + File.separator + "started_at_" + dh.getFullDate() +".log";
		File logFile = new File(fileName);
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
	
}
