package smartMirror.SMManager;

import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JFrame;

import smartMirror.Command.CommandHandler;
import smartMirror.DateAndTime.DateHandler;
import smartMirror.Exception.SmartMirrorException;
import smartMirror.SMPanel.SMPanel;
import smartMirror.Settings.Settings;
import smartMirror.widget.DateAndClockWidget;

public class SMManager {

	private static String input;
	private static Scanner scanner;
	private static Settings settings;
	private static CommandHandler commandHandler;

	private JFrame frame;
	private static SMPanel panel;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		input = "";

		while (!configurate())
			;
		System.out.println("Succesfully configurated");
		runSM();
		System.out.println("Succesfully started\nEnter (E)xit to stop the program");

		while (!input.equalsIgnoreCase("e")) {
			// commandHandler = new CommandHandler(panel.getWidgetHandler());
			input = scanner.nextLine();

			try {
				/*
				 * if (!input.equalsIgnoreCase("e")) { commandHandler.command(input); }
				 */

				panel.getWidgetHandler().addWidget(new DateAndClockWidget(10, 10, 100, 100));
				
			} catch (SmartMirrorException e) {
				e.printStackTrace();
			}

		}
	}

	private static boolean configurate() {
		System.out.println("Ini with default values? (y)es or (n)o: ");
		input = scanner.nextLine();

		if (input.equalsIgnoreCase("y")) {
			settings = new Settings();
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
}
