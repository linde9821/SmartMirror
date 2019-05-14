package smartMirror.SMManager;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import smartMirror.Command.CommandHandler;
import smartMirror.Exception.SmartMirrorException;
import smartMirror.File.LogHandler;
import smartMirror.File.SettingsFileHandler;
import smartMirror.SMPanel.SMPanel;
import smartMirror.Settings.Settings;
import smartMirror.widget.AdvancedClock;
import smartMirror.widget.WeatherWidget;

public class SMManager {
	private static Settings settings;
	private static CommandHandler commandHandler;
	private static LogHandler log;
	private static SettingsFileHandler sfl;

	private static JFrame frame;
	private static SMPanel panel;

	public SMManager() throws IOException {
		frame = new JFrame();
		int boundX = 100;
		int boundY = 100;
		frame.setBounds(boundX, boundY, settings.getX(), settings.getY());
		createSettingFile(boundX, boundY, settings.getX(), settings.getY());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new SMPanel(settings);
		panel.setBounds(0, 0, settings.getX(), settings.getY());
		panel.setVisible(true);
		frame.getContentPane().add(panel);
	}

	public static void main(String[] args) throws IOException {
		LogHandler.iniLogHandler();
		while (!configurate());

		System.out.println("Succesfully configurated");
		startSmartMirror();
		System.out.println("Succesfully started\nEnter (E)xit to stop the program");

		// wait for frame
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		startManager();

		frame.dispose();
	}

	private static boolean configurate() {
		System.out.println("Ini SmartMirror with default values? (y)es or (n)o: ");
		Scanner scanner = new Scanner(System.in);
		String input = "y"; //scanner.next();

		if (input.equalsIgnoreCase("y")) {
			settings = new Settings();
			try {
				createFiles();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else if (input.equalsIgnoreCase("n")) {
			return true;
		} else {
			System.out.println("Unknown input");
			return false;
		}
	}

	private static void startSmartMirror() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SMManager window = new SMManager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void startManager() {
		commandHandler = new CommandHandler(panel.getWidgetHandler(), log);
		System.out.println("Commandhandler online:");
		Scanner scanner = new Scanner(System.in);
		String input;
		
		autoloadWidgets();

		while (true) {
			System.out.print("/: ");
			input = scanner.nextLine();

			try {
				commandHandler.command(input);
			} catch (SmartMirrorException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static void createFiles() throws IOException {
		if (LogHandler.fileExist()) {
			LogHandler.addTextToLogFile(LogHandler.STARTED, "Mirror started!");
		}else {
			LogHandler.addTextToLogFile(LogHandler.CREATED, "New LogFile created!");
			LogHandler.addTextToLogFile(LogHandler.CREATED, "New Settingfile created!");
			LogHandler.addTextToLogFile(LogHandler.STARTED, "Mirror started!");
		}
	}

	public static void changeDim(int xdim, int ydim) {
		frame.setBounds(100, 100, xdim, ydim);
		settings.setX(xdim);
		settings.setY(ydim);
		panel.setBounds(0, 0, settings.getX(), settings.getY());
		
	}

	public static void changeyDim(int ydim) {
		changeDim(frame.getX(), ydim);

	}

	public static void changexDim(int xdim) {
		changeDim(xdim, frame.getY());
	}
	
	private static void autoloadWidgets() {
		try {
			//panel.getWidgetHandler().addWidget(new AdvancedClock(5, 5, 400, 400, panel));
			panel.getWidgetHandler().addWidget(new WeatherWidget(5, 600, 400, 400, panel));
		} catch (SmartMirrorException e) {
			e.printStackTrace();
		}
	}
	
	private void createSettingFile(int boundsX, int boundsY, int xCoord, int yCoord) throws IOException {
		sfl = new SettingsFileHandler();
		sfl.fillInSettingFile(boundsX, boundsY, xCoord, yCoord);
	}
}
