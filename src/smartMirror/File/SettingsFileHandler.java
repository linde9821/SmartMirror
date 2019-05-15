/**
* @author  Marvin Saﬂe
* @version 0.1
* @since 15.05.2019 
*/

package smartMirror.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import smartMirror.DateAndTime.DateHandler;
import smartMirror.Settings.Settings;

public class SettingsFileHandler {

	static DateHandler dh;
	static String fileName;
	static LogHandler log;
	static Settings settings; 
	static File settingFile;
	
	public SettingsFileHandler() throws IOException {
		dh = new DateHandler();
		createFolder();
		createFormSettingFile();
	}
	
	public static void createFormSettingFile() throws IOException {
		fileName = "settings" + File.separator + "formSettings_from_" + dh.getFullDate() + ".ini";
		settingFile = new File(fileName);
		if(settingFile.exists()) {
			settingFile.delete();
		}
		if(!settingFile.exists()) {
			settingFile.createNewFile();
			log.addTextToLogFile(log.CREATED, "New Form settings created!");
		}
	}	

	private static void createFolder() {
		File settingFolder = new File("settings");
		settingFolder.mkdir();
	}
	
	public void fillInSettingFile(int boundsX, int boundsY, int xCoord, int yCoord) throws IOException {
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8");
		BufferedWriter fw = new BufferedWriter(out);
		
		fw.write("Bound X: ");
		fw.write(boundsX);
		fw.newLine();
		fw.write("Bound Y: ");
		fw.write(boundsY);
		fw.newLine();
		fw.write("xCoord: ");
		fw.write(xCoord);
		fw.newLine();
		fw.write("yCoord: ");
		fw.write(yCoord);
		fw.close();		
	}

	
}
