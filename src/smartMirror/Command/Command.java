/**
 * Abstrakte Klasse welche die Grundeigenschaften aller Commands bereitstellt
* @author  Moritz Lindner
* @version 0.2.2
* @since 15.05.2019 
*/

package smartMirror.Command;

import java.io.IOException;
import java.util.ArrayList;

import smartMirror.Exception.SmartMirrorException;

public abstract class Command {
	/**
	 * Erstellt Instanz zur Laufzeit.
	 * 
	 * @param command String welcher den Command aufruft
	 */
	public Command(String command) {
		this.command = command;
		alias = new ArrayList<String>();
	}

	protected String command;
	protected ArrayList<String> alias;

	/**
	 * Definiert was geschehen soll wenn das Command aufgerufen wird.
	 * 
	 * @throws SmartMirrorException
	 * @throws IOException
	 */
	public abstract void runCommand() throws SmartMirrorException, IOException;

	/**
	 * Liefert den String zum Aufrufen des Commands sowie alle Alias.
	 * 
	 * @return String
	 */
	public String getCommand() {
		return command + alias;
	}

	/**
	 * Fügt ein neues Alias zum Command hinzu.
	 * 
	 * @param newAlias Neuer Alias für das Command
	 */
	protected void addAlias(String newAlias) {
		alias.add(newAlias);
	}

	/**
	 * Prüft ob der übergebene String einen Command bzw. einen Alias entspricht.
	 * Liefert true zurück falls war ansonsten falsch. Groß- und Kleinschreibung
	 * wird nicht beachtet.
	 * 
	 * @param input String der womöglich ein Command aufrufen soll
	 * @return boolean
	 */
	public boolean checkForMatch(String input) {
		if (command.equalsIgnoreCase(input)) {
			return true;
		} else {
			for (String a : alias) {
				if (a.equalsIgnoreCase(input))
					return true;
			}

		}

		return false;
	}

	/**
	 * Liefert alle vorhanden alias eines Commands.
	 * 
	 * @return String
	 */
	public String getAllAllias() {
		if (alias.size() == 0)
			return "";
		else {
			StringBuffer strBf = new StringBuffer();

			for (String a : alias) {
				strBf.append(a.toLowerCase());
			}

			return strBf.toString();
		}
	}
}
