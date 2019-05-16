/**
 * Kontrolliert den gesamten Ablauf des Programms. Ist Einstiegspunkt.
* @author Moritz Lindner 
* @author Marvin Sa�e
* @version 0.3.3
* @since 16.05.2019 
* 
* Changelog:
* 0.3.3
* 	- verbesserte Startzeit #21
* 	- angepasste Startprozedur  
* 	- Frameposition angepasst 
* 	- keine Widgets mehr in der Autoloadfunktion 
* 	- Dokumentationserweiterung
* 	- 
*/

package smartMirror.Manager;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import smartMirror.Command.CommandHandler;
import smartMirror.Exception.SmartMirrorException;
import smartMirror.File.LogHandler;
import smartMirror.File.SettingsFileHandler;
import smartMirror.Panel.SmartMirrorPanel;
import smartMirror.Settings.Settings;

public class SmartMirrorManager {
	private static Settings settings;
	private static CommandHandler commandHandler;
	private static SettingsFileHandler sfl;

	private static JFrame frame;
	private static SmartMirrorPanel panel;

	public SmartMirrorManager() throws IOException {
		frame = new JFrame();
		int boundX = 0;
		int boundY = 10;
		frame.setBounds(boundX, boundY, settings.getX(), settings.getY());
		createSettingFile(boundX, boundY, settings.getX(), settings.getY());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new SmartMirrorPanel(settings);
		panel.setBounds(0, 0, settings.getX(), settings.getY());
		panel.setVisible(true);
		frame.getContentPane().add(panel);
	}

	/**
	 * Einstiegspunkt und Hauptfunktion des Programms. Initiert alle ben�tigten
	 * Programmteile und beendet diese auch.
	 * 
	 * @param args not used
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		LogHandler.iniLogHandler();
		while (!configurate())
			;

		System.out.println("succesfully configurated");
		startSmartMirror();
		System.out.println("succesfully started");

		startManager();

		frame.dispose();
	}

	/**
	 * Konfiguriert Programm. (Aktuell wird automatisch die Standardkonfiguration
	 * geladen, d.h. die Userabfragen wurden deaktiviert)
	 * 
	 * @return boolean welcher angiebt ob die Konfiguration erfolgreich war
	 */
	private static boolean configurate() {
		System.out.println("Ini SmartMirror with default values? (y)es or (n)o: ");
		Scanner scanner = new Scanner(System.in);
		String input = "y"; // scanner.next();

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

	/**
	 * Erstellt den Frame des Mirrors
	 */
	private static void startSmartMirror() {
		try {
			new SmartMirrorManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SmartMirrorManager.frame.setVisible(true);
	}

	/**
	 * Hauptschleife des Managers. Beinhaltet den CommandHandler und �bernimmt daher
	 * die interaktion mit dem Nutzer.
	 */
	private static void startManager() {
		commandHandler = new CommandHandler(panel.getWidgetHandler());
		System.out.println("**********************\nCommandhandler online:\n**********************");
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
		} else {
			LogHandler.addTextToLogFile(LogHandler.CREATED, "New LogFile created!");
			LogHandler.addTextToLogFile(LogHandler.CREATED, "New Settingfile created!");
			LogHandler.addTextToLogFile(LogHandler.STARTED, "Mirror started!");
		}
	}

	public static void changeDim(int xdim, int ydim) {
		frame.setBounds(100, 100, xdim, ydim);	//hier sollten die Bounds aus Settings eingebaut werden 
		settings.setX(xdim);
		settings.setY(ydim);
		panel.setBounds(0, 0, settings.getX(), settings.getY());
		panel.getWidgetHandler().updateMap(settings);
		panel.getWidgetHandler().proofAllWidgets();
	}

	public static void changeyDim(int ydim) {
		changeDim(frame.getX(), ydim);
	}

	/**
	 * 
	 * @param xdim Neuer Wert f�r die X-Dimension
	 */
	public static void changexDim(int xdim) {
		changeDim(xdim, frame.getY());
	}

	private static void autoloadWidgets() {
		/*
		 * aktuell ausgeschalten
		try {
			panel.getWidgetHandler().addWidget(new AdvancedClockWidget(5, 5, 400, 400, panel));
			panel.getWidgetHandler().addWidget(new WeatherWidget(5, 600, 400, 400, panel));
		} catch (SmartMirrorException e) {
			e.printStackTrace();
		}
		*/
	}

	private void createSettingFile(int boundsX, int boundsY, int xCoord, int yCoord) throws IOException {
		sfl = new SettingsFileHandler();
		sfl.fillInSettingFile(boundsX, boundsY, xCoord, yCoord);
	}
}