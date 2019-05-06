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

	DateHandler dh;
	String fileName;
	LogHandler log;
	Settings settings; 
	File settingFile;
	
	public SettingsFileHandler() throws IOException {
		dh = new DateHandler();
		createFolder();
	}
	
	public void createFormSettingFile() throws IOException {
		fileName = "settings" + File.separator + "formSsettings_from_" + dh.getFullDate() + ".ini";
		settingFile = new File(fileName);
		if(!settingFile.exists()) {
			settingFile.createNewFile();
			log.addTextToLogFile(log.CREATED, "New Form settings created!");
		}
	}	

	public void getWidget(String kindOfWidget, String fileName) throws UnsupportedEncodingException, FileNotFoundException {
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName, true), "UTF-8");
		BufferedWriter fw = new BufferedWriter(out);
//
	}
	
	// form settings auto write schreiben
	private void createFolder() {
		File settingFolder = new File("settings");
		settingFolder.mkdir();
	}
}
