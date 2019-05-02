package smartMirror.File;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import smartMirror.DateAndTime.DateHandler;
import smartMirror.Location.Area;
import smartMirror.Settings.Settings;

public class SettingsFileHandler {

	DateHandler dh;
	String fileName;
	LogHandler log;
	Settings settings; 
	
	public SettingsFileHandler() throws IOException {
		dh = new DateHandler();
		createFolder();
	}
	
	public void createFormSettingFile() throws IOException {
		fileName = "settings" + File.separator + "formSsettings_from_" + dh.getFullDate() + ".ini";
		File settingFile = new File(fileName);
		if(!settingFile.exists()) {
			settingFile.createNewFile();
			log.addTextToLogFile(log.CREATED, "New Form settings created!");
			//createFileForm();
		}
	}	
	
	public void addSettingToSettingFile(String input, Area area, String widgetText) throws IOException {		
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(this.fileName, true), "UTF-8");
		BufferedWriter fw = new BufferedWriter(out);
		
		fw.write("(" + input + ")");
		fw.newLine();
		fw.write(area.getxCoord());
		fw.newLine();
		fw.write(area.getyCoord());
		fw.newLine();
		fw.write(area.getHight());
		fw.newLine();
		fw.write(area.getWidth());
		fw.close();
	}
	
	private void createFolder() {
		File settingFolder = new File("settings");
		settingFolder.mkdir();
	}
}
