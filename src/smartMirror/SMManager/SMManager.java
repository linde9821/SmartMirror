package smartMirror.SMManager;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import smartMirror.Command.CommandHandler;
import smartMirror.Exception.SmartMirrorException;
import smartMirror.File.LogHandler;
import smartMirror.SMPanel.SMPanel;
import smartMirror.Settings.Settings;

class SMManager {

	private static String input;
	private static Scanner scanner;
	private static Settings settings;
	private static CommandHandler commandHandler;
	private static LogHandler log;


	private static JFrame frame;
	private static SMPanel panel;

	public static void main(String[] args) throws IOException {
		scanner = new Scanner(System.in);
		input = "";

		while (!configurate());
		
		System.out.println("Succesfully configurated");
		runSM();
		System.out.println("Succesfully started\nEnter (E)xit to stop the program");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		commandHandler = new CommandHandler(panel.getWidgetHandler(), log);
		while (!input.equalsIgnoreCase("e")) {
			System.out.println();
			scanner = new Scanner(System.in);
			scanner.reset();
			try {
				if (scanner.hasNext()) {
					input = scanner.nextLine();
					scanner.close();

					if (!input.equalsIgnoreCase("e")) {
						commandHandler.command(input);
					}
				}
			} catch (SmartMirrorException e) {
				e.printStackTrace();
			}
		}

		frame.dispose();
	}

	private static boolean configurate() throws IOException {
		System.out.println("Ini with default values? (y)es or (n)o: ");
		input = scanner.next();

		if (input.equalsIgnoreCase("y")) {
			settings = new Settings();
			createLog();
			return true;
		} else if (input.equalsIgnoreCase("n")) {
			return true;
		} else {
			System.out.println("Unknown input");
			return false;
		}
		
	}

	private static void runSM() {
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

	public SMManager() {
		frame = new JFrame();
		frame.setBounds(100, 100, settings.getX(), settings.getY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new SMPanel(settings);
		panel.setBounds(0, 0, settings.getX(), settings.getY());
		panel.setVisible(true);
		frame.getContentPane().add(panel);
	}
	
	public static void createLog() throws IOException {
		log  = new LogHandler();
		log.createLogFile();
		log.addTextToLogFile(log.CREATED, "New LogFile created!");
	}
}
