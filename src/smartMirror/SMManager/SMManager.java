package smartMirror.SMManager;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;

import smartMirror.Command.CommandHandler;
import smartMirror.Exception.SmartMirrorException;
import smartMirror.SMPanel.SMPanel;
import smartMirror.Settings.Settings;

class SMManager {

	private static String input;
	private static Scanner scanner;
	private static BufferedReader reader;
	private static Settings settings;
	private static CommandHandler commandHandler;

	private static JFrame frame;
	private static SMPanel panel;

	public static void main(String[] args) throws IOException, SmartMirrorException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		//scanner = new Scanner(System.in);
		input = "";

		while (!configurate())
			;

		System.out.println("Succesfully configurated");
		runSM();
		System.out.println("Succesfully started\nEnter (E)xit to stop the program");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		commandHandler = new CommandHandler(panel.getWidgetHandler());
		while (!input.equalsIgnoreCase("e")) {
			System.out.println("loop");
			try {
				//if (reader.readLine())
						//scanner.hasNext())
				input = reader.readLine();
				reader.reset();

				if (!input.equalsIgnoreCase("e")) {
					commandHandler.command(input);
				}
				
				/*
				 * panel.getWidgetHandler().addWidget(new DateAndClockWidget(10, 10, 100, 100,
				 * panel));
				 * 
				 * Thread t = new Thread(panel.getWidgetHandler().getActiveWidgets());
				 * t.start();
				 */
			} catch (Exception e) {
				//e.printStackTrace();
				reader = new BufferedReader(new InputStreamReader(System.in));
			}
		}
		
		frame.dispose();
	}

	private static boolean configurate() throws IOException {
		System.out.println("Ini with default values? (y)es or (n)o: ");
		input = reader.readLine();

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
