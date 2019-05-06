/**
 * 
 */
package smartMirror.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author marvi_000
 *
 */
public class widgetFileHandler {
	String fileName;
	LogHandler log;
	File widgetFile;
	ArrayList<File> widgetList;

	/**
	 * 
	 */
	public widgetFileHandler() {
		createFolder();
	}
	
	public void createNewWidgetFile(String kind, int xCoord, int yCoord, int xBound, int yBound, String text) throws IOException {
		fileName = "widgets" + File.separator + kind + widgetList.size();
		widgetFile = new File(fileName);
		if(!widgetFile.exists()) {
			widgetFile.createNewFile();
			widgetList.add(widgetFile);
			LogHandler.addTextToLogFile(log.ADDED, "New Widget added!");
			fillInWidgetFile(xCoord, yCoord, xBound, yBound, text);
		}
	}
	
	private void createFolder() {
		File widgetFolder = new File("widgets");
		widgetFolder.mkdir();
	}
	
	private void fillInWidgetFile(int xCoord, int yCoord, int xBound, int yBound, String text) throws IOException {
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8");
		BufferedWriter fw = new BufferedWriter(out);
		
		fw.write("x Coord: ");
		fw.write(xCoord);
		fw.newLine();
		fw.write("y Coord: ");
		fw.write(yCoord);
		fw.newLine();
		fw.write("x Bound: ");
		fw.write(xBound);
		fw.newLine();
		fw.write("y Bound: ");
		fw.write(yBound);
		if(!text.isEmpty()) {
			fw.write("Text: ");
			fw.write(text);
		}
		fw.close();
	}

}
