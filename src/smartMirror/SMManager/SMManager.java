package smartMirror.SMManager;

import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JFrame;

import smartMirror.SMPanel.SMPanel;
import smartMirror.Settings.Settings;
import smartMirror.widget.TextWidget;

public class SMManager {

	private static String input;
	private static Scanner scanner;
	private static Settings settings;
	
	private JFrame frame;
	private static SMPanel panel;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		input = "";
		
		while (!configurate());
		System.out.println("Succesfully configurated");
		runSM();
		System.out.println("Succesfully started");
		
		input = scanner.nextLine();
		panel.getWidgetHandler().addWidget(new TextWidget(0, 0, 100, 100));
	}
	
	
	private static boolean configurate() {
		System.out.println("Ini with default values? (y)es or (n)o: ");
		input = scanner.nextLine();

		if (input.equalsIgnoreCase("y")) {
			settings = new Settings();
			return true;
		}else if (input.equalsIgnoreCase("n")) {
			return true;
		}else {
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

	public SMManager(){
		frame = new JFrame();
		frame.setBounds(100, 100, settings.getX(), settings.getY());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new SMPanel();
		panel.setBounds(0, 0, settings.getX(), settings.getY());
		panel.setVisible(true);
		frame.getContentPane().add(panel);
	}
}
