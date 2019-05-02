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

public class SMManager {
	private static Settings settings;
	private static CommandHandler commandHandler;
	private static LogHandler log;

	private static JFrame frame;
	private static SMPanel panel;

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

	public static void main(String[] args) throws IOException {
		while (!configurate())
			;

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
		String input = scanner.next();

		if (input.equalsIgnoreCase("y")) {
			settings = new Settings();
			try {
				createLog();
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

	private static void createLog() throws IOException {
		log = new LogHandler();
		log.createLogFile();
		log.addTextToLogFile(log.CREATED, "New LogFile created!");
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
}
