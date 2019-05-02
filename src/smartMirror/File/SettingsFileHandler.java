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
	
	OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(this.fileName, true), "UTF-8");
	BufferedWriter fw = new BufferedWriter(out); 
	
	public SettingsFileHandler(LogHandler log) throws IOException {
		this.log = log;
	}
	
	public void createSettingFile() throws IOException {
		fileName = "setting" + File.separator + "settings_from_" + dh.getFullDate();
		File settingFile = new File(fileName);
		if(!settingFile.exists()) {
			settingFile.createNewFile();
			log.addTextToLogFile(log.CREATED, "New settingfile created!");
			createFileForm();
		}
	}
	
	public void createFileForm() throws IOException {
		fw.write(settings.getX());
		fw.newLine();
		fw.write(settings.getY());
		fw.newLine();
		fw.write("[Widgets]");
		fw.newLine();	
		fw.close();
	}
	
	
	public void addSettingToSettingFile(String input, Area area, String widgetText) throws IOException {		
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
	
	

}
